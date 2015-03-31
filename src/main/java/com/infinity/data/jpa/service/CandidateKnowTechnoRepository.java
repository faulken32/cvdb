/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.data.jpa.service;

import com.infinity.data.jpa.domain.Candidate;
import com.infinity.data.jpa.domain.CandidateKnowTechno;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ncanicatti
 */
public interface CandidateKnowTechnoRepository extends CrudRepository<CandidateKnowTechno, Integer> {
    
}
