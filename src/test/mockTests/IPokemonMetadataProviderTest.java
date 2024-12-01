package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;

public class IPokemonMetadataProviderTest {

    IPokemonMetadataProvider pokemonMetadataProvider;


    @Before
    public void setUp() {
        pokemonMetadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
    }

    // Unit Test
    @Test
    public void testGetPokemonMetadataMock() throws PokedexException {
        // Création d'un objet PokemonMetadata fictif
        PokemonMetadata metadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);

        // Configuration du mock pour retourner l'objet fictif
        when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(metadata);

        // Appel de la méthode à tester
        PokemonMetadata result = pokemonMetadataProvider.getPokemonMetadata(0);

        // Vérification des résultats
        assertNotNull(result);
        assertEquals(0, result.getIndex());
        assertEquals("Bulbizarre", result.getName());
        assertEquals(126, result.getAttack());
        assertEquals(126, result.getDefense());
        assertEquals(90, result.getStamina());
    }


}
