package com.smarterrecipe.domain.repository;

import com.smarterrecipe.domain.model.UserModel;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    UserModel save(UserModel user);
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);
    List<UserModel> getAll();
}