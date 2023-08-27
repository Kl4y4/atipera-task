package com.atipera.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepositoryInfoController {

    @GetMapping("/")
    public String hello() {
        return "Hello world";
    }

}
