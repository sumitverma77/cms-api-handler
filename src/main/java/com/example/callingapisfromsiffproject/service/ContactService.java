package com.example.callingapisfromsiffproject.service;

import com.example.callingapisfromsiffproject.constant.MessageConstant;
import com.example.callingapisfromsiffproject.facades.ContactManagement;
import com.example.callingapisfromsiffproject.dto.request.AddRequest;
import com.example.callingapisfromsiffproject.dto.request.DeleteRequest;
import com.example.callingapisfromsiffproject.dto.request.SearchByBothRequest;
import com.example.callingapisfromsiffproject.dto.response.AddResponse;
import com.example.callingapisfromsiffproject.dto.response.DeleteResponse;
import com.example.callingapisfromsiffproject.dto.response.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContactService {

    @Autowired
    private ContactManagement contactManagement;
    @Autowired
    private RedisTemplate<String , Object> redisTemplate;

    public DeleteResponse deleteContact(DeleteRequest deleteRequest) {
        DeleteResponse deleteResponse = contactManagement.deleteContact(deleteRequest);
        deleteResponse.setMsg(deleteResponse.getMsg().toUpperCase());
        return deleteResponse;
    }
    public List<SearchResponse> searchByBoth(SearchByBothRequest searchByBothRequest) {
        List<SearchResponse> searchResponse = contactManagement.searchByBoth(searchByBothRequest);
        return searchResponse;
    }

    /**
     * cehcks in cache if phone number is present then it is returning for there only
     * else  calling for the external service to addContact
     *
     * @param addRequest - name and phone of user
     * @return - contact status saved or already presnent
     */
    public AddResponse addService(AddRequest addRequest) {
        AddResponse addResponse = new AddResponse();
        Object cachedValue = redisTemplate.opsForValue().get(addRequest.getPhone());

        if (cachedValue != null) {
            Long cachedId = Long.parseLong(cachedValue.toString());
            addResponse.setId(cachedId);
            addResponse.setMsg(MessageConstant.alreadyPresent);
            return addResponse;
        }

        AddResponse addResponseFromApi = contactManagement.addService(addRequest);
        System.out.println("Calling from database");

        redisTemplate.opsForValue().set(addRequest.getPhone(), addResponseFromApi.getId());

        return addResponseFromApi;
    }
}

