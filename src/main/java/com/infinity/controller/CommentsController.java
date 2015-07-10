/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.api.dto.Candidat;
import com.api.dto.Comments;
import com.api.dto.PartialCandidat;
import com.infinity.service.CandidatService;
import com.infinity.service.CommentsService;
import java.io.IOException;
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
 * @author t311372
 */
@Controller
public class CommentsController {

    private static final Logger LOG = LoggerFactory.getLogger(CommentsController.class);

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CandidatService candidatService;

    @RequestMapping(value = {"/comments/get/{id}"}, method = RequestMethod.GET)
    public ModelAndView getComments(@PathVariable String id) throws IOException {

        Comments byId = commentsService.getById(id);

        ModelAndView mv = new ModelAndView("comments");

        mv.addObject("comments", byId);

        return mv;
    }

    @RequestMapping(value = {"/comments/update/{id}"}, method = RequestMethod.POST)
    public String updateFormComments(@ModelAttribute("comments") Comments comments, String id) throws IOException, InterruptedException, ExecutionException {

        Candidat byId = candidatService.getById(comments.getPartialCandidat().getId());

        PartialCandidat partialCandidat = comments.getPartialCandidat();

        partialCandidat.setName(byId.getName());

        comments.setId(id);
        comments.setPartialCandidat(partialCandidat);

        commentsService.updateOneById(comments);

        return "redirect:/elastic/get/" + comments.getPartialCandidat().getId();
    }

    @RequestMapping(value = {"/comments/add/{id}"}, method = RequestMethod.GET)
    public ModelAndView addComments(@PathVariable String id) throws IOException {

        ModelAndView mv = new ModelAndView("addComments");
        Candidat byId = candidatService.getById(id);
        
        PartialCandidat partialCandidat = new PartialCandidat();
        partialCandidat.setId(byId.getId());
        partialCandidat.setName(byId.getName());
        
        Comments comments = new Comments();
        comments.setPartialCandidat(partialCandidat);
        
        mv.addObject("comments", comments);
        return mv;
    }
    
    @RequestMapping(value = {"/comments/add"}, method = RequestMethod.POST)
    public String addFormComments(@ModelAttribute("comments") Comments comments) throws IOException, InterruptedException, ExecutionException {

        Candidat byId = candidatService.getById(comments.getPartialCandidat().getId());
        PartialCandidat partialCandidat = comments.getPartialCandidat();
        partialCandidat.setName(byId.getName());
  
        comments.setPartialCandidat(partialCandidat);

        commentsService.addComments(comments);

        return "redirect:/elastic/get/" + comments.getPartialCandidat().getId();
    }

}
