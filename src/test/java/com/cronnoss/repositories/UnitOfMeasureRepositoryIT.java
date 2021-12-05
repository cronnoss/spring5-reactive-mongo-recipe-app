package com.cronnoss.repositories;

import com.cronnoss.bootstrap.RecipeBootstrap;
import com.cronnoss.domain.UnitOfMeasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() throws Exception {

        recipeRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();
        categoryRepository.deleteAll();

        RecipeBootstrap recipeBootstrap = new RecipeBootstrap(categoryRepository, recipeRepository, unitOfMeasureRepository);

        recipeBootstrap.onApplicationEvent(null);
    }


    @Test
    public void findByDescription() throws Exception {

        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        Assertions.assertEquals("Teaspoon", uomOptional.get().getDescription());
    }


    @Test
    public void findByDescriptionCup() throws Exception {

        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");

        Assertions.assertEquals("Cup", uomOptional.get().getDescription());
    }

}