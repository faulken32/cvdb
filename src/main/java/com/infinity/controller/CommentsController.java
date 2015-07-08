/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;


import com.api.dto.Candidat;
import com.api.dto.Comments;
import com.api.dto.PartialCandidat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.infinity.service.CandidatService;
import com.infinity.service.CommentsService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
    
    private static final Logger LOG = LoggerFactory
            .getLogger(CommentsController.class);
    
    
    @Autowired
    private CommentsService commentsService;
    
    @Autowired
    private CandidatService candidatService;
    
    
    
     @RequestMapping(value = {"/comments/get/{id}"})
    public ModelAndView getComments(@PathVariable String id) throws IOException {

        ArrayList<Comments> byIdSearhText = commentsService.getByIdSearhText(id);
    

    

        ModelAndView mv = new ModelAndView("comments");
        if (!byIdSearhText.isEmpty()) {
            mv.addObject("comments", byIdSearhText);
        } else {
            LOG.error("no comments found for {}", id);
        }
     
        return mv;
    }
    
    
    @RequestMapping(value = {"/comments/update/{id}"}, method = RequestMethod.POST)        
    public String updateFormComments(@ModelAttribute("comments") Comments exp, String candiatId) throws InterruptedException, JsonProcessingException, ExecutionException, UnsupportedEncodingException, IOException {
        
        LOG.debug(candiatId);
        
        Candidat byId = candidatService.getById(candiatId);
        PartialCandidat partialCandidat = new PartialCandidat();
        partialCandidat.setId(candiatId);
        partialCandidat.setName(byId.getName());
        
      
        

        
   

        return "redirect:/elastic/get/" + candiatId;
    }
}
