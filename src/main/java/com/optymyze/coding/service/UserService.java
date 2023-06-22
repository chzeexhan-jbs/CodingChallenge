package com.optymyze.coding.service;

import com.optymyze.coding.dto.GithubRepoResponseDTO;
import com.optymyze.coding.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(long id);

    List<GithubRepoResponseDTO> getUserGithubReposByUserId(long id);
}
