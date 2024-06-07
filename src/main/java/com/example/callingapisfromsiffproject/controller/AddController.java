package com.example.callingapisfromsiffproject.controller;

import com.example.callingapisfromsiffproject.request.AddRequest;
import com.example.callingapisfromsiffproject.request.SearchByBothRequest;
import com.example.callingapisfromsiffproject.response.AddResponse;
import com.example.callingapisfromsiffproject.response.SearchResponse;
import com.example.callingapisfromsiffproject.service.ContactService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("call/")
public class AddController {

    @Autowired
   private ContactService contactService;
    @PostMapping("add/api")
    public AddResponse add(@RequestBody AddRequest addRequest) {
        return contactService.addService(addRequest);
    }
    @GetMapping("search/api")
    public List<SearchResponse> search(@RequestBody SearchByBothRequest searchByBothRequest)
    {
        return contactService.searchByBoth(searchByBothRequest);
    }
}
