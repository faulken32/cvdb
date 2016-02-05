/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infinity.dto.Candidat;
import com.infinity.dto.Mail;
import com.infinity.service.CandidatService;
import com.infinity.service.mail.MailService;
import com.infinity.tools.ConstantType;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author t311372
 */
@Controller
public class MailController {

    private static final Logger LOG = LoggerFactory.getLogger(MailController.class);
    @Autowired
    private CandidatService candidatService;
    private static final String SEND_TO_ALL_JSP = "sendToAll";
    private static final String MAIL_UPDATE_STATUS = "Ou en ête vous ?";
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private MailService mailService;
    /**
     *
     * URL du web service d'envoie pour le site pulbic valeur dans le fichier de
     * config
     */
    @Value("${MAIL_URL}")
    private String UPDATE_STATUS_URL;
    private final static String MAIL_TEMPLATE_FILE = "/checkStatus.vm";

    /**
     * executé en cron retourne les candidat qui n'ont pas eu de d'activité au
     * cours du mois précedant
     *
     * @throws IOException
     * @throws Exception
     */
    @Scheduled(cron="0 * * * * ?")
    public void SendEmailToInactiveCandidate() throws IOException, Exception {

        List<Candidat> findLastMonthOfInavtivity = candidatService.findLastMonthOfInavtivity();

        for (Candidat findLastMonthOfInavtivity1 : findLastMonthOfInavtivity) {

            LOG.debug(findLastMonthOfInavtivity1.getName());
            findLastMonthOfInavtivity1.setStatus("nosearch");
            candidatService.updateOneById(findLastMonthOfInavtivity1);
            if (findLastMonthOfInavtivity1.getEmail() != null) {
                        String loadTemplateFromVelocity = this.loadTemplateFromVelocity(findLastMonthOfInavtivity1);
//                        mailService.send(findLastMonthOfInavtivity1.getEmail(), loadTemplateFromVelocity, MailController.MAIL_UPDATE_STATUS);
            }
        }

    }

    /**
     *
     * @return @throws JsonProcessingException
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public ModelAndView mailTemplate() throws JsonProcessingException, IOException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        List<Mail> all = (List<Mail>) mailService.getAll(ConstantType.MAIL_TYPE, Mail.class);
        ModelAndView modelAndView = new ModelAndView("mail");

        if (all != null && !all.isEmpty()) {
            Mail get = all.get(0);
            if (get != null) {
                modelAndView.addObject("mail", get);
            }

        } else {

            Mail createDefaultTemplate = this.createDefaultTemplate();
            mailService.add(createDefaultTemplate, ConstantType.MAIL_TYPE);
        }

        return modelAndView;

    }

    /**
     *
     * @param mail
     * @return
     * @throws JsonProcessingException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public ModelAndView mailTemplateForm(@ModelAttribute("mail") Mail mail) throws JsonProcessingException, NoSuchFieldException, IllegalAccessException, IllegalArgumentException, InterruptedException, ExecutionException {

        ModelAndView modelAndView = new ModelAndView("mail");
        if (mail != null) {
            mailService.updateOneById(mail, ConstantType.MAIL_TYPE);
        }
        modelAndView.addObject("mail", mail);
        return modelAndView;
    }

    /**
     *
     * @return
     */
    private Mail createDefaultTemplate() {

        Mail mail = new Mail();
        mail.setId(UUID.randomUUID().toString());
        mail.setFrom("contact@infinity-web.fr");
        mail.setContends("default");

        return mail;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/mail/sendToAll", method = RequestMethod.GET)
    public ModelAndView sendEmailToALL() {

        ModelAndView modelAndView = new ModelAndView(MailController.SEND_TO_ALL_JSP);

        return modelAndView;

    }

    /**
     * Envoie un mail a tous les candidats
     *
     * @param send resultat de la check box
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/mail/sendToAll", method = RequestMethod.POST)
    public ModelAndView sendEmailToALLPost(boolean send) throws IOException {

        ModelAndView modelAndView = new ModelAndView(MailController.SEND_TO_ALL_JSP);
        List<String> missingEmail = new ArrayList<>();
        if (send == true) {
            ArrayList<Candidat> all = candidatService.getAll();
            for (Candidat candidat : all) {
                if (candidat != null) {
                    if (candidat.getEmail() != null) {
                        String loadTemplateFromVelocity = this.loadTemplateFromVelocity(candidat);
                        mailService.send(candidat.getEmail(), loadTemplateFromVelocity, MailController.MAIL_UPDATE_STATUS);
                    } else {
                        missingEmail.add(candidat.getName());
                    }
                }
            }
        }
        modelAndView.addObject("missing", missingEmail);
        return modelAndView;

    }

    @RequestMapping(value = {"/candidat/sendMail/{candidaId}"}, method = RequestMethod.GET)
    public ModelAndView sendEMailToSpecificCandidat(@PathVariable String candidaId) throws IOException {

        Candidat candidat = candidatService.getById(candidaId);

        ModelAndView modelAndView = new ModelAndView("sendToAll");
        modelAndView.addObject("candidat", candidat);
        return modelAndView;
    }

    @RequestMapping(value = {"/candidat/sendMail/{candidaId}"}, method = RequestMethod.POST)
    public ModelAndView sendEMailToSpecificCandidatPost(@PathVariable String candidaId, boolean send) throws IOException {

        Candidat candidat = candidatService.getById(candidaId);
        String error = null;
        
        if (send && (candidat != null || candidat.getEmail() != null)) {
            if (!candidat.getEmail().isEmpty()) {

                String loadTemplateFromVelocity = this.loadTemplateFromVelocity(candidat);
                mailService.send(candidat.getEmail(), loadTemplateFromVelocity, MailController.MAIL_UPDATE_STATUS);
            } else {
               error = "adresse email vide"; 
               
            }
            
        } else {
        
            error = "pas de candidat ou pas d'adresse email";
        } 
        
        
        

        ModelAndView modelAndView = new ModelAndView("sendToAll");
        modelAndView.addObject("res", error);
        modelAndView.addObject("all", true);
        return modelAndView;
    }

    /**
     *
     * @return
     */
    private String loadTemplateFromVelocity(Candidat candidat) {
        String template = null;
        Map<String, Object> model = new HashMap<>();

        if (candidat != null) {
            model.put("candidat", candidat);
            model.put("url", UPDATE_STATUS_URL);
            template = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, MAIL_TEMPLATE_FILE, "UTF8", model);
        }

        return template;
    }
}
