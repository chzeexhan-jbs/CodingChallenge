package com.optymyze.coding.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {

    @Autowired
    private RestTemplate restTemplate;

    public <T> T exchange(String url, HttpMethod httpMethod, HttpHeaders httpHeaders, Class<T> responseClass) {
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, new HttpEntity<>(httpHeaders), responseClass);
            if (responseEntity.getStatusCode().equals(HttpStatus.OK))
                return responseEntity.getBody();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getLocalizedMessage());
        }
        return null;
    }
}
