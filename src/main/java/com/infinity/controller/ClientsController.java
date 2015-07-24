/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.api.dto.Clients;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.infinity.service.ClientsService;
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
public class ClientsController {

    private static final Logger LOG = LoggerFactory.getLogger(ClientsController.class);
    @Autowired
    private ClientsService clientsService;

    @RequestMapping(value = {"/client/add"}, method = RequestMethod.GET)
    public ModelAndView addClient() {

        ModelAndView mv = new ModelAndView("addClient");

        return mv;
    }

    /**
     *
     * @param clients
     * @return
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @RequestMapping(value = {"/client/add"}, method = RequestMethod.POST)
    public String addClient(@ModelAttribute("clients") Clients clients) throws JsonProcessingException {

        clientsService.addClient(clients);

        return "index";
    }

    @RequestMapping(value = {"/client/update/{id}"}, method = RequestMethod.GET)
    public ModelAndView updateClient(@PathVariable String id) throws IOException {

        ModelAndView mv = new ModelAndView("addClient");
        mv.addObject("client", clientsService.getById(id));
        return mv;
    }

    @RequestMapping(value = {"/client/update/{id}"}, method = RequestMethod.POST)
    public ModelAndView updateClientForm(@ModelAttribute("clients") Clients clients, @PathVariable String id) throws IOException, InterruptedException, ExecutionException {

        clients.setId(id);
        clientsService.updateOneById(clients);

        ModelAndView mv = new ModelAndView("addClient");
        mv.addObject("client", clientsService.getById(id));
        return mv;

    }

    @RequestMapping(value = {"/client/all"}, method = RequestMethod.GET)
    public ModelAndView allClient() throws IOException {

        ModelAndView mv = new ModelAndView("allClient");
        mv.addObject("client", clientsService.getAll());

        return mv;
    }

    /**
     *
     * @param clienId
     * @return
     */
    @RequestMapping(value = {"/client/job/add/{id}"}, method = RequestMethod.GET)
    public ModelAndView addJobs(@PathVariable String clienId)  {
        
        ModelAndView mv = new ModelAndView("addJob");
       

        return mv;
    }

}
