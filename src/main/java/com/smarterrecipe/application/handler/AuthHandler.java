package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.repository.UserRepository;
import com.smarterrecipe.domain.model.enums.Role;
import com.smarterrecipe.domain.service.JwtService;
import com.smarterrecipe.presentation.dto.auth.AuthResponse;
import com.smarterrecipe.presentation.dto.auth.LoginRequest;
import com.smarterrecipe.presentation.dto.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthHandler {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername()) || userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Username atau Email sudah terdaftar");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.HOME_COOK);

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user.getUsername());
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String jwtToken = jwtService.generateToken(user.getUsername());

        return AuthResponse.builder().token(jwtToken).build();
    }
}