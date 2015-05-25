/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.api.dto.Candidat;
import com.api.dto.Experiences;

import com.infinity.service.CandidatService;
import com.infinity.service.ExpService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    @RequestMapping(value = {"/elastic/get/{id}"})
    public ModelAndView getCandidat(@PathVariable String id) throws IOException {

        Candidat byId = candidatService.getById(id);
        byId.setId(id);
        
        ArrayList<Experiences> byId1 = expService.getById(id);
        
        
        ModelAndView mv = new ModelAndView("elastic");
        mv.addObject("candidat", byId);
        return mv;
    }
    
    
    @RequestMapping(value = {"/elastic/update"} , method = RequestMethod.POST)
    public ModelAndView updateCandidat(@ModelAttribute("candidat") Candidat candidat, BindingResult result) throws IOException, InterruptedException, ExecutionException {
        
        
      
        long updateOneById = candidatService.updateOneById(candidat);
       
         String valueOf = String.valueOf(updateOneById);
        
               
        
//                boolean hasErrors = result.hasErrors();
                
        LOG.debug(valueOf);
        
        ModelAndView mv = new ModelAndView("elastic");
        String totot ="titi";
        return mv;
    }

}
