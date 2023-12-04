package com.example.aulateste.github.controller;

import com.example.aulateste.github.component.GitHubUserAndRepos;
import com.example.aulateste.github.entity.GitHubRepository;
import com.example.aulateste.github.entity.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/github")
public class GitHubController {

    private final RestTemplate restTemplate;
    private static final String GITHUB_API_URL = "https://api.github.com/users/";

    @Autowired
    public GitHubController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<GitHubUserAndRepos> getUserAndRepos(@PathVariable String username) {
        try {
            // Obter dados públicos do usuário
            String userUrl = GITHUB_API_URL + username;
            GitHubUser userData = restTemplate.getForObject(userUrl, GitHubUser.class);

            // Obter repositórios públicos do usuário
            String reposUrl = GITHUB_API_URL + username + "/repos";
            GitHubRepository[] reposData = restTemplate.getForObject(reposUrl, GitHubRepository[].class);

            GitHubUserAndRepos result = new GitHubUserAndRepos(userData, reposData);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
