/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.dto;

/**
 *
 * @author t311372
 */
public class ClientsCriteria {
    
    private String id;
    private int expTotal;
    private String profileName;
    private String profiType;
    private PartialsClients partialsClients;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getExpTotal() {
        return expTotal;
    }

    public void setExpTotal(int expTotal) {
        this.expTotal = expTotal;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfiType() {
        return profiType;
    }

    public void setProfiType(String profiType) {
        this.profiType = profiType;
    }

    public PartialsClients getPartialsClients() {
        return partialsClients;
    }

    public void setPartialsClients(PartialsClients partialsClients) {
        this.partialsClients = partialsClients;
    }
    
    
    
    
}
