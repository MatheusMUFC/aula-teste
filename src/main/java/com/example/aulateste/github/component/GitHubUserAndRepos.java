package com.example.aulateste.github.component;

import com.example.aulateste.github.entity.GitHubRepository;
import com.example.aulateste.github.entity.GitHubUser;

public class GitHubUserAndRepos {
    private final GitHubUser user;
    private final GitHubRepository[] repositories;

    public GitHubUserAndRepos(GitHubUser user, GitHubRepository[] repositories) {
        this.user = user;
        this.repositories = repositories;
    }

    public GitHubUser getUser() {
        return user;
    }

    public GitHubRepository[] getRepositories() {
        return repositories;
    }
}
