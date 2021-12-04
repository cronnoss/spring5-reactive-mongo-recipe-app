package com.cronnoss.converters;

import com.cronnoss.commands.IngredientCommand;
import com.cronnoss.commands.UnitOfMeasureCommand;
import com.cronnoss.domain.Ingredient;
import com.cronnoss.domain.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class IngredientCommandToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final String ID_VALUE = "1";
    public static final String UOM_ID = "2";

    IngredientCommandToIngredient converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() throws Exception {
        Assertions.assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        Assertions.assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUom(unitOfMeasureCommand);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        Assertions.assertNotNull(ingredient);
        Assertions.assertNotNull(ingredient.getUom());
        Assertions.assertEquals(ID_VALUE, ingredient.getId());
        Assertions.assertEquals(AMOUNT, ingredient.getAmount());
        Assertions.assertEquals(DESCRIPTION, ingredient.getDescription());
        Assertions.assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();


        //when
        Ingredient ingredient = converter.convert(command);

        //then
        Assertions.assertNotNull(ingredient);
        Assertions.assertNull(ingredient.getUom());
        Assertions.assertEquals(ID_VALUE, ingredient.getId());
        Assertions.assertEquals(AMOUNT, ingredient.getAmount());
        Assertions.assertEquals(DESCRIPTION, ingredient.getDescription());
    }

}