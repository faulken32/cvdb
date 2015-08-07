/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.service;

import com.infinity.dto.Candidat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Utilisateur
 */
@Service
public class CandidatService {
//

    @Autowired
    private ElasticClientConf elasticClientConf;
    private TransportClient client;

    public String addCandidat(Candidat candidat) throws JsonProcessingException {

        client = elasticClientConf.getClient();

        ObjectMapper mapper = new ObjectMapper();

        byte[] json = mapper.writeValueAsBytes(candidat);

        IndexResponse response = client.prepareIndex("cvdb", "candidat")
                .setSource(json)
                .execute()
                .actionGet();

        String id = response.getId();
        return id;
    }

    public Candidat getById(String id) throws IOException {

        client = elasticClientConf.getClient();
         Candidat readValue = null;
        try {

            GetResponse response = client.
                    prepareGet("cvdb", "candidat", id)
                    .execute()
                    .actionGet();

            ObjectMapper mapper = new ObjectMapper();
            readValue = mapper.readValue(response.getSourceAsString(), Candidat.class);
        } catch (NullPointerException e) {
            
            return  null;
        }
        return readValue;
    }

    public long updateOneById(Candidat candidat) throws IOException, InterruptedException, ExecutionException {

        client = elasticClientConf.getClient();
        ObjectMapper mapper = new ObjectMapper();

        byte[] json = mapper.writeValueAsBytes(candidat);

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("cvdb");
        updateRequest.type("candidat");
        updateRequest.id(candidat.getId());
        updateRequest.doc(json);

        UpdateResponse get = client.update(updateRequest).get();
        long version = get.getVersion();

        return version;
    }
    
    
     public ArrayList<Candidat> getAll() throws IOException {

        client = elasticClientConf.getClient();

        QueryBuilder qb = QueryBuilders.matchAllQuery();
        SearchResponse response = client.prepareSearch("cvdb")
                .setTypes("candidat")
                .setQuery(qb) // Query
                .setFrom(0).setSize(100).setExplain(true)
                .execute()
                .actionGet();

        SearchHit[] hits = response.getHits().getHits();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Candidat> candidatList = new ArrayList<>();

        if (hits.length > 0) {
            for (int i = 0; i < hits.length; i++) {
                Candidat readValue = mapper.readValue(hits[i].getSourceAsString(), Candidat.class);
                readValue.setId(hits[i].getId());
                candidatList.add(readValue);

            }
        }
        return candidatList;

    }
     
     
     public ArrayList<Candidat> getByName(final String name) throws IOException {

      
        client = elasticClientConf.getClient();
//        QueryBuilder qb = QueryBuilders.queryStringQuery(name);
       QueryBuilder qb = QueryBuilders.prefixQuery("name", name);
        SearchResponse response = client.prepareSearch("cvdb")
                .setTypes("candidat")               
                .setQuery(qb)
                .setFrom(0).setSize(100).setExplain(true) 
//                 .addSort(fieldSort("end").order(DESC).missing("_last"))// Query
                .execute()
                .actionGet();

        SearchHit[] hits = response.getHits().getHits();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Candidat> candidat = new ArrayList<>();

        if (hits.length > 0) {
            for (int i = 0; i < hits.length; i++) {
                Candidat readValue = mapper.readValue(hits[i].getSourceAsString(), Candidat.class);
                readValue.setId(hits[i].getId());
                candidat.add(readValue);

            }
        }
        return candidat;

    }

}
