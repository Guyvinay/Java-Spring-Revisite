package com.app.service.impl;

import com.app.modal.Profile;
import com.app.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Profile saveProfile(Profile profile) throws IOException {
        UUID uuid = UUID.randomUUID();
        profile.setId(uuid.toString());

        Map<String, Object> objectMap =   convertProfileDocumentToMap(profile);

        IndexRequest indexRequest = new IndexRequest("profiles", "_doc",profile.getId() )
                .source(objectMap, XContentType.JSON);

        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println(indexResponse.getResult().name());

        System.out.println(objectMap);
        return profile;
    }

    @Override
    public Profile findById(String id) throws IOException {
        GetRequest getRequest =  new GetRequest("profiles", "_doc", id);
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> responseMap =  getResponse.getSource();
        System.out.println(responseMap);
        Profile profile = objectMapper.convertValue(responseMap, Profile.class);
        return profile;
    }

    @Override
    public List<Profile> findAllProfiles() throws IOException {
        SearchRequest searchRequest = new SearchRequest("profiles");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//        System.out.println(searchResponse);

        return getSearchResult(searchResponse);
    }

    private List<Profile> getSearchResult(SearchResponse searchResponse) {
        List<Profile> profiles = new ArrayList<>();
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        System.out.println(Arrays.toString(searchHits));

        if(searchHits.length>0){

            Arrays.stream(searchHits)
                    .forEach(
                            hit->
                                profiles.add(
                                        convertMapDocumentToProfile(hit.getSourceAsMap())
                                )
                    );
        }
        return profiles;
    }

    private Profile convertMapDocumentToProfile(Map<String, Object> sourceAsMap) {
        return objectMapper.convertValue(sourceAsMap, Profile.class);
    }

    private Map<String, Object> convertProfileDocumentToMap(Profile profileDocument) {
        return objectMapper.convertValue(profileDocument, Map.class);
    }




//    @Autowired
//    private ObjectMapper objectMapper;

//    @Override
//    public String saveProfile(Profile profile) {
//
//        UUID uuid = UUID.randomUUID();
//        profile.setId(uuid.toString());
//        return profile.getId();
//    }

}
