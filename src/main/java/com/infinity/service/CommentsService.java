/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.service;

import com.api.dto.Comments;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author t311372
 */

@Service
public class CommentsService {
    
    
    private static final Logger LOG = LoggerFactory.getLogger(CommentsService.class);

    @Autowired
    private ElasticClientConf elasticClientConf;
    private TransportClient client;
    
     public ArrayList<Comments> getByIdSearhText(String id) throws IOException {
        
        
        LOG.info("id du candidat {} ",id);
        client = elasticClientConf.getClient();

        QueryBuilder qb = QueryBuilders.matchQuery("partialCandidat.id",id);
        SearchResponse response = client.prepareSearch("cvdb")
                .setTypes("comments")
                .setQuery(qb) // Query
                .execute()
                .actionGet();

        SearchHit[] hits = response.getHits().getHits();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Comments> comments = new ArrayList<>();

        if (hits.length > 0) {
            for (SearchHit hit : hits) {
                Comments readValue = mapper.readValue(hit.getSourceAsString(), Comments.class);
                readValue.setId(hit.getId());
                comments.add(readValue);
            }
        }
        return comments;

    }

    
    
}
