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
import java.util.ListIterator;

@Service
@Scope
@RequiredArgsConstructor
public class APIConsumerService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public String getRepos(String username) throws JsonProcessingException {

        RepoExternalObj[] response = restTemplate.getForObject(
                "https://api.github.com/users/" + username + "/repos", RepoExternalObj[].class
        );

        List<ResponseObj> responseObjs = processResponse(response);

        return objectMapper.writeValueAsString(responseObjs);
    }

    private List<ResponseObj> processResponse(RepoExternalObj[] response) {

        response = Arrays.stream(response).filter(res -> !res.fork()).toArray(RepoExternalObj[]::new);

        List<Branch[]> branches = fetchBranches(Arrays.stream(response).toList());
        ListIterator<Branch[]> branchListIterator = branches.listIterator();

        return Arrays.stream(response).map(res -> new ResponseObj(res.name(), res.owner().login(), branchListIterator.next())).toList();
    }

    private List<Branch[]> fetchBranches(List<RepoExternalObj> response) {

        List<Branch[]> branches = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (RepoExternalObj respObj : response) {

            sb.append("https://api.github.com/repos/")
                    .append(respObj.owner().login())
                    .append("/")
                    .append(respObj.name())
                    .append("/branches");

            Branch[] branchesToAdd = restTemplate.getForObject(
                    sb.toString(), Branch[].class
            );

            branches.add(branchesToAdd);

            sb.setLength(0);

        }

        return branches;
    }

}
