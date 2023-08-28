package com.atipera.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RepoExternalObj(Long id, String name, String full_name, Owner owner, boolean fork) {
}
