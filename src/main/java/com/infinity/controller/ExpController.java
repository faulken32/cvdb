/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.api.dto.Experiences;
import com.infinity.service.ExpService;
import java.io.IOException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author t311372
 */

@Controller
public class ExpController {

     private static final Logger LOG = LoggerFactory.getLogger(ExpController.class);
     
     @Autowired
     private ExpService expService;
     
     
    @RequestMapping(value = {"/exp/all/{candidatId}"}, method = RequestMethod.GET)
    public ModelAndView allExp(@PathVariable String candidatId) throws IOException {
        
         ArrayList<Experiences> byIdSearhText = expService.getByIdSearhText(candidatId);
        
        ModelAndView mv = new ModelAndView("expList");
        mv.addObject("exp", byIdSearhText);
        
        return mv;
    }
    
}
