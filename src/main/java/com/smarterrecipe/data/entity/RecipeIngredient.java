package com.smarterrecipe.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "recipe_ingredients")
@Getter @Setter
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    private Double amount;
    private String unit;

    @OneToMany(mappedBy = "recipeIngredient")
    private Set<RecipeIngredientSubstitution> substitutions = new LinkedHashSet<>();
}