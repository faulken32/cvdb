package com.infinity.controller;


import com.infinity.data.jpa.domain.Candidate;
import com.infinity.data.jpa.service.CandidateRepository;
import com.infinity.data.jpa.service.UsersRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static final Logger LOG = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private UsersRepository services;

	@Autowired
	private CandidateRepository candidateRepository;

	/**
	 * Handle the main page
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/" })
	public ModelAndView getIndex() {

		List<Candidate> findAll = candidateRepository.findAll();

		ModelAndView mv = new ModelAndView("index");
		mv.addObject("candidate", findAll);

		return mv;
	}

	@RequestMapping(value = { "/find/{id}" })
	public ModelAndView getIndex(@PathVariable Long id) {
		
		
		Candidate findOne = candidateRepository.findOne(id);
		
		ModelAndView mv = new ModelAndView("findOne");
		mv.addObject("candidate", findOne);

		return mv;
	}
        
        
        @RequestMapping(value =  "/addone" , method=RequestMethod.GET)
	public ModelAndView AddCandidate() {
		
		
		
		
		ModelAndView mv = new ModelAndView("addone");
		

		return mv;
	}
        
        @RequestMapping(value =  "/addone" , method=RequestMethod.POST)
	public ModelAndView AddCandidate(@ModelAttribute Candidate candidate) {
		
		
		LOG.debug(candidate.getName());
		
		ModelAndView mv = new ModelAndView("addone");
		

		return mv;
	}
}
