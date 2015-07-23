/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.service;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author t311372
 */

@Service
public class ClientsService {
    
    
    
      @Autowired
    private ElasticClientConf elasticClientConf;
    private TransportClient client;

    public String addCandidat(Client clientDto) throws JsonProcessingException {

        client =  elasticClientConf.getClient();

        ObjectMapper mapper = new ObjectMapper();

        byte[] json = mapper.writeValueAsBytes(clientDto);

        IndexResponse response = client.prepareIndex("cvdb", "client")
                .setSource(json)
                .execute()
                .actionGet();

        String id = response.getId();
        return id;
    }
    
}
