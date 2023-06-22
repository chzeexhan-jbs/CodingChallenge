package com.optymyze.coding;

import com.optymyze.coding.dto.GithubRepoResponseDTO;
import com.optymyze.coding.dto.UserDTO;
import com.optymyze.coding.service.impl.UserServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class CodingApplicationTests {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    @Order(1)
    void createUser() {
        UserDTO userDTO = userServiceImpl.createUser(new UserDTO("Zeeshan", "Ch", "SE", "https://github.com/givanthak"));
        Assert.isInstanceOf(UserDTO.class, userDTO);
    }

    @Test
    @Order(2)
    void updateUser() {
        UserDTO userDTO = userServiceImpl.updateUser(new UserDTO(Long.valueOf(1), "Zeeshan", "Ch", "SSE", "https://github.com/givanthak"));
        Assert.isInstanceOf(UserDTO.class, userDTO);
    }

    @Test
    @Order(3)
    void getUserById() {
        Optional<UserDTO> userDTO = userServiceImpl.getUserById(1);
        Assert.isTrue(userDTO.isPresent());
        Assert.isTrue(userDTO.get().getPosition().equals("SSE"));
    }

    @Test
    @Order(4)
    void getAllUsers() {
        List<UserDTO> userDTOList = userServiceImpl.getAllUsers();
        Assert.isTrue(!userDTOList.isEmpty());
    }

    @Test
    @Order(5)
    void getUserGithubRepoDetails() {
        List<GithubRepoResponseDTO> githubRepoResponseDTOList = userServiceImpl.getUserGithubReposByUserId(1);
        Assert.isTrue(!githubRepoResponseDTOList.isEmpty());
        Assert.isTrue(githubRepoResponseDTOList.get(1).getName().equals("announcementwebapp"));
        Assert.isTrue(githubRepoResponseDTOList.get(1).getLanguages().contains("Java"));
    }

    @Test
    @Order(6)
    void deleteUser() {
        userServiceImpl.deleteUser(1);
        Optional<UserDTO> userDTO = userServiceImpl.getUserById(1);
        Assert.isTrue(userDTO.isEmpty());
    }

}
