/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



/**
 *
 * @author nicolas
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    
    
    @Autowired            
    DataSource dataSource;

//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
// 
//	  auth.jdbcAuthentication().dataSource(dataSource)
//		.usersByUsernameQuery(
//			"select email,pass from users where email=?")
//		.authoritiesByUsernameQuery(
//			"select email, role from userrole where email=?");
//	}
    
    
}
