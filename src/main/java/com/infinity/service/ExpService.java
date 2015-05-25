/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.service;

import com.api.dto.Experiences;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import org.elasticsearch.action.search.SearchResponse;
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
public class ExpService {

    @Autowired
    private ElasticClientConf elasticClientConf;
    private TransportClient client;

    public  ArrayList<Experiences> getById(String id) throws IOException {

        client = elasticClientConf.getClient();
        QueryBuilder qb = QueryBuilders.queryStringQuery(id);
        SearchResponse response = client.prepareSearch("cvdb")
                .setTypes("exp")
                .setQuery(qb) // Query
                .execute()
                .actionGet();

        SearchHit[] hits = response.getHits().getHits();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Experiences> candidateExp = new ArrayList<>();
        if (hits.length > 0) {
            for (int i = 0; i < hits.length; i++) {

               candidateExp.add( mapper.readValue(hits[i].getSourceAsString(), Experiences.class));

            }
        }

        return candidateExp;
//        Experiences readValue = mapper.readValue(response., Candidat.class);
    }

}
