package com.smarterrecipe.domain.service;

import com.smarterrecipe.domain.model.UserModel;
import com.smarterrecipe.domain.model.enums.Role;
import com.smarterrecipe.domain.repository.UserRepository;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserModel registerUser(String username, String email, String encodedPassword) {
        if (repository.existsByUsername(username) || repository.existsByEmail(email)) {
            throw new IllegalArgumentException("Username or email unavailable");
        }
        UserModel user = UserModel.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .role(Role.HOME_COOK)
                .build();
        return repository.save(user);
    }

    public UserModel getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public UserModel getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public List<UserModel> getAllUsers() {
        return repository.getAll();
    }

    public UserModel updateUser(UserModel model) {
        return repository.save(model);
    }
}