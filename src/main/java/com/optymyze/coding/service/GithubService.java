package com.optymyze.coding.service;

import com.optymyze.coding.dto.GithubRepoResponseDTO;

import java.util.List;

public interface GithubService {
    List<GithubRepoResponseDTO> getGithubRepos(String githubProfileUrl);
}
