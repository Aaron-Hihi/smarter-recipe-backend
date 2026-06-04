package com.smarterrecipe.application.handler;

import com.smarterrecipe.domain.model.UserModel;
import com.smarterrecipe.application.security.JwtService;
import com.smarterrecipe.domain.service.UserService;
import com.smarterrecipe.application.dto.AuthResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthHandler {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResult register(String name, String email, String password) {
        UserModel user = userService.registerUser(
                name,
                email,
                passwordEncoder.encode(password)
        );

        String jwtToken = jwtService.generateToken(user.getUsername());
        return AuthResult.builder().token(jwtToken).user(user).build();
    }

    public AuthResult login(String email, String password) {
        // Find user by email first to get username for auth
        UserModel user = userService.getByEmail(email);

        if (Boolean.TRUE.equals(user.getIsBanned())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account is banned");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password)
        );

        String jwtToken = jwtService.generateToken(user.getUsername());

        return AuthResult.builder().token(jwtToken).user(user).build();
    }
}