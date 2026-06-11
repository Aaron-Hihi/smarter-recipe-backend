package com.smarterrecipe.common.config;

import com.smarterrecipe.data.entity.User;
import com.smarterrecipe.data.repository.user.UserJpaRepository;
import com.smarterrecipe.domain.model.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AdminSeeder {

    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedAdmin() {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setFullName("System Administrator");
                admin.setEmail("admin@smarterrecipe.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                admin.setIsVerified(true);
                userRepository.save(admin);
                log.info("Admin user seeded successfully. Username: admin, Password: admin123");
            } else {
                log.info("Admin user already exists. Skipping seeder.");
            }
        };
    }
}
