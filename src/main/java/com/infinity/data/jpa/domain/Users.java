/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.data.jpa.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;   


/**
 *
 * @author nicolas
 */

@Entity
@Table(name = "users")

public class Users implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusers")
    private Integer idusers;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
   
  
    
    @Column(name = "pass")
    private String pass;
    
    @Column(name = "email")
    private String email;
  
   

    public Users() {
    }

    public Users(Integer idusers) {
        this.idusers = idusers;
    }

    public Integer getIdusers() {
        return idusers;
    }

    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    @Override
    public String toString() {
        return "com.infinity.data.jpa.domain.Users[ idusers=" + idusers + " ]";
    }
    
}
