/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.service;

import com.api.dto.Candidat;
import com.api.dto.Experiences;
import com.api.dto.PartialCandidat;
import com.api.dto.Techonologies;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class TimeExpService {

    private static final Logger LOG = LoggerFactory.getLogger(TimeExpService.class);

    @Autowired
    private ExpService expService;
    @Autowired
    private ElasticClientConf elasticClientConf;
    private TransportClient client;

    public String addTimeExpOrUpdate(Candidat candidat) throws JsonProcessingException, IOException, InterruptedException, ExecutionException {

        client = elasticClientConf.getClient();
        ArrayList<Experiences> allExpForCandidat = expService.getByIdSearhText(candidat.getId());


        HashMap<String, Float> allTechno = new HashMap<>();
        ArrayList<Techonologies> technoFromCandidatList = getTechoByCandidatId(candidat.getId());
        // si il ya des techno deja enregistré
        if (technoFromCandidatList != null) {

            for (Techonologies technoFromCandidat : technoFromCandidatList) {
                allTechno.put(technoFromCandidat.getName(), technoFromCandidat.getNbYears());
            }
        }

        String id = null;
        for (Experiences exp : allExpForCandidat) {
            
            for (String technoList : exp.getTecnoList()) {
                if (!allTechno.containsKey(technoList)) {
                    allTechno.put(technoList, exp.getDuration());
                } else {
                    
                    Float get = allTechno.get(technoList);
                    float expduration = exp.getDuration();
                    if(get != expduration){
                        get = expduration;
                    }
                    allTechno.remove(technoList);
                    allTechno.put(technoList, get);
                }
            }

        }
        for (Map.Entry<String, Float> entrySet : allTechno.entrySet()) {

            String key = entrySet.getKey();
            Float value = entrySet.getValue();
            Techonologies techonologies = new Techonologies();
            techonologies.setName(key);
            techonologies.setNbYears(value);

            PartialCandidat partialCandidat = new PartialCandidat();
            partialCandidat.setId(candidat.getId());
            partialCandidat.setName(candidat.getName());

            techonologies.setPartialCandidat(partialCandidat);
            String tecnoExist = isTecnoExist(key);
            if (tecnoExist != null) {
                id = this.updateOneById(techonologies);
            } else {
                ObjectMapper mapper = new ObjectMapper();

                byte[] json = mapper.writeValueAsBytes(techonologies);

                IndexResponse response = client.prepareIndex("cvdb", "techno")
                        .setSource(json)
                        .execute()
                        .actionGet();

                id = response.getId();
            }
        }

        return id;
    }

    public Techonologies getById(String id) {

        client = elasticClientConf.getClient();
        Techonologies readValue = null;
        try {

            GetResponse response = client.
                    prepareGet("cvdb", "techno", id)
                    .execute()
                    .actionGet();

            ObjectMapper mapper = new ObjectMapper();
            readValue = mapper.readValue(response.getSourceAsString(), Techonologies.class);
        } catch (NullPointerException e) {
            return null;
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
            return null;
        }
        return readValue;
    }

    public String updateOneById(Techonologies techno) throws IOException, InterruptedException, ExecutionException {

        client = elasticClientConf.getClient();
        ObjectMapper mapper = new ObjectMapper();

        byte[] json = mapper.writeValueAsBytes(techno);

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("cvdb");
        updateRequest.type("techno");
        updateRequest.id(techno.getId());
        updateRequest.doc(json);

        UpdateResponse get = client.update(updateRequest).get();
        String id = get.getId();

        return id;
    }

    public ArrayList<Techonologies> getTechoByCandidatId(String id) throws IOException {

        LOG.debug("id du candidat {} ", id);
        client = elasticClientConf.getClient();
//        QueryBuilder qb = QueryBuilders.queryStringQuery(id);
        QueryBuilder qb = QueryBuilders.matchQuery("partialCandidat.id", id);
        SearchResponse response = client.prepareSearch("cvdb")
                .setTypes("techno")
                .setQuery(qb) // Query
                .execute()
                .actionGet();

        SearchHit[] hits = response.getHits().getHits();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Techonologies> candidateTechno = new ArrayList<>();

        if (hits.length > 0) {
            for (int i = 0; i < hits.length; i++) {
                Techonologies readValue = mapper.readValue(hits[i].getSourceAsString(), Techonologies.class);
                readValue.setId(hits[i].getId());
                candidateTechno.add(readValue);

            }
        } else {
            return null;
        }

        return candidateTechno;

    }

    /**
     *
     * @param id
     * @return id of the techno
     */
    private String isTecnoExist(String id) {

        Techonologies byId = this.getById(id);
        if (byId != null) {
            return byId.getId();
        } else {
            return null;
        }
    }

}
