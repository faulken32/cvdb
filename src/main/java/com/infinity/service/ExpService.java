package com.infinity.service;

import com.infinity.dto.Experiences;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infinity.dto.Candidat;
import com.infinity.dto.ClientOffers;
import com.infinity.dto.TechnoCriteria;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import static org.elasticsearch.search.sort.SortBuilders.fieldSort;
import static org.elasticsearch.search.sort.SortOrder.DESC;
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
    @Autowired
    private ClientsJobsService clientsJobsService;

    public ArrayList<Experiences> getByIdSearhText(String id) throws IOException {

        LOG.debug("id du candidat {} ", id);
        client = elasticClientConf.getClient();
//        QueryBuilder qb = QueryBuilders.queryStringQuery(id);
        QueryBuilder qb = QueryBuilders.matchQuery("partialCandidat.id", id);
        SearchResponse response = client.prepareSearch("cvdb")
                .setTypes("exp")
                .setQuery(qb)
                .setFrom(0).setSize(100).setExplain(true)
                .addSort(fieldSort("end").order(DESC).missing("_last"))// Query
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

    public long deleteById(String id) {

        DeleteResponse response = client.prepareDelete("cvdb", "exp", id)
                .execute()
                .actionGet();

        return response.getVersion();

    }

    public ArrayList<String> searchEngine(String jobOfferId) throws IOException {

        client = elasticClientConf.getClient();
        ClientOffers criteriaFromDb = clientsJobsService.getById(jobOfferId);
        ArrayList<Experiences> exp = new ArrayList<>();
        ArrayList<Candidat> candidatList = new ArrayList<>();

//      QueryBuilder qb = QueryBuilders.queryStringQuery("");
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        if (criteriaFromDb != null && criteriaFromDb.getExpTotalMin() != 0) {

            qb.must(QueryBuilders.rangeQuery("nbYearExp").from(criteriaFromDb.getExpTotalMin()).to(criteriaFromDb.getExpTotalMax()));
            qb.must(QueryBuilders.termQuery("mobilite", criteriaFromDb.getDep()));
            qb.mustNot(QueryBuilders.termQuery("status", "nosearch"));

            SearchResponse response = client.prepareSearch("cvdb")
                    .setTypes("candidat")
                    .setQuery(qb)
                    .setFrom(0).setSize(100).setExplain(true)
                    //                 .addSort(fieldSort("end").order(DESC).missing("_last"))// Query
                    .execute()
                    .actionGet();

            SearchHit[] hits = response.getHits().getHits();
            ObjectMapper mapper = new ObjectMapper();

            if (hits.length > 0) {
                for (SearchHit hit : hits) {
                    Candidat readValue = mapper.readValue(hit.getSourceAsString(), Candidat.class);
                    candidatList.add(readValue);
                }
            }

            if (!candidatList.isEmpty() && criteriaFromDb != null) {
                if (!criteriaFromDb.getTechnoCriterias().isEmpty()) {
                    BoolQueryBuilder qbExpCriteria = QueryBuilders.boolQuery();

                    for (TechnoCriteria technoCriteria : criteriaFromDb.getTechnoCriterias()) {

                        qbExpCriteria.should(QueryBuilders.queryStringQuery(technoCriteria.getTechnoName()));
                        qbExpCriteria.must(QueryBuilders.rangeQuery("duration")
                                .from(technoCriteria.getExpDurationStart())
                                .to(technoCriteria.getExpDurationEnd()));

                    }

                    SearchResponse responseCritetia = client.prepareSearch("cvdb")
                            .setTypes("exp")
                            .setQuery(qbExpCriteria)
                            .setFrom(0).setSize(100).setExplain(true)
                            //                 .addSort(fieldSort("end").order(DESC).missing("_last"))// Query
                            .execute()
                            .actionGet();

                    SearchHit[] hits1 = responseCritetia.getHits().getHits();

                    if (hits1.length > 0) {
                        for (SearchHit hits11 : hits1) {
                            Experiences readValue = mapper.readValue(hits11.getSourceAsString(), Experiences.class);
                            exp.add(readValue);
                        }
                    }
                }
            }
        }
        ArrayList<String> candidatIdRes = new ArrayList<> ();
        if (!candidatList.isEmpty()) {
            for (Candidat candidat : candidatList) {
                
                candidatIdRes.add(candidat.getId());
            }
        }

        return candidatIdRes;

    }
}
