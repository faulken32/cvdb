/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.dto;

import java.io.Serializable;

/**
 *
 * @author Utilisateur
 */
public class Techonologies implements Serializable {
    
    private String _id;
    private String name;
    private Float nbYears;
    private PartialCandidat partialCandidat;

   

    public Techonologies() {
     
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getNbYears() {
        return nbYears;
    }

    public void setNbYears(Float nbYears) {
        this.nbYears = nbYears;
    }

    public PartialCandidat getPartialCandidat() {
        return partialCandidat;
    }

    public void setPartialCandidat(PartialCandidat partialCandidat) {
        this.partialCandidat = partialCandidat;
    }

    public String getId() {
        return _id;
    }

    public void setId(String Id) {
        this._id = Id;
    }


}
