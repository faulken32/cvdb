package com.infinity.service;

import com.api.dto.Experiences;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Utilisateur
 */
@Service
public class ExpService {

    private static final Logger LOG = LoggerFactory.getLogger(ExpService.class);

    @Autowired
    private ElasticClientConf elasticClientConf;
    private TransportClient client;

    public ArrayList<Experiences> getByIdSearhText(String id) throws IOException {
        
        
        LOG.debug("id du candidat {} ",id);
        client = elasticClientConf.getClient();
//        QueryBuilder qb = QueryBuilders.queryStringQuery(id);
        QueryBuilder qb = QueryBuilders.matchQuery("candidat.id",id);
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
                Experiences readValue = mapper.readValue(hits[i].getSourceAsString(), Experiences.class);
                readValue.setId(hits[i].getId());
                candidateExp.add(readValue);

            }
        }
        return candidateExp;

    }

    /**
     *
     * @param id
     * @return
     * @throws java.io.IOException
     */
    public Experiences getById(String id) {

        client = elasticClientConf.getClient();
        GetResponse response = client.
                prepareGet("cvdb", "exp", id)
                .execute()
                .actionGet();

        ObjectMapper mapper = new ObjectMapper();
        Experiences readValue = null;
        try {

            readValue = mapper.readValue(response.getSourceAsString(), Experiences.class);
            readValue.setId(id);
        } catch (IOException e) {

            LOG.error(e.getMessage());
        }

        return readValue;

    }

    public long updateById(Experiences exp) throws InterruptedException, JsonProcessingException, ExecutionException, UnsupportedEncodingException {

        client = elasticClientConf.getClient();
        ObjectMapper mapper = new ObjectMapper();

        final Charset utf8 = Charset.forName("UTF-8");
        byte[] json = mapper.writeValueAsBytes(exp);

        String convert = new String(json, utf8);
        byte[] ptext = convert.getBytes("UTF-8");
        LOG.debug(ptext.toString());
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("cvdb");
        updateRequest.type("exp");
        updateRequest.id(exp.getId());
        updateRequest.doc(ptext);

        UpdateResponse get = client.update(updateRequest).get();
        long version = get.getVersion();
        return version;

    }

    public long addExp(Experiences exp) throws JsonProcessingException {

        client = elasticClientConf.getClient();

        ObjectMapper mapper = new ObjectMapper();

        byte[] json = mapper.writeValueAsBytes(exp);

        IndexResponse response = client.prepareIndex("cvdb", "exp")
        .setSource(json)
        .execute()
        .actionGet();
        

        
        long version = response.getVersion();
        return version;
    }
}
