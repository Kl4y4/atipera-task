package com.atipera.task.service;

import com.atipera.task.model.Branch;
import com.atipera.task.model.RepoExternalObj;
import com.atipera.task.model.ResponseObj;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Scope
@RequiredArgsConstructor
public class APIConsumerService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public String GetHello() {
        return "Hello world!";
    }

    public String GetRepos(String username) throws JsonProcessingException {

        RepoExternalObj[] response = restTemplate.getForObject(
                "https://api.github.com/users/" + username + "/repos", RepoExternalObj[].class
        );

        List<ResponseObj> responseObjs = ProcessResponse(response);

        return objectMapper.writeValueAsString(responseObjs);
    }

    private List<ResponseObj> ProcessResponse(RepoExternalObj[] response) {

        response = Arrays.stream(response).filter(res -> !res.fork()).toArray(RepoExternalObj[]::new);

        List<ResponseObj> repos = Arrays.stream(response).map(res -> new ResponseObj(res.name(), res.owner().login(), new Branch[] {})).toList();

        return repos;
    }

}
