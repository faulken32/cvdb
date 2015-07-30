/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.service;


import javax.annotation.PreDestroy;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Utilisateur
 */
@Service
public class ElasticClientConf {

    
    
    private TransportClient client;
    private static final Logger LOG = LoggerFactory
            .getLogger(ElasticClientConf.class);

    
    
    public ElasticClientConf() {

        client = new TransportClient()
//                .addTransportAddress(new InetSocketTransportAddress("10.60.198.75", 9300));
                .addTransportAddress(new InetSocketTransportAddress("192.168.0.12", 9301))
                .addTransportAddress(new InetSocketTransportAddress("192.168.0.12", 9302));
        
        
        LOG.info("client créated");

    }

    public TransportClient getClient() {
        return client;
    }

    public void setClient(TransportClient client) {
        this.client = client;
    }
    
    @PreDestroy
    public void close(){
        client.close();
    }
}
