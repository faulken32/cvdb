/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.data.jpa;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author faulken
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan(basePackages ={"com.infinity.data.jpa.service"})
@EnableSpringDataWebSupport


public class JpaConfig {

    @Value("${createTable}")
    private String createTable;
    
    
    @Bean(name="dataSopurce")
    public DataSource dataSource() {

        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver"); 
        dataSource.setUrl("jdbc:mysql://localhost:3306/cv");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;

    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        properties.setProperty("hibernate.hbm2ddl.auto", createTable);
//        properties.setProperty("showSql", "false");
        InstrumentationLoadTimeWeaver instrumentationLoadTimeWeaver = new InstrumentationLoadTimeWeaver();
        
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setLoadTimeWeaver(instrumentationLoadTimeWeaver);
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.infinity.data.jpa.domain");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(properties);
        factory.afterPropertiesSet();
        
        
        return factory.getObject();

    }
    
    
    @Bean 
    public JpaTransactionManager transactionManager(){
        
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        
        
        return jpaTransactionManager;
    
    } 
    
    
    
    
   
    

}
