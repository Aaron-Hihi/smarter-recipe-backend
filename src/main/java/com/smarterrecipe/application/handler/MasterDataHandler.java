package com.smarterrecipe.application.handler;

import com.smarterrecipe.data.entity.Equipment;
import com.smarterrecipe.data.entity.Ingredient;
import com.smarterrecipe.data.repository.ingredient.EquipmentJpaRepository;
import com.smarterrecipe.data.repository.ingredient.IngredientJpaRepository;
import com.smarterrecipe.presentation.dto.masterdata.MasterDataItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MasterDataHandler {

    private final IngredientJpaRepository ingredientJpaRepository;
    private final EquipmentJpaRepository equipmentJpaRepository;

    @Transactional(readOnly = true)
    public List<MasterDataItemResponse> getIngredients() {
        return ingredientJpaRepository.findAll().stream()
                .map(item -> new MasterDataItemResponse(item.getId(), item.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public MasterDataItemResponse createIngredient(String name) {
        ingredientJpaRepository.findByNameIgnoreCase(name)
                .ifPresent(item -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Ingredient already exists");
                });
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        Ingredient saved = ingredientJpaRepository.save(ingredient);
        return new MasterDataItemResponse(saved.getId(), saved.getName());
    }

    @Transactional
    public MasterDataItemResponse updateIngredient(Long id, String name) {
        Ingredient ingredient = ingredientJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found"));
        ingredient.setName(name);
        Ingredient saved = ingredientJpaRepository.save(ingredient);
        return new MasterDataItemResponse(saved.getId(), saved.getName());
    }

    @Transactional
    public void deleteIngredient(Long id) {
        if (!ingredientJpaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
        }
        ingredientJpaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<MasterDataItemResponse> getEquipment() {
        return equipmentJpaRepository.findAll().stream()
                .map(item -> new MasterDataItemResponse(item.getId(), item.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public MasterDataItemResponse createEquipment(String name) {
        equipmentJpaRepository.findByNameIgnoreCase(name)
                .ifPresent(item -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Equipment already exists");
                });
        Equipment equipment = new Equipment();
        equipment.setName(name);
        Equipment saved = equipmentJpaRepository.save(equipment);
        return new MasterDataItemResponse(saved.getId(), saved.getName());
    }

    @Transactional
    public MasterDataItemResponse updateEquipment(Long id, String name) {
        Equipment equipment = equipmentJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found"));
        equipment.setName(name);
        Equipment saved = equipmentJpaRepository.save(equipment);
        return new MasterDataItemResponse(saved.getId(), saved.getName());
    }

    @Transactional
    public void deleteEquipment(Long id) {
        if (!equipmentJpaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found");
        }
        equipmentJpaRepository.deleteById(id);
    }
}
