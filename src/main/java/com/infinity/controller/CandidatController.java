/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.api.dto.Candidat;
import com.infinity.service.CandidatService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public  ResponseEntity<ArrayList<Candidat>> getByName(@PathVariable String candidatName)  {

         ArrayList<Candidat> byName = null;
         ResponseEntity<ArrayList<Candidat>> responseEntity = null;
        try {
            byName = candidatService.getByName(candidatName);
            responseEntity = new ResponseEntity<>(byName, HttpStatus.OK);
           
        } catch (Exception e) {
             LOG.error(e.getMessage());
             responseEntity = new ResponseEntity<>(byName,HttpStatus.BAD_REQUEST);
        }              
        return responseEntity;
    }
}
