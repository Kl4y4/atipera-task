package com.atipera.task.controller;

import com.atipera.task.service.APIConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RepositoryInfoController {

    private final APIConsumerService apiService;
    private final ObjectMapper objectMapper;

    @GetMapping("/")
    public String hello() {
        return apiService.GetHello();
    }

    @GetMapping("/repos")
    public String GetRepos(@RequestParam String username, @RequestHeader("Accept") String acceptHeader) throws JsonProcessingException {

        if (!CheckHeaders(acceptHeader)) {
            System.out.println("406");
//            return objectMapper.writeValueAsString(new ErrorSchema(406, "Not Acceptable - Accept header does not contain 'application/json'"));
        }

        return apiService.GetRepos(username);
    }

    private boolean CheckHeaders(String acceptHeader) {
        return acceptHeader.contains("application/json");
    }

}
