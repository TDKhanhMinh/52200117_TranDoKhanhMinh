package com.example.Lab9_10.repository;

import com.example.Lab9_10.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsesRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
}
