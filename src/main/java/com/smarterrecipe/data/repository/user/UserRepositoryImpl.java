package com.smarterrecipe.data.repository.user;

import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.domain.model.UserModel;
import com.smarterrecipe.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public UserModel save(UserModel model) {
        User entity = model.getId() != null ? jpaRepository.findById(model.getId()).orElse(new User()) : new User();
        entity.setUsername(model.getUsername());
        entity.setFullName(model.getFullName());
        entity.setBio(model.getBio());
        entity.setEmail(model.getEmail());
        entity.setPassword(model.getPassword());
        entity.setRole(model.getRole());
        entity.setProfilePictureUrl(model.getProfilePictureUrl());
        if (model.getIsBanned() != null) entity.setIsBanned(model.getIsBanned());
        if (model.getIsVerified() != null) entity.setIsVerified(model.getIsVerified());
        entity = jpaRepository.save(entity);
        return toModel(entity);
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return jpaRepository.findByUsername(username).map(this::toModel);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(this::toModel);
    }

    @Override
    public List<UserModel> getAll() {
        return jpaRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }

    private UserModel toModel(User entity) {
        return UserModel.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .fullName(entity.getFullName())
                .bio(entity.getBio())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole())
                .profilePictureUrl(entity.getProfilePictureUrl())
                .isBanned(entity.getIsBanned())
                .isVerified(entity.getIsVerified())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}