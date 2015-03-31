/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.data.jpa.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ncanicatti
 */
@Entity
@Table(name = "user_Know_Techno")
public class CandidateKnowTechno implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; 
    
    
    
    @ManyToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;
    
    
    @ManyToOne
    @JoinColumn(name="techno_id")
    private Technologies techno;


    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Technologies getTechno() {
        return techno;
    }

    public void setTechno(Technologies techno) {
        this.techno = techno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

        
    
    

    
  
    
    
    
    

}
