package com.atipera.task.controller;

import com.atipera.task.service.APIConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RepositoryInfoController {

    private final APIConsumerService apiService;

    @GetMapping("/repos")
    public ResponseEntity<String> getRepos(@RequestParam String username, @RequestHeader("Accept") String acceptHeader) {

        if (!checkHeaders(acceptHeader)) {
            return ResponseEntity.status(406).body("Not Acceptable - Accept header does not contain 'application/json'");
        }

        try {
            return ResponseEntity.status(200).body(apiService.getRepos(username));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Not Found - This user does not exist");
        }

    }

    private boolean checkHeaders(String acceptHeader) {
        return acceptHeader.toLowerCase().contains("application/json");
    }

}
