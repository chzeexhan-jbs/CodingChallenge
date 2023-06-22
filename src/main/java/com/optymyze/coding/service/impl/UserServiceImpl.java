package com.optymyze.coding.service.impl;

import com.optymyze.coding.dto.GithubRepoResponseDTO;
import com.optymyze.coding.dto.UserDTO;
import com.optymyze.coding.model.User;
import com.optymyze.coding.repository.UserRepository;
import com.optymyze.coding.service.GithubService;
import com.optymyze.coding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private GithubService githubService;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User dtoToEntity(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getFirstName(), userDTO.getSurName(), userDTO.getPosition(), userDTO.getGitHubProfileUrl());
    }

    private UserDTO entityToDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getSurName(), user.getPosition(), user.getGitHubProfileUrl());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user -> entityToDTO(user)).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(long id) {
        return userRepository.findById(id).map(user -> new UserDTO(user.getId(), user.getFirstName(), user.getSurName(), user.getPosition(), user.getGitHubProfileUrl()));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User _user = userRepository.save(dtoToEntity(userDTO));
        return entityToDTO(_user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User _user = userRepository.save(dtoToEntity(userDTO));
        return entityToDTO(_user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<GithubRepoResponseDTO> getUserGithubReposByUserId(long id) {
        Optional<String> userGitHubProfileUrl = userRepository.findGitHubProfileUrlById(id);
        if (userGitHubProfileUrl.isPresent() && !userGitHubProfileUrl.get().isBlank())
            return githubService.getGithubRepos(userGitHubProfileUrl.get());
        return new ArrayList<>();
    }
}
