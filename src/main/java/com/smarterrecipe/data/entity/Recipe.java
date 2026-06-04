package com.smarterrecipe.data.entity;

import com.smarterrecipe.domain.model.enums.RecipeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NamedEntityGraph(
        name = "Recipe.deepFetch",
        attributeNodes = {
                @NamedAttributeNode(value = "recipeIngredients", subgraph = "recipeIngredients.subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "recipeIngredients.subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("ingredient"),
                                @NamedAttributeNode(value = "substitutions", subgraph = "substitutions.subgraph")
                        }
                ),
                @NamedSubgraph(
                        name = "substitutions.subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("substituteIngredient")
                        }
                )
        }
)
@Table(name = "recipes")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(nullable = false)
    private String title;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "serving_size")
    private Integer servingSize;

    @Column(name = "preparation_time")
    private Integer preparationTime;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RecipeStatus status = RecipeStatus.DRAFTED;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "total_reviews")
    private Integer totalReviews;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecipeIngredient> recipeIngredients = new LinkedHashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecipeStep> recipeSteps = new LinkedHashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeDietaryTag> recipeDietaryTags = new LinkedHashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<RecipePantry> recipePantries = new LinkedHashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecipeEquipment> recipeEquipments = new LinkedHashSet<>();
}