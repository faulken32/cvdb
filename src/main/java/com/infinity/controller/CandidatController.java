/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.infinity.dto.Candidat;
import com.infinity.dto.ClientOffers;
import com.infinity.dto.Clients;
import com.infinity.dto.Experiences;
import com.infinity.dto.School;
import com.infinity.dto.SendTo;
import com.infinity.service.CandidatService;
import com.infinity.service.ClientsService;
import com.infinity.service.ExpService;
import com.infinity.service.PowerSearchEngine;
import com.infinity.service.SchoolService;
import com.infinity.service.mail.SendMail;
import com.infinity.tools.DateTools;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
public class CandidatController {

    private static final Logger LOG = LoggerFactory.getLogger(CandidatController.class);

    @Autowired
    private CandidatService candidatService;
    @Autowired
    private ExpService expService;
    @Autowired
    private SchoolService schoolService;

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private SendMail sendMailService;

    @Autowired
    private PowerSearchEngine powerSearchEngine;

    @RequestMapping(value = {"/candidat"}, method = RequestMethod.GET)
    public ModelAndView addOne() throws IOException {

        ModelAndView mv = new ModelAndView("addCandidat");

        return mv;
    }

    /**
     *
     * @param candidat
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"/candidat"}, method = RequestMethod.POST)
    public String addOneForm(@ModelAttribute Candidat candidat) throws IOException {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        candidat.setEnterDate(simpleDateFormat.format(date));

        String id = candidatService.addCandidat(candidat);
        return "redirect:/elastic/get/" + id;
    }

    @RequestMapping(value = {"/candidat/get/{candidatName}"}, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ArrayList<Candidat>> getByName(@PathVariable String candidatName) {

        ArrayList<Candidat> byName = null;
        ResponseEntity<ArrayList<Candidat>> responseEntity = null;
        try {
            byName = candidatService.getByName(candidatName);
            responseEntity = new ResponseEntity<>(byName, HttpStatus.OK);

        } catch (Exception e) {
            LOG.error(e.getMessage());
            responseEntity = new ResponseEntity<>(byName, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @RequestMapping(value = {"/candidat/export/{id}/{clientId}"}, method = RequestMethod.GET)
    public ModelAndView getByNameForExport(@PathVariable String id, @PathVariable String clientId) throws IOException {

        ModelAndView modelAndView = new ModelAndView("export");

        Candidat byId = candidatService.getById(id);
        ArrayList<Experiences> byIdSearhText = expService.getByIdSearhText(id);
        ArrayList<School> schoolList = schoolService.getByIdSearhText(id);
        modelAndView.addObject("candidat", byId);
        modelAndView.addObject("exp", byIdSearhText);
        modelAndView.addObject("school", schoolList);

        return modelAndView;
    }

    @RequestMapping(value = {"/candidat/export/{id}/{clientId}"}, method = RequestMethod.POST)
    public String getByNameForExportFrom(@PathVariable String id, @PathVariable String clientId, String contends) throws IOException, InterruptedException, ExecutionException {

        contends = " <!DOCTYPE html><html>\n"
                + "         <head>\n"
                + "            <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n"
                + "           \n"
                + "         </head>" + contends + "</html>";

        ModelAndView modelAndView = new ModelAndView("export");

        Clients client = clientsService.getById(clientId);

        Candidat candidat = candidatService.getById(id);
        ArrayList<SendTo> sendToList = candidat.getSendTo();
        SendTo sendTo = new SendTo();
        sendTo.setCandidatId(id);
        sendTo.setClientId(clientId);
        sendTo.setDate(DateTools.getDateNow());

        ArrayList<String> emailListSended = new ArrayList<>();
        emailListSended.add(client.getEmail());
        sendTo.setEmail(emailListSended);
        sendTo.setDate(new Date().toString());

        if (sendToList == null) {
            ArrayList<SendTo> newsendToList = new ArrayList<>();
            newsendToList.add(sendTo);
            candidat.setSendTo(newsendToList);
        } else {

            sendToList.add(sendTo);
            candidat.setSendTo(sendToList);
        }
        candidatService.updateOneById(candidat);

        sendMailService.send(client.getEmail(), contends);
        return "redirect:/res";
    }

    @RequestMapping(value = {"/power"}, method = RequestMethod.GET)
    public ModelAndView powerSearch() {

//        HashMap<ClientOffers, ArrayList<Candidat>> matchCandidat = powerSearchEngine.matchCandidat();

        ModelAndView modelAndView = new ModelAndView("power");

        return modelAndView;

    }

    @RequestMapping(value = {"/power"}, method = RequestMethod.POST)
    public ModelAndView powerSearchPost(String cvContends, String mobilite) throws IOException {

        ArrayList<Candidat> powerSearch = candidatService.powerSearch(mobilite, cvContends);

        ModelAndView modelAndView = new ModelAndView("power");

        modelAndView.addObject("candidat", powerSearch);

        return modelAndView;

    }

    @RequestMapping(value = {"/power/res"}, method = RequestMethod.GET)
    public ModelAndView powerSearchRes() {

        HashMap<ClientOffers, ArrayList<Candidat>> matchCandidat = powerSearchEngine.matchCandidat();

        ModelAndView modelAndView = new ModelAndView("searchResultsPower");
        modelAndView.addObject("candidat",matchCandidat);
        return modelAndView;

    }
    
    
    @RequestMapping(value = {"/candidat/exportPower/{id}/{clientId}"}, method = RequestMethod.GET)
    public ModelAndView getByNameForExportPower(@PathVariable String id, @PathVariable String clientId) throws IOException {

        ModelAndView modelAndView = new ModelAndView("exportPower");

        Candidat byId = candidatService.getById(id);
    
        modelAndView.addObject("candidat", byId);
    
        return modelAndView;
    }
}
