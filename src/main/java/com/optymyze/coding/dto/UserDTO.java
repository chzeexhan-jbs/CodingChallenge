package com.optymyze.coding.dto;

import com.optymyze.coding.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long id;
    private String firstName;
    private String surName;
    private String position;
    @Pattern(regexp = Constant.GITHUB_PROFILE_URL_REGEX, message = "Please Enter Valid Github Profile URL. e.g: https://github.com/username")
    private String gitHubProfileUrl;

    public UserDTO(String firstName, String surName, String position, String gitHubProfileUrl) {
        this.firstName = firstName;
        this.surName = surName;
        this.position = position;
        this.gitHubProfileUrl = gitHubProfileUrl;
    }
}
