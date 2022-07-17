package com.example.springinactionmessagingjms.repository;

import com.example.springinactionmessagingjms.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
