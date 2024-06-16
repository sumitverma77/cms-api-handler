package com.example.callingapisfromsiffproject.controller;

import com.example.callingapisfromsiffproject.dto.request.AddRequest;
import com.example.callingapisfromsiffproject.dto.request.DeleteRequest;
import com.example.callingapisfromsiffproject.dto.request.SearchByBothRequest;
import com.example.callingapisfromsiffproject.dto.response.AddResponse;
import com.example.callingapisfromsiffproject.dto.response.DeleteResponse;
import com.example.callingapisfromsiffproject.dto.response.SearchResponse;
import com.example.callingapisfromsiffproject.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("delete")
    public DeleteResponse delete(@RequestBody DeleteRequest deleteRequest)
    {
      return  contactService.deleteContact(deleteRequest);
    }
}