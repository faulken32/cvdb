package com.infinity.controller;

import com.infinity.service.CandidatService;
import com.infinity.service.mail.SendMail;
import java.io.IOException;
import javax.mail.MessagingException;
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
    
    @Autowired
    private SendMail sendMail;

    /**
     * Handle the main page
     *
    
     * @return
     * @throws java.io.IOException
     * @throws javax.mail.MessagingException
     */
    @RequestMapping(value = {"/"})
    public ModelAndView getIndex() throws IOException, MessagingException {
        
        sendMail.send();
        
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("candidat", candidatService.getAll());
        return mv;
    }

}
