package com.infinity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private static final Logger LOG = LoggerFactory
            .getLogger(HomeController.class);

    /**
     * Handle the main page
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/"})
    public ModelAndView getIndex() {

        ModelAndView mv = new ModelAndView("index");

        return mv;
    }

}
