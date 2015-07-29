/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.service;

import com.api.dto.ClientOffers;
import com.api.dto.Clients;

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
 * @author t311372
 */
@Service
public class ClientsJobsService {

    @Autowired
    private ElasticClientConf elasticClientConf;
    private TransportClient client;
    
    
    /**
     * 
     * @param clientOffers
     * @return
     * @throws JsonProcessingException 
     */
    public String addJobs(ClientOffers clientOffers) throws JsonProcessingException {

        client = elasticClientConf.getClient();

        ObjectMapper mapper = new ObjectMapper();

        byte[] json = mapper.writeValueAsBytes(clientOffers);

        IndexResponse response = client.prepareIndex("cvdb", "jobs")
                .setSource(json)
                .execute()
                .actionGet();

        String id = response.getId();
        return id;
    }
    
    /**
     * 
     * @param jobs
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException 
     */
    public long updateOneById(ClientOffers jobs) throws IOException, InterruptedException, ExecutionException {

        client = elasticClientConf.getClient();
        ObjectMapper mapper = new ObjectMapper();

        byte[] json = mapper.writeValueAsBytes(jobs);

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("cvdb");
        updateRequest.type("jobs");
        updateRequest.id(jobs.getId());
        updateRequest.doc(json);

        UpdateResponse get = client.update(updateRequest).get();
        long version = get.getVersion();

        return version;
    }

//    public Clients getById(String id) throws IOException {
//
//        client = elasticClientConf.getClient();
//        Clients readValue = null;
//        try {
//
//            GetResponse response = client.
//                    prepareGet("cvdb", "client", id)
//                    .execute()
//                    .actionGet();
//
//            ObjectMapper mapper = new ObjectMapper();
//            readValue = mapper.readValue(response.getSourceAsString(), Clients.class);
//        } catch (NullPointerException e) {
//
//            return null;
//        }
//        return readValue;
//    }

    public ArrayList<ClientOffers> getAll() throws IOException {

        client = elasticClientConf.getClient();
//        QueryBuilder qb = QueryBuilders.queryStringQuery(id);
        QueryBuilder qb = QueryBuilders.matchAllQuery();
        SearchResponse response = client.prepareSearch("cvdb")
                .setTypes("jobs")
                .setQuery(qb) // Query
                .execute()
                .actionGet();

        SearchHit[] hits = response.getHits().getHits();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<ClientOffers> ClientOffersList = new ArrayList<>();

        if (hits.length > 0) {
            for (int i = 0; i < hits.length; i++) {
                ClientOffers readValue = mapper.readValue(hits[i].getSourceAsString(), ClientOffers.class);
                readValue.setId(hits[i].getId());
                ClientOffersList.add(readValue);

            }
        }
        return ClientOffersList;

    }
}
