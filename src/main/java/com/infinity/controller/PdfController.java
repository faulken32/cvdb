/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author t311372
 */
@Controller
public class PdfController  {

  

    @RequestMapping(value = "/generate/pdf", method = RequestMethod.GET)
    ModelAndView generatePdf(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
 

        
//        Employee employee = new Employee();
//        employee.setFirstName("Yashwant");
//        employee.setLastName("Chavan");
        
       Map map = new HashMap<>(); 
        ArrayList<String> word = new ArrayList<>();
        word.add("toto");
        word.add("titi");
       map.put("wordList", word);

        return new ModelAndView("pdfView", map);

      
    }

}
