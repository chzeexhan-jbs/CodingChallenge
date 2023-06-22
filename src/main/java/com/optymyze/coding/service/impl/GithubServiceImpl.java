package com.optymyze.coding.service.impl;

import com.optymyze.coding.client.RestClient;
import com.optymyze.coding.constant.Constant;
import com.optymyze.coding.dto.GithubRepoDetailsDTO;
import com.optymyze.coding.dto.GithubRepoResponseDTO;
import com.optymyze.coding.service.GithubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class GithubServiceImpl implements GithubService {

    private static final Logger logger = LoggerFactory.getLogger(GithubServiceImpl.class);
    @Autowired
    private RestClient restClient;
    @Value("${github.auth.token}")
    private String GITHUB_AUTH_TOKEN;

    @Override
    public List<GithubRepoResponseDTO> getGithubRepos(String githubProfileUrl) {
        String githubUsername = githubProfileUrl.split("github.com/")[1];
        List<GithubRepoResponseDTO> response = new ArrayList<GithubRepoResponseDTO>();

        try {
            List<GithubRepoDetailsDTO> githubRepos = getGitHubRepos(githubUsername);
            for (GithubRepoDetailsDTO githubRepoDetailsDTO : githubRepos) {
                response.add(new GithubRepoResponseDTO(githubRepoDetailsDTO.getName(), githubRepoDetailsDTO.getFullName(), getGithubRepoLanguages(githubRepoDetailsDTO.getLanguagesUrl())));
            }
        } catch (Exception ex) {
            if (ex.getMessage().contains("API rate limit exceeded"))
                logger.error("API rate limit exceeded. Please update the Github Access Token.");
        }

        return response;
    }

    private List<GithubRepoDetailsDTO> getGitHubRepos(String githubUsername) {
        String githubProfileUrl = Constant.GITHUB_REPO_URL.replace("{username}", githubUsername);
        GithubRepoDetailsDTO[] responseBody = restClient.exchange(githubProfileUrl, HttpMethod.GET, getGithubHttpHeader(), GithubRepoDetailsDTO[].class);
        return Arrays.asList(responseBody);
    }

    private Set<String> getGithubRepoLanguages(String githubRepoLanguageUrl) {
        Map responseBody = restClient.exchange(githubRepoLanguageUrl, HttpMethod.GET, getGithubHttpHeader(), Map.class);
        return responseBody.keySet();
    }

    private HttpHeaders getGithubHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (StringUtils.hasText(GITHUB_AUTH_TOKEN))
            httpHeaders.setBearerAuth(GITHUB_AUTH_TOKEN);
        return httpHeaders;
    }
}
