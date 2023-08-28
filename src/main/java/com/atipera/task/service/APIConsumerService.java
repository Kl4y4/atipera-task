package com.atipera.task.service;

import com.atipera.task.model.RepoResponseObj;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@Scope
@RequiredArgsConstructor
public class APIConsumerService {

    private final RestTemplate restTemplate;

    public String GetHello() {
        return "Hello world!";
    }

    public String GetRepos() {

        RepoResponseObj[] response = restTemplate.getForObject(
                "https://api.github.com/users/kl4y4/repos", RepoResponseObj[].class
        );

        response = Arrays.stream(response).filter(res -> !res.fork()).toArray(RepoResponseObj[]::new);

        StringBuilder sb = new StringBuilder();
        for (RepoResponseObj repo : response) {
            sb.append(repo.name())
                    .append(" ")
                    .append(repo.owner().login());
        }

        return sb.toString();
    }

}
