package com.smarterrecipe.data.repository.dietaryingredientrestriction;

import com.smarterrecipe.data.entity.DietaryIngredientRestriction;
import com.smarterrecipe.domain.repository.DietaryRestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DietaryIngredientRestrictionRepositoryImpl implements DietaryRestrictionRepository {
    private final DietaryIngredientRestrictionJpaRepository jpaRepository;

    @Override
    public List<Long> findViolatingIngredients(List<Long> tagIds, List<Long> ingredientIds) {
        List<DietaryIngredientRestriction> violations = jpaRepository.findViolations(tagIds, ingredientIds);
        return violations.stream()
                .map(v -> v.getIngredient().getId())
                .collect(Collectors.toList());
    }
}