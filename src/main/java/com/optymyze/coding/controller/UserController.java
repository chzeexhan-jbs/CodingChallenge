package com.optymyze.coding.controller;

import com.optymyze.coding.dto.GithubRepoResponseDTO;
import com.optymyze.coding.dto.UserDTO;
import com.optymyze.coding.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        if (userDTO.getId() != null)
            throw new RuntimeException("Invalid Request");
        try {
            return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        logger.debug("Update User : called");
        if (userDTO.getId() == null)
            throw new RuntimeException("Invalid Request");
        try {
            return userService.getUserById(userDTO.getId())
                    .map(savedUser -> {
                        return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.OK);
                    })
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(required = false) String title) {
        logger.debug("Get All User : called");
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") long id) {
        logger.debug("Get User By Id : called");
        Optional<UserDTO> userData = userService.getUserById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        logger.debug("Delete User By Id : called");
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/github/repos/{userId}")
    public ResponseEntity<List<GithubRepoResponseDTO>> getUserGithubReposByUserId(@PathVariable("userId") long userId) {
        logger.debug("Get User Github Repos By User Id : called");
        try {
            return new ResponseEntity<>(userService.getUserGithubReposByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
