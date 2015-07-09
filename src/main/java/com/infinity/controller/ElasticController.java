/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.api.dto.Candidat;
import com.api.dto.Comments;
import com.api.dto.Experiences;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.infinity.service.CandidatService;
import com.infinity.service.CommentsService;
import com.infinity.service.ExpService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

/**
 *
 * @author Utilisateur
 */
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

    @RequestMapping(value = {"/elastic/get/{id}"})
    public ModelAndView getCandidat(@PathVariable String id) throws IOException {

        Candidat byId = candidatService.getById(id);
        byId.setId(id);

        ArrayList<Experiences> byId1 = expService.getByIdSearhText(id);

        ArrayList<Comments> commentsList = commentsService.getByCandidatId(id);

        ModelAndView mv = new ModelAndView("elastic");

        if (!byId1.isEmpty()) {
            mv.addObject("exp", byId1);
        } else {
            LOG.debug("no exp found for {}", id);
        }
        mv.addObject("candidat", byId);
        mv.addObject("comments", commentsList);
        return mv;
    }

    @RequestMapping(value = {"/elastic/update"}, method = RequestMethod.POST)
    public ModelAndView updateCandidat(@ModelAttribute("candidat") Candidat candidat) throws IOException, InterruptedException, ExecutionException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        List<String> mobilite = candidat.getMobilite();
        List<String> remove = new ArrayList<>();
        for (String value : mobilite) {
            if (value.isEmpty()) {
                remove.add(value);

            }
        }
        mobilite.removeAll(remove);
        candidat.setUpdateDate(simpleDateFormat.format(date));
        long updateOneById = candidatService.updateOneById(candidat);

        String valueOf = String.valueOf(updateOneById);

        LOG.debug(valueOf);

        ModelAndView mv = new ModelAndView("redirect:get/" + candidat.getId());

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
    public String updateFormExp(@ModelAttribute("exp") Experiences exp, String candiatId) throws InterruptedException, JsonProcessingException, ExecutionException, UnsupportedEncodingException, IOException {

        LOG.debug(candiatId);

        Candidat byId = candidatService.getById(candiatId);

        exp.setCandidat(byId);
        expService.updateById(exp);

        return "redirect:/elastic/get/" + candiatId;
    }

    @RequestMapping(value = {"/elastic/exp/add/{id}"}, method = RequestMethod.GET)
    public ModelAndView addExp(@PathVariable String id) throws IOException, InterruptedException, ExecutionException {

        LOG.debug("id candidat {}", id);
        Candidat byId = candidatService.getById(id);

        Experiences experiences = new Experiences();
        experiences.setCandidat(byId);

        ModelAndView mv = new ModelAndView("addexp");
        mv.addObject("exp", experiences);
        return mv;
    }

    @RequestMapping(value = {"/elastic/exp/add/{id}"}, method = RequestMethod.POST)
    public String addExp(@ModelAttribute("exp") Experiences exp, String candidatid) throws IOException, InterruptedException, ExecutionException, ParseException {

        Candidat byId = candidatService.getById(candidatid);
      
        exp.setCandidat(byId);

        expService.addExp(exp);

        return "redirect:/elastic/get/" + candidatid;
    }
}
