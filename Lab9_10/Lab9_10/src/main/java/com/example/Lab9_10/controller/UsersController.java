package com.example.Lab9_10.controller;

import com.example.Lab9_10.dto.UserDTO;
import com.example.Lab9_10.jwt.JwtUtil;
import com.example.Lab9_10.model.Users;
import com.example.Lab9_10.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        Users findUser  = (Users) userService.loadUserByUsername(user.getUsername());

        String jwtToken = jwtUtil.generateToken(findUser);

        UserDTO userDTO = new UserDTO();
        userDTO.setToken(jwtToken);
        userDTO.setUsername(findUser.getUsername());
        userDTO.setPassword(findUser.getFirstName());
        userDTO.setLastName(findUser.getLastName());

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user) {

        Users newUser = Users.builder()
                .password(passwordEncoder.encode(user.getPassword()))
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .enabled(true)
                .build();

        return  new ResponseEntity<>(userService.saveUser(newUser), HttpStatus.CREATED);

    }
}
