package com.example.callingapisfromsiffproject.service;

import com.example.callingapisfromsiffproject.request.AddRequest;
import com.example.callingapisfromsiffproject.request.SearchByBothRequest;
import com.example.callingapisfromsiffproject.response.AddResponse;
import com.example.callingapisfromsiffproject.response.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ContactService {
    @Autowired
    private RestTemplate restTemplate;
public List<SearchResponse> searchByBoth(SearchByBothRequest searchByBothRequest)
{
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<SearchByBothRequest> requestEntity = new HttpEntity<>(searchByBothRequest, headers);

    String url = "http://localhost:8080/contact/search/both";

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

public AddResponse addService(AddRequest addRequest)  {
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);

       HttpEntity<AddRequest> requestEntity = new HttpEntity<>(addRequest, headers);
       //with header add request is also needed

       String url = "http://localhost:8080/contact/add";

       ResponseEntity<AddResponse> response = restTemplate.exchange(
               url,
               HttpMethod.POST,
               requestEntity,
               AddResponse.class
       );
       AddResponse addResponse = response.getBody();

       addResponse.setMsg(addResponse.getMsg().toUpperCase());
       return addResponse ;

       //------------------------------------------------------------------------------------------------------------

//       HttpHeaders headers = new HttpHeaders();
//       headers.setContentType(MediaType.APPLICATION_JSON);
//
//       HttpEntity<AddRequest> requestEntity = new HttpEntity<>(addRequest, headers);
//
//       String url = "http://localhost:8080/contact/add";
//
//       ResponseEntity<String> response = restTemplate.exchange(
//               url,
//               HttpMethod.POST,
//               requestEntity,
//               String.class
//       );
//       String  responseBody = response.getBody();
//
//       try {
//           ObjectMapper objectMapper = new ObjectMapper();
//           JsonNode jsonResponse = objectMapper.readTree(responseBody);
//           String msg = jsonResponse.get("msg").asText();
//           if (msg != null) {
//               msg = msg.toUpperCase();
//               ((ObjectNode) jsonResponse).put("msg", msg);
//           }
//
//           return ResponseEntity.status(response.getStatusCode()).body(jsonResponse);
//       } catch (JsonProcessingException e) {
//           e.printStackTrace();
//           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//       }


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

