package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IPokedexTest {

    IPokedex pokedex;
    Pokemon pokemon01;
    Pokemon pokemon02;

    @Before
    public void setUp() {
        pokedex = Mockito.mock(IPokedex.class);
        pokemon01 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, .56);
        pokemon02 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0);
    }

    @Test
    public void testAddPokemon() {
        // Define behavior for addPokemon
        when(pokedex.addPokemon(pokemon01)).thenReturn(1);

        // Call addPokemon and verify the result
        int result = pokedex.addPokemon(pokemon01);
        assertEquals(1, result);

        // Verify that addPokemon was called with the specific Pokemon
        verify(pokedex, times(1)).addPokemon(pokemon01);
    }

    @Test
    public void testSize() {
        // Define behavior for size()
        when(pokedex.size()).thenReturn(2);

        pokedex.addPokemon(pokemon01);
        pokedex.addPokemon(pokemon02);
        // Call addPokemon and verify the result
        int result = pokedex.size();
        assertEquals(2, result);

        // Verify that addPokemon was called with the specific Pokemon
        verify(pokedex, times(1)).size();
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        // Define behavior for getPokemon
        when(pokedex.getPokemon(0)).thenReturn(pokemon01);

        pokedex.addPokemon(pokemon01);
        // Call getPokemon and verify the result
        Pokemon result = pokedex.getPokemon(0);
        assertNotNull(result);
        assertEquals("Bulbizarre", result.getName());

        // Verify that getPokemon was called with the expected argument
        verify(pokedex, times(1)).getPokemon(0);
    }


    @Test
    public void testGetPokemons() {
        // Define behavior for getPokemons
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(pokemon01);
        pokemonList.add(pokemon02);
        when(pokedex.getPokemons()).thenReturn(pokemonList);

        // Call getPokemons and verify the result
        List<Pokemon> result = pokedex.getPokemons();
        assertEquals(2, result.size());
        assertEquals("Bulbizarre", result.get(0).getName());
        assertEquals("Aquali", result.get(1).getName());

        // Verify that getPokemons was called once
        verify(pokedex, times(1)).getPokemons();
    }

    @Test
    public void testGetPokemonsSorted() {
        // Define behavior for getPokemons with sorting
        List<Pokemon> pokemonListDescending = new ArrayList<>();
        pokemonListDescending.add(pokemon02);
        pokemonListDescending.add(pokemon01);
        when(pokedex.getPokemons(any(Comparator.class))).thenReturn(pokemonListDescending);

        // Call getPokemons with a comparator and verify the result
        List<Pokemon> result = pokedex.getPokemons(PokemonComparators.NAME);
        assertEquals(2, result.size());
        assertEquals("Aquali", result.get(0).getName());
        assertEquals("Bulbizarre", result.get(1).getName());

        // Verify that getPokemons was called with a comparator
        verify(pokedex, times(1)).getPokemons(any(Comparator.class));
    }


}

