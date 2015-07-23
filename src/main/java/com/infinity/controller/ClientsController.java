/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.api.dto.Candidat;
import com.api.dto.Experiences;
import com.api.dto.PartialCandidat;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
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
public class ClientsController {
    
    
    @RequestMapping(value = {"/client/add"}, method = RequestMethod.GET)
    public ModelAndView addExp() {

////        Candidat byId = candidatService.getById(id);
//        PartialCandidat partialCandidat = new PartialCandidat();
//        partialCandidat.setId(id);
//        partialCandidat.setName(byId.getName());
//
//        Experiences experiences = new Experiences();
//        experiences.setPartialCandidat(partialCandidat);

        ModelAndView mv = new ModelAndView("addClient");
     
        return mv;
    }
    
}
