package com.example.callingapisfromsiffproject.service;

import com.example.callingapisfromsiffproject.configuration.RestTemplateConfig;
import com.example.callingapisfromsiffproject.request.AddRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


    @Service
public class ContactService {
    @Autowired
    private RestTemplate restTemplate;

   public ResponseEntity<JsonNode> addService(AddRequest addRequest)  {

       //------------------------------------------------------------------------------------------------------------

       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);

       HttpEntity<AddRequest> requestEntity = new HttpEntity<>(addRequest, headers);

       String url = "http://localhost:8080/contact/add";

       ResponseEntity<String> response = restTemplate.exchange(
               url,
               HttpMethod.POST,
               requestEntity,
               String.class
       );
       String  responseBody = response.getBody();

       try {
           ObjectMapper objectMapper = new ObjectMapper();
           JsonNode jsonResponse = objectMapper.readTree(responseBody);
           String msg = jsonResponse.get("msg").asText();
           if (msg != null) {
               msg = msg.toUpperCase();
               ((ObjectNode) jsonResponse).put("msg", msg);
           }

           return ResponseEntity.status(response.getStatusCode()).body(jsonResponse);
       } catch (JsonProcessingException e) {
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }


       //---------------------------------------------------------------------------------------------------------


//       String name=addRequest.getName();
//       System.out.println(name);
//       String phone=addRequest.getPhone();
//       System.out.println(phone);
//       String url = "http://localhost:8080/contact/add";
//       String jsonData = "{\"name\":\"" + name + "\", \"phone\":\"" + phone + "\"}";
//       HttpHeaders headers = new HttpHeaders();
//       headers.setContentType(MediaType.APPLICATION_JSON);
//       HttpEntity<String> requestEntity = new HttpEntity<>(jsonData, headers);
//       ResponseEntity<String>response=restTemplate.postForEntity(url , requestEntity , String.class);
//      String responseBody = response.getBody();
//       try {
//           ObjectMapper objectMapper = new ObjectMapper();
//           JsonNode jsonResponse = objectMapper.readTree(responseBody);
//           long id = jsonResponse.get("id").asLong();
//           String msg = jsonResponse.get("msg").asText();
//           if (msg != null) {
//               msg = msg.toUpperCase();
//               ((ObjectNode) jsonResponse).put("msg", msg);
//           }
//           return ResponseEntity.status(response.getStatusCode()).body(jsonResponse);
//       }
//       catch (JsonProcessingException e) {
//           e.printStackTrace();
//           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//       }
   }
   }

