/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import com.infinity.dto.ClientOffers;
import com.infinity.dto.Clients;
import com.infinity.dto.PartialsClients;
import com.infinity.dto.TechnoCriteria;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.infinity.service.ClientsJobsService;
import com.infinity.service.ClientsService;
import java.io.IOException;
import java.util.ArrayList;
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
        ArrayList<Clients> all = clientsService.getAll();
        LOG.debug("all : " + Integer.toString(all.size()));
        ModelAndView mv = new ModelAndView("allClients");
        mv.addObject("client", all);

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
        clientsJobsService.getById(clienId);
//        mv.addObject("jobs", clienId);

        return mv;
    }

    /**
     *
     *
     * @param clientOffers
     * @param partialClientOffers
     * @param clienId
     * @return @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @RequestMapping(value = {"/client/job/add/{clienId}"}, method = RequestMethod.POST)
    public ModelAndView addJobs(@ModelAttribute ClientOffers clientOffers, @PathVariable String clienId) throws IOException, InterruptedException, ExecutionException {

        Clients c = clientsService.getById(clienId);

        PartialsClients partialsClients = new PartialsClients();
        partialsClients.setId(c.getId());
        partialsClients.setName(c.getName());

        TechnoCriteria technoCriteria = new TechnoCriteria();
        technoCriteria.setTechnoName("java");
        technoCriteria.setExpDurationEnd(1);
        technoCriteria.setExpDurationStart(0);

        ArrayList<TechnoCriteria> arrayList = new ArrayList<>();
        arrayList.add(technoCriteria);
        clientOffers.setPartialsClients(partialsClients);
        clientOffers.setTechnoCriterias(arrayList);
        String addJobs = clientsJobsService.addJobs(clientOffers);
        clientOffers.setId(addJobs);
        clientsJobsService.updateOneById(clientOffers);

        ModelAndView mv = new ModelAndView("redirect:/client/job/update/" + clienId + "/" + addJobs);
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

    @RequestMapping(value = {"/client/job/update/{offerId}/{clienId}"}, method = RequestMethod.GET)
    public ModelAndView updateJobs(@PathVariable String clienId, @PathVariable String offerId) throws IOException {

        ModelAndView mv = new ModelAndView("addJob");

        ClientOffers jobs = clientsJobsService.getById(offerId);
        mv.addObject("jobs", jobs);

        return mv;
    }

    /**
     *
     * @param clientOffers
     * @param clienId
     * @param offerId
     * @param redirectAttrs
     * @return
     * @throws IOException
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    @RequestMapping(value = {"/client/job/update/{offerId}/{clienId}"}, method = RequestMethod.POST)
    public String updateJobs(@ModelAttribute ClientOffers clientOffers,
            @PathVariable String clienId,
            @PathVariable String offerId) throws IOException, InterruptedException, ExecutionException {

//        ClientOffers clientOffers1 = clientsJobsService.getById(offerId);
        clientOffers.setId(offerId);
        ClientOffers offerFromDb = clientsJobsService.getById(offerId);
        if (offerFromDb != null) {
            ArrayList<TechnoCriteria> technoCriterias = offerFromDb.getTechnoCriterias();
            clientOffers.setTechnoCriterias(technoCriterias);
        }
        
        Clients byId = clientsService.getById(clienId);
                       
        
        PartialsClients partialsClients = new PartialsClients();
        partialsClients.setId(clienId);
        partialsClients.setName(byId.getName());
        clientOffers.setPartialsClients(partialsClients);

        clientsJobsService.updateOneById(clientOffers);


        return "redirect:/client/job/update/{offerId}/{clienId}";
    }

    @RequestMapping(value = {"/client/job/criteria/updateAdd/{offerId}/{clienId}"}, method = RequestMethod.POST)
    public String addOrUpdateCriteria(@ModelAttribute ClientOffers clientOffers,
            @PathVariable String clienId,
            @PathVariable String offerId, @ModelAttribute TechnoCriteria technoCriteria) throws IOException, InterruptedException, ExecutionException {

        ClientOffers jobs = clientsJobsService.getById(offerId);

        if (jobs != null && jobs.getTechnoCriterias() == null) {
            technoCriteria.setId("1");
            ArrayList<TechnoCriteria> technoList = new ArrayList<>();
            technoList.add(technoCriteria);
            
            jobs.setTechnoCriterias(technoList);
            clientsJobsService.updateOneById(jobs);

        } else {
            
            ArrayList<TechnoCriteria> technoCriteriasList = jobs.getTechnoCriterias();
            if (technoCriteria.getId() == null) {
                int size = technoCriteriasList.size();
                technoCriteria.setId(Integer.toString(size+1));
                technoCriteriasList.add(technoCriteria);
                clientsJobsService.updateOneById(jobs);
            } else {
                for (TechnoCriteria criteriaFromList : technoCriteriasList) {
                    
                    if(criteriaFromList.getId().equals(technoCriteria.getId())){
                        
                        criteriaFromList = technoCriteria;
                        technoCriteriasList.remove(criteriaFromList);
                        technoCriteriasList.add(technoCriteria);
                    }
                    
                }
            }
           
        }

        return "redirect:/client/job/update/{offerId}/{clienId}";
    }

}
