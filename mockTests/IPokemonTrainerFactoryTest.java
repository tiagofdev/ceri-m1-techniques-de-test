package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;

public class IPokemonTrainerFactoryTest {

    IPokemonTrainerFactory trainerFactory;

    IPokedexFactory pokedexFactory;
    IPokedex pokedex;

    @Before
    public void setUp() {
        trainerFactory = Mockito.mock(IPokemonTrainerFactory.class);
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        pokedex = Mockito.mock(IPokedex.class);
    }

    @Test
    public void testCreateTrainer() throws PokedexException {
        PokemonTrainer trainer = new PokemonTrainer("playerx", Team.VALOR, pokedex);

        // Define expected behavior
        when(trainerFactory.createTrainer("playerx", Team.VALOR, pokedexFactory)).thenReturn(trainer);

        PokemonTrainer result = trainerFactory.createTrainer("playerx", Team.VALOR, pokedexFactory);

        // Vérification des résultats
        assertNotNull(result);
        assertEquals("playerx", result.getName());
        assertEquals(Team.VALOR, result.getTeam());
        assertEquals(pokedex, result.getPokedex());

    }

}
