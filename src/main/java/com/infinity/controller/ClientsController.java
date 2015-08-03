/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.api.dto.ClientOffers;
import com.api.dto.Clients;

import com.api.dto.PartialsClients;
import com.api.dto.partialFromModel.PartialClientOffers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.infinity.service.ClientsJobsService;
import com.infinity.service.ClientsService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private ClientsJobsService clientsJobsService;

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
    public ModelAndView addClient(@ModelAttribute("clients") Clients clients) throws JsonProcessingException, IOException, ExecutionException, InterruptedException {

        String addClient = clientsService.addClient(clients);
        clients.setId(addClient);
        clientsService.updateOneById(clients);

        return new ModelAndView("redirect:/client/all");
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
     * @throws java.io.IOException
     */
    @RequestMapping(value = {"/client/job/add/{clienId}"}, method = RequestMethod.GET)
    public ModelAndView addJobs(@PathVariable String clienId) throws IOException {

        ModelAndView mv = new ModelAndView("addJob");
        mv.addObject("client", clienId);

        return mv;
    }

    /**
     *
     *
     * @param partialClientOffers
     * @param clienId
     * @return @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @RequestMapping(value = {"/client/job/add/{clienId}"}, method = RequestMethod.POST)
    public ModelAndView addJobs(@ModelAttribute PartialClientOffers partialClientOffers, @PathVariable String clienId) throws IOException, InterruptedException, ExecutionException {

        Clients c = clientsService.getById(clienId);
        PartialsClients partialsClients = new PartialsClients();
        partialsClients.setId(c.getId());
        partialsClients.setName(c.getName());

        ArrayList<String> technolist = new ArrayList<>();
        String[] splitExpName = partialClientOffers.getExpName().split(",");
        
//        for (String splitExpName1 : splitExpName) {
//              technolist.add(partialClientOffers.getExpName() + ":" + partialClientOffers.getExpRange());
//        }
//        
//        
      

        ClientOffers clientOffers = new ClientOffers();
        clientOffers.setDesc(partialClientOffers.getDesc());
        clientOffers.setExpTotal(partialClientOffers.getExpTotal());
        clientOffers.setProfiType(partialClientOffers.getProfiType());
        clientOffers.setProfileName(partialClientOffers.getProfileName());
        clientOffers.setTechnoList(technolist);
        clientOffers.setPartialsClients(partialsClients);

        String jobsId = clientsJobsService.addJobs(clientOffers);
        clientOffers.setId(jobsId);
        clientsJobsService.updateOneById(clientOffers);
        ModelAndView mv = new ModelAndView("redirect:/client/all");
        return mv;
    }

    /**
     *
     * @param clienId
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = {"/client/job/all/{clienId}"}, method = RequestMethod.GET)
    public ModelAndView getAllJobs(@PathVariable String clienId) throws IOException {

        ArrayList<ClientOffers> all = clientsJobsService.getAll();

        ModelAndView mv = new ModelAndView("allJobs");
        mv.addObject("allJobs", all);

        return mv;
    }

    @RequestMapping(value = {"/client/job/update/{offerId}"}, method = RequestMethod.GET)
    public ModelAndView updateJobs(@PathVariable String offerId) throws IOException {

        ModelAndView mv = new ModelAndView("updateJobs");
        ClientOffers jobs = clientsJobsService.getById(offerId);
        
        if (jobs.getTechnoList() != null) {

            ArrayList<String> technoList = jobs.getTechnoList();
            HashMap<String, String> hashTechno = new HashMap<>();

            for (String value : technoList) {
                String[] split = value.split(":");
                hashTechno.put(split[0], split[1]);
            }
            mv.addObject("techno", hashTechno);
        }
        mv.addObject("jobs", jobs);
        
        return mv;
    }

}
