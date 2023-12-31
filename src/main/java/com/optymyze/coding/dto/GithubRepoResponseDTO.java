package com.optymyze.coding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubRepoResponseDTO {
    private String name;
    private String fullName;
    private Set<String> languages;
}
