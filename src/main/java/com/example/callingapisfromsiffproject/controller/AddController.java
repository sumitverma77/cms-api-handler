package com.example.callingapisfromsiffproject.controller;

import com.example.callingapisfromsiffproject.request.AddRequest;
import com.example.callingapisfromsiffproject.service.ContactService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("call/")
public class AddController {

    @Autowired
   private ContactService contactService;
    @PostMapping("add/api")
    public ResponseEntity<JsonNode> add(@RequestBody AddRequest addRequest) {
        return contactService.addService(addRequest);
    }
}
