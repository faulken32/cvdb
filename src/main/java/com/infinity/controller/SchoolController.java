/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;


import com.api.dto.Candidat;
import com.api.dto.PartialCandidat;
import com.api.dto.School;
import com.infinity.service.CandidatService;
import com.infinity.service.SchoolService;
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
public class SchoolController {
    
    private static final Logger LOG = LoggerFactory
            .getLogger(SchoolController.class);
    
    @Autowired
    private SchoolService schoolService;
    
    @Autowired
    private CandidatService candidatService;
    
    
     @RequestMapping(value = {"/school/get/{id}"}, method = RequestMethod.GET)
    public ModelAndView getUpdateFormSchool(@PathVariable String id) {
        
       
        School byId = schoolService.getById(id);
        
        ModelAndView modelAndView = new ModelAndView("updateSchool");
        modelAndView.addObject("school", byId);
        
        return modelAndView;
    }
    
    
      @RequestMapping(value = {"/school/update"}, method = RequestMethod.POST)
    public String updateSchoolForm(@ModelAttribute("school") School school , String id) throws IOException, InterruptedException, ExecutionException {
        
        
        
        Candidat byId = candidatService.getById(school.getPartialCandidat().getId());
        
        PartialCandidat partialCandidat1 = new PartialCandidat();
        partialCandidat1.setId(byId.getId());
        partialCandidat1.setName(byId.getName());
        
        school.setId(id);
        school.setPartialCandidat(partialCandidat1);
        
        schoolService.updateOneById(school);
        
        return "redirect:/elastic/get/" + school.getPartialCandidat().getId();
    }
    
       @RequestMapping(value = {"/school/add"}, method = RequestMethod.POST)
    public String addSchoolForm(@ModelAttribute("school") School school , String id) throws IOException, InterruptedException, ExecutionException {
        
        
        
        Candidat byId = candidatService.getById(id);
        
        PartialCandidat partialCandidat1 = new PartialCandidat();
        partialCandidat1.setId(byId.getId());
        partialCandidat1.setName(byId.getName());
        
    
        school.setPartialCandidat(partialCandidat1);
        
        schoolService.addSchool(school);
        
        return "redirect:/elastic/get/" + school.getPartialCandidat().getId();
    }
     
      @RequestMapping(value = {"/school/add/{id}"}, method = RequestMethod.GET )
    public ModelAndView add(@PathVariable String id) throws IOException, InterruptedException, ExecutionException {
        
        
        
        Candidat byId = candidatService.getById(id);
        ModelAndView modelAndView = new ModelAndView("addSchool");
        modelAndView.addObject("candidat", byId);
        
        
        return modelAndView;
    }
    
    
    
}
