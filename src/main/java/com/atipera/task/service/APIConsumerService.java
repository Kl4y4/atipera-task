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

import java.util.ArrayList;
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

        response = Arrays.stream(response).filter(res -> !res.fork()).toArray(RepoExternalObj[]::new);

        List<ResponseObj> responseObjs = new ArrayList<>();
        for (RepoExternalObj repo : response) {
            ResponseObj obj = new ResponseObj(
                    repo.name(), repo.owner().login(), new Branch[]{}
            );
            responseObjs.add(obj);
        }

        return objectMapper.writeValueAsString(responseObjs);
    }

}
