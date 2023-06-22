package com.optymyze.coding.repository;

import com.optymyze.coding.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u.gitHubProfileUrl from User u where u.id = :id")
    Optional<String> findGitHubProfileUrlById(long id);
}
