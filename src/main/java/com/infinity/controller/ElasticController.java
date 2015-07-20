/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.api.dto.Candidat;
import com.api.dto.Comments;
import com.api.dto.Experiences;
import com.api.dto.PartialCandidat;
import com.api.dto.School;
import com.api.dto.Techonologies;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.infinity.service.CandidatService;
import com.infinity.service.CommentsService;
import com.infinity.service.ExpService;
import com.infinity.service.TimeExpService;
import com.infinity.service.SchoolService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ElasticController {

    private static final Logger LOG = LoggerFactory
            .getLogger(ElasticController.class);

    @Autowired
    private CandidatService candidatService;

    @Autowired
    private ExpService expService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private SchoolService schoolService;
    
    

    @RequestMapping(value = {"/elastic/get/{id}"})
    public ModelAndView getCandidat(@PathVariable String id) throws IOException {
        
        
        ModelAndView mv = new ModelAndView("elastic");
        CandidatEnum candidatEnum = new CandidatEnum();
        
        
        Candidat byId = candidatService.getById(id);
        if (byId == null) {
             mv.addObject("noCandidat", true);
        } else {
            
            byId.setId(id);
            float nbYearExp = 0;
            ArrayList<Experiences> byId1 = expService.getByIdSearhText(id);
            for (Experiences byId11 : byId1) {
                float duration = byId11.getDuration();
                nbYearExp += duration;
            }
            
            byId.setNbYearExp(nbYearExp);
            ArrayList<Comments> commentsList = commentsService.getByCandidatId(id);

            ArrayList<School> schoolList = schoolService.getByIdSearhText(id);
            
            
            if (!byId1.isEmpty()) {
                mv.addObject("exp", byId1);
            } else {
                LOG.debug("no exp found for {}", id);
            }
            mv.addObject("candidat", byId);
            mv.addObject("status", candidatEnum.getStatusList());
            mv.addObject("comments", commentsList);
            mv.addObject("school", schoolList);
        }
        return mv;
    }

    @RequestMapping(value = {"/elastic/update"}, method = RequestMethod.POST)
    public ModelAndView updateCandidat(@ModelAttribute("candidat") Candidat candidat) throws IOException, InterruptedException, ExecutionException {
        
        float nbYearExp = 0;
            ArrayList<Experiences> byId1 = expService.getByIdSearhText(candidat.getId());
            for (Experiences byId11 : byId1) {
                float duration = byId11.getDuration();
                nbYearExp += duration;
            }
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        if (candidat.getMobilite() != null) {

            List<String> mobilite = candidat.getMobilite();
            List<String> remove = new ArrayList<>();
            for (String value : mobilite) {
                if (value.isEmpty()) {
                    remove.add(value);

                }
            }
            mobilite.removeAll(remove);
        }
        candidat.setNbYearExp(nbYearExp);
        candidat.setUpdateDate(simpleDateFormat.format(date));
        long updateOneById = candidatService.updateOneById(candidat);

        String valueOf = String.valueOf(updateOneById);

        LOG.debug(valueOf);

        ModelAndView mv = new ModelAndView("redirect:/elastic/get/" + candidat.getId());

        return mv;
    }

    @RequestMapping(value = {"/elastic/exp/update/{id}"}, method = RequestMethod.GET)
    public ModelAndView getUpdateFormExp(@PathVariable String id) {

        Experiences byId = expService.getById(id);

        ModelAndView modelAndView = new ModelAndView("updateExp");
        modelAndView.addObject("exp", byId);

        return modelAndView;
    }

    @RequestMapping(value = {"/elastic/exp/update/{id}"}, method = RequestMethod.POST)
    public String updateFormExp(@ModelAttribute("exp") Experiences exp, String candiatId) throws InterruptedException, JsonProcessingException, ExecutionException, UnsupportedEncodingException, IOException, ParseException {

        LOG.debug(candiatId);

        Candidat candidat = candidatService.getById(candiatId);
        candidat.setId(candiatId);
        PartialCandidat partialCandidat = new PartialCandidat();
        partialCandidat.setId(candidat.getId());
        partialCandidat.setName(candidat.getName());
        ArrayList<String> emptyTechno = new ArrayList<>();
        List<String> techno = exp.getTecnoList();
        for (String technoToRemove : exp.getTecnoList()) {
            if (technoToRemove.isEmpty()) {
                emptyTechno.add(technoToRemove);
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date start = simpleDateFormat.parse(exp.getStart());
        Date end = simpleDateFormat.parse(exp.getEnd());

        long diff =  end.getTime() - start.getTime() ;
        
        float aYear = 31536000000.0f;
        float nbYear = diff / aYear ;
        techno.removeAll(emptyTechno);
        exp.setDuration(nbYear);
        exp.setTecnoList(techno);
        exp.setPartialCandidat(partialCandidat);
        expService.updateById(exp);
//        timeExpService.addTimeExpOrUpdate(candidat);

        return "redirect:/elastic/get/" + candiatId;
    }

    @RequestMapping(value = {"/elastic/exp/add/{id}"}, method = RequestMethod.GET)
    public ModelAndView addExp(@PathVariable String id) throws IOException, InterruptedException, ExecutionException {

        LOG.debug("id candidat {}", id);
        Candidat byId = candidatService.getById(id);
        PartialCandidat partialCandidat = new PartialCandidat();
        partialCandidat.setId(id);
        partialCandidat.setName(byId.getName());

        Experiences experiences = new Experiences();
        experiences.setPartialCandidat(partialCandidat);

        ModelAndView mv = new ModelAndView("addexp");
        mv.addObject("exp", experiences);
        return mv;
    }

    @RequestMapping(value = {"/elastic/exp/add/{id}"}, method = RequestMethod.POST)
    public String addExp(@ModelAttribute("exp") Experiences exp, String candidatid) throws IOException, InterruptedException, ExecutionException, ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = simpleDateFormat.parse(exp.getStart());
        Date end = simpleDateFormat.parse(exp.getEnd());

        long diff =  end.getTime() - start.getTime() ;
        float nbYear = diff / 31536000000.0f;

        Candidat candidat = candidatService.getById(candidatid);
        candidat.setId(candidatid);
        PartialCandidat partialCandidat = new PartialCandidat();
        partialCandidat.setId(candidat.getId());
        partialCandidat.setName(candidat.getName());
        exp.setDuration(nbYear);
        exp.setPartialCandidat(partialCandidat);

        expService.addExp(exp);
//        timeExpService.addTimeExpOrUpdate(candidat);
        

        return "redirect:/elastic/get/" + candidatid;
    }
}
