package com.atipera.task.model;

public record RepoResponseObj(Long id, String name, String full_name, Owner owner, boolean fork) {
}
