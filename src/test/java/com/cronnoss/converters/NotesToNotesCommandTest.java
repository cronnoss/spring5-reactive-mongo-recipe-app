package com.cronnoss.converters;

import com.cronnoss.commands.NotesCommand;
import com.cronnoss.domain.Notes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotesToNotesCommandTest {

    public static final String ID_VALUE = "1";
    public static final String RECIPE_NOTES = "Notes";
    NotesToNotesCommand converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void convert() throws Exception {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        Assertions.assertEquals(ID_VALUE, notesCommand.getId());
        Assertions.assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }

    @Test
    public void testNull() throws Exception {
        Assertions.assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        Assertions.assertNotNull(converter.convert(new Notes()));
    }
}