package com.smarterrecipe.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "follower")
    private List<UserFollows> following;

    @OneToMany(mappedBy = "followee")
    private List<UserFollows> followers;

    @OneToMany(mappedBy = "user")
    private List<UserDietaryTag> userDietaryTags;

    @OneToMany(mappedBy = "user")
    private List<UserPantry> userPantries;

    @OneToMany(mappedBy = "creator")
    private List<Recipe> recipes;

    @OneToMany(mappedBy = "owner")
    private List<RecipeList> recipeLists;
}