package com.infinity.controller;

import com.infinity.service.CandidatService;
import com.infinity.service.ExpService;
import java.io.IOException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private static final Logger LOG = LoggerFactory
            .getLogger(HomeController.class);

    @Autowired
    private CandidatService candidatService;

    

    /**
     * Handle the main page
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/"})
    public ModelAndView getIndex() throws IOException {

    
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("candidat", candidatService.getAll());
        return mv;
    }

}
