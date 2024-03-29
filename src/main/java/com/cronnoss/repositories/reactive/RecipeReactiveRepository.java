package com.cronnoss.repositories.reactive;

import com.cronnoss.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String>{
}
