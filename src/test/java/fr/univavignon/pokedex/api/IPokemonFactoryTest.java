package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class IPokemonFactoryTest {

    IPokemonFactory pokemonFactory;

    @Before
    public void setUp() {
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
    }

    @Test
    public void testCreatePokemon() throws PokedexException {
        // Création d'un objet PokemonFactory fictif
        Pokemon pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, .56);

        // Configuration du mock pour retourner l'objet fictif
        when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(pokemon);

        // Appel de la méthode à tester
        Pokemon result = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);

        // Vérification des résultats
        assertNotNull(result);
        assertEquals(613, result.getCp());
        assertEquals(64, result.getHp());
        assertEquals(4000, result.getDust());
        assertEquals(4, result.getCandy());

    }


}
