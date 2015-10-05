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
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        clientOffers.setPartialsClients(partialsClients);

        String addJobs = clientsJobsService.addJobs(clientOffers);
        clientOffers.setId(addJobs);
        clientsJobsService.updateOneById(clientOffers);

        ModelAndView mv = new ModelAndView("redirect:/client/job/update/" + addJobs + "/" + clienId);
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

        ArrayList<ClientOffers> all = clientsJobsService.getAllByClientId(clienId);

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
        
        if(jobs != null && jobs.getTechnoCriterias() == null){
           
            
            jobs.setTechnoCriterias(new ArrayList<>());
        }
        
        
        if (jobs != null && jobs.getTechnoCriterias() != null) {                                                             
            
            if (jobs.getTechnoCriterias().isEmpty()) {

                ArrayList<TechnoCriteria> technoList = new ArrayList<>();

                technoList.add(technoCriteria);
                jobs.setTechnoCriterias(technoList);
                clientsJobsService.updateOneById(jobs);
            } else {

                ArrayList<TechnoCriteria> technoCriterias = jobs.getTechnoCriterias();
                technoCriterias.add(technoCriteria);
                jobs.setTechnoCriterias(technoCriterias);
                clientsJobsService.updateOneById(jobs);
            }

            
        } 
        return "redirect:/client/job/update/{offerId}/{clienId}";
    }

    @RequestMapping(value = {"/client/job/criteria/techno/update/{offerId}/{clienId}"}, method = RequestMethod.POST)
    public String UpdateCriteriaTechnoList(HttpServletRequest request, HttpServletResponse httpServletResponse, @PathVariable String offerId) throws IOException, InterruptedException, ExecutionException {

        Map<String, String[]> parameterMap = request.getParameterMap();
        ArrayList<TechnoCriteria> technoList = new ArrayList<>();

        for (int i = 0; i < parameterMap.size(); i++) {
            TechnoCriteria technoCriteria = new TechnoCriteria();

            if (parameterMap.containsKey("technoName-" + i)) {

                technoCriteria.setTechnoName(parameterMap.get("technoName-" + i)[0]);

            }
            if (parameterMap.containsKey("expDurationStart-" + i)) {

                technoCriteria.setExpDurationStart(Integer.valueOf(parameterMap.get("expDurationStart-" + i)[0]));

            }
            if (parameterMap.containsKey("expDurationEnd-" + i)) {

                technoCriteria.setExpDurationEnd(Integer.valueOf(parameterMap.get("expDurationEnd-" + i)[0]));

            }
            if (technoCriteria.getTechnoName() != null) {
                technoList.add(technoCriteria);
            }

        }
        ClientOffers fromDb = clientsJobsService.getById(offerId);
        fromDb.setTechnoCriterias(technoList);
        clientsJobsService.updateOneById(fromDb);

        return "redirect:/client/job/update/{offerId}/{clienId}";
    }
}
