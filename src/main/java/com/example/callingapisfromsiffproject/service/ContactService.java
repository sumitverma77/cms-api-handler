package com.example.callingapisfromsiffproject.service;

import com.example.callingapisfromsiffproject.facades.ContactManagement;
import com.example.callingapisfromsiffproject.request.AddRequest;
import com.example.callingapisfromsiffproject.request.DeleteRequest;
import com.example.callingapisfromsiffproject.request.SearchByBothRequest;
import com.example.callingapisfromsiffproject.response.AddResponse;
import com.example.callingapisfromsiffproject.response.DeleteResponse;
import com.example.callingapisfromsiffproject.response.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
public class ContactService {

    @Autowired
    private ContactManagement contactManagement;

    public DeleteResponse deleteContact(DeleteRequest deleteRequest) {
        DeleteResponse deleteResponse = contactManagement.deleteContact(deleteRequest);
        deleteResponse.setMsg(deleteResponse.getMsg().toUpperCase());
        return deleteResponse;
    }


    public List<SearchResponse> searchByBoth(SearchByBothRequest searchByBothRequest) {
        List<SearchResponse> searchResponse = contactManagement.searchByBoth(searchByBothRequest);
        return searchResponse;
    }


    public AddResponse addService(AddRequest addRequest) {
        AddResponse addResponse = contactManagement.addService(addRequest);
        addResponse.setMsg(addResponse.getMsg().toUpperCase());
        return addResponse;
    }
}

