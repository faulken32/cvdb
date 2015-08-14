/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.infinity.dto.Candidat;
import com.infinity.dto.ClientOffers;
import com.infinity.service.ClientsService;
import com.infinity.service.SearchEngineServices;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author t311372
 */
@Controller
public class ResController {

    private static final Logger LOG = LoggerFactory.getLogger(ResController.class);

    @Autowired
    private SearchEngineServices searchEngineServices;

 

    @RequestMapping(value = {"/res"})
    public ModelAndView getIndex() throws IOException {

        HashMap<ClientOffers, ArrayList<Candidat>> searchEngine = searchEngineServices.searchEngine();
        
//        Collection<ArrayList<Candidat>> values = searchEngine.values();
        
        
        ModelAndView mv = new ModelAndView("searchResults");
      
        mv.addObject("candidat",searchEngine);
        return mv;
    }
}
