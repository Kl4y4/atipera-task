package com.atipera.task.controller;

import com.atipera.task.service.APIConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RepositoryInfoController {

    private final APIConsumerService apiService;

    @GetMapping("/")
    public String hello() {
        return apiService.GetHello();
    }

    @GetMapping("/repos")
    public String GetRepos() {
        return apiService.GetRepos();
    }

}
