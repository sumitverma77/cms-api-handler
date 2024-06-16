package com.example.callingapisfromsiffproject.facades;

import com.example.callingapisfromsiffproject.dto.request.AddRequest;
import com.example.callingapisfromsiffproject.dto.request.DeleteRequest;
import com.example.callingapisfromsiffproject.dto.request.SearchByBothRequest;
import com.example.callingapisfromsiffproject.dto.response.AddResponse;
import com.example.callingapisfromsiffproject.dto.response.DeleteResponse;
import com.example.callingapisfromsiffproject.dto.response.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class ContactManagement {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WebClient webClient;
     @Autowired
     private RedisTemplate redisTemplate;
    public static final String ADD_SERVICE = "add";
    public static final String DELETE_SERVICE = "delete";
    public static final String SEARCH_BY_BOTH_SERVICE = "search/both";
    @Value("${ContactManagement.baseUrl}")
    private String baseUrl;

    public AddResponse addService(AddRequest addRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AddRequest> requestEntity = new HttpEntity<>(addRequest, headers);
        //with header add request is also needed

        String url = baseUrl + ADD_SERVICE;
        ResponseEntity<AddResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                AddResponse.class
        );
        return response.getBody();
    }
    public DeleteResponse deleteContact(DeleteRequest deleteRequest)
    {

        DeleteResponse deleteResponse= webClient.method(HttpMethod.DELETE)
                .uri(baseUrl+DELETE_SERVICE)
                .body(BodyInserters.fromValue(deleteRequest))
                .retrieve()
                .bodyToMono(DeleteResponse.class)
                .block();
        return deleteResponse;
    }
    public List<SearchResponse> searchByBoth(SearchByBothRequest searchByBothRequest)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SearchByBothRequest> requestEntity = new HttpEntity<>(searchByBothRequest, headers);

        String url = baseUrl+SEARCH_BY_BOTH_SERVICE;
        ResponseEntity<List> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                //Rest Template does't support Post HttpMethod
                requestEntity,
                List.class
                //new ParameterizedTypeReference<List<SearchResponse>>() {}
        );
        return response.getBody();
    }

}
