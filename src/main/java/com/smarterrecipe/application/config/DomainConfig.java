package com.smarterrecipe.application.config;

import com.smarterrecipe.domain.engine.DietaryRuleEngine;
import com.smarterrecipe.domain.engine.MatchingEngine;
import com.smarterrecipe.domain.repository.*;
import com.smarterrecipe.domain.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public DietaryRuleEngine dietaryRuleEngine(DietaryRestrictionRepository repository) {
        return new DietaryRuleEngine(repository);
    }

    @Bean
    public MatchingEngine matchingEngine() {
        return new MatchingEngine();
    }

    @Bean
    public DietaryTagService dietaryTagService(DietaryTagRepository repository) {
        return new DietaryTagService(repository);
    }

    @Bean
    public IngredientService ingredientService(IngredientRepository repository) {
        return new IngredientService(repository);
    }

    @Bean
    public PantryService pantryService(PantryRepository repository) {
        return new PantryService(repository);
    }

    @Bean
    public RecipeService recipeService(RecipeRepository repository) {
        return new RecipeService(repository);
    }

    @Bean
    public UserService userService(UserRepository repository) {
        return new UserService(repository);
    }
}
