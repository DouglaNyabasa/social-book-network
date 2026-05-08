package com.doug.socialbooknetwork.repository;

import com.doug.socialbooknetwork.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> existsByEmail(String email);

}
