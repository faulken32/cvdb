package com.infinity.controller;

import com.infinity.data.jpa.domain.Candidate;
import com.infinity.data.jpa.service.CandidateRepository;
import com.infinity.data.jpa.service.UsersRepository;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import java.util.List;
import javax.persistence.PersistenceException;
import javax.servlet.http.Part;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private static final Logger LOG = LoggerFactory
            .getLogger(HomeController.class);

    @Autowired
    private UsersRepository services;

    @Autowired
    private CandidateRepository candidateRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     * Handle the main page
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/"})
    public ModelAndView getIndex() {

        List<Candidate> findAll = candidateRepository.findAll();

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("title", "indexTitre");
        mv.addObject("candidate", findAll);

        return mv;
    }

    @RequestMapping(value = {"/find/{id}"})
    public ModelAndView getIndex(@PathVariable Long id) {

        Candidate findOne = candidateRepository.findOne(id);

        ModelAndView mv = new ModelAndView("findOne");
        mv.addObject("candidate", findOne);

        return mv;
    }

    @RequestMapping(value = "/addone", method = RequestMethod.GET)
    public ModelAndView AddCandidate() {

        ModelAndView mv = new ModelAndView("addone");

        return mv;
    }

    /**
     * save candidat form 1
     *
     * @param file
     * @param candidate
     * @param result
     * @param model
     * @return
     * @ModelAttribute("candidate") Candidate candidate, BindingResult result,
     * Model model
     */
//    @Transactional
    @RequestMapping(value = "/addone", method = RequestMethod.POST)
    public ModelAndView AddCandidate(
            @ModelAttribute("candidate") Candidate candidate, BindingResult result) {

        boolean hasErrors = result.hasErrors();
        
        result.rejectValue("cv", "toto");
        boolean succes = false;
        boolean errorMessage = false;
//        try {
//            InputStream inputStream = file.getInputStream();
//        } catch (Exception e) {
//            LOG.error(e.getMessage());
//            
//        }

        if (!hasErrors) {

            candidate.setCreationdate(Date.from(Instant.now()));
            try {
                candidateRepository.save(candidate);
                succes = true;

            } catch (Throwable e) {
                Throwable ex = e;
                while (ex.getCause() != null) {

                    ex = ex.getCause();
                    if (ex instanceof ConstraintViolationException) {

                        ConstraintViolationException sqle = (ConstraintViolationException) ex;

                        if ("23000".equals(sqle.getSQLState())) {
                            errorMessage = true;
                        }
                    }
                }
            }
        } else {

            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError allError : allErrors) {

                LOG.debug(allError.toString());

            }
        }
        ModelAndView mv = new ModelAndView("addone");

        mv.addObject("succes", succes);
        mv.addObject("errorMsg", errorMessage);
        return mv;
    }
}
