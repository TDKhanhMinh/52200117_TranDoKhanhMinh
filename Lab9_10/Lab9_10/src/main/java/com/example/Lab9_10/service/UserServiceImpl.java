package com.example.Lab9_10.service;

import com.example.Lab9_10.model.Users;
import com.example.Lab9_10.repository.UsesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsesRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("Couldn't find user'"));
    }

    @Override
    public Users saveUser(Users newUser) {
        if (userRepo.findByUsername(newUser.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        return userRepo.save(newUser);
    }
}
