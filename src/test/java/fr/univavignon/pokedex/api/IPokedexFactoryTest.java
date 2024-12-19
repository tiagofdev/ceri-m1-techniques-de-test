package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class IPokedexFactoryTest {

    @Mock
    IPokemonMetadataProvider metadataProvider;

    @Mock
    IPokemonFactory pokemonFactory;

    @Mock
    IPokedexFactory pokedexFactory;

    IPokedex pokedex;

    @Before
    public void setUp() {
        // Initialize mocks for the above interfaces
        MockitoAnnotations.initMocks(this);
        pokedex = Mockito.mock(IPokedex.class); // Mock an IPokedex instance
        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);
    }

    @Test
    public void testCreatePokedex() {
        // Call createPokedex method
        IPokedex result = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        // Verify that createPokedex returns a non-null IPokedex
        assertNotNull("Expected non-null IPokedex instance", result);

        // Optionally, check that createPokedex returns the expected mock
        assertEquals("Expected IPokedex mock instance", pokedex, result);

        // Verify that createPokedex was called with the expected arguments
        verify(pokedexFactory).createPokedex(metadataProvider, pokemonFactory);
    }


}
