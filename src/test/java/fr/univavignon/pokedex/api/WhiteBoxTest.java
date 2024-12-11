package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.imp.RocketFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class WhiteBoxTest {

    IPokemonMetadataProvider pokemonMetadataProvider;
    IPokemonFactory pokemonFactory;
    IPokemonFactory rocketFactory;  // RocketPokemonFactory
    PokedexFactory pokedexFactory;
    IPokedex pokedex;
    IPokedex rockdex;
    IPokemonTrainerFactory trainerFactory;

    Pokemon pokemon1;
    Pokemon pokemon2;
    Pokemon rockmon1;
    Pokemon rockmon2;

    @Before
    public void setUp() {
        pokemonMetadataProvider = new PokemonMetadataProvider();
        pokemonFactory = new PokemonFactory();
        rocketFactory = new RocketFactory();

        pokedexFactory = new PokedexFactory();
        pokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);
        pokemon1 = pokemonFactory.createPokemon(24, 281, 36, 1324, 2);
        pokemon2 = pokemonFactory.createPokemon(25, 207, 52, 5836, 5);

        rockdex = pokedexFactory.createPokedex(pokemonMetadataProvider, rocketFactory);
        rockmon1 = rocketFactory.createPokemon(24, 281, 36, 1324, 2);
        rockmon2 = rocketFactory.createPokemon(25, 207, 52, 5836, 5);

        trainerFactory = new TrainerFactory();
    }


    // Testing IPokemonFactory
    // custom Pokedex implements IPokedex which extends IPokemonFactory
    // Testing valid index
    @Test
    public void testingIPokemonFactoryValidIndex() {
        Pokemon result = pokedex.createPokemon(24, 281, 36, 1324, 2);
        assertNotNull(result);
        assertEquals(24, result.getIndex());
        assertEquals("Arbok", result.getName());
        assertEquals(13, result.getAttack());
        assertEquals(13, result.getDefense());
        assertEquals(12, result.getStamina());
        assertEquals(281, result.getCp());
        assertEquals(36, result.getHp());
        assertEquals(1324, result.getDust());
        assertEquals(2, result.getCandy());
    }

    // Testing boundary index and testing custom values other than base values
    @Test
    public void testingIPokemonFactoryBoundaries() {
        Pokemon result = pokedex.createPokemon(1, 613, 64, 4000, 4);
        assertNotNull(result);
        assertEquals(1, result.getIndex());
        assertEquals("Bulbasaur", result.getName());
        assertEquals(5, result.getAttack());
        assertEquals(5, result.getDefense());
        assertEquals(2, result.getStamina());
        assertEquals(613, result.getCp());
        assertEquals(64, result.getHp());
        assertEquals(4000, result.getDust());
        assertEquals(4, result.getCandy());

        Pokemon result2 = pokedex.createPokemon(151, 666, 50, 5000, 4);
        assertNotNull(result);
        assertEquals(151, result2.getIndex());
        assertEquals("Mew", result2.getName());
        assertEquals(1, result2.getAttack());
        assertEquals(5, result2.getDefense());
        assertEquals(4, result2.getStamina());
        assertEquals(666, result2.getCp());
        assertEquals(50, result2.getHp());
        assertEquals(5000, result2.getDust());
        assertEquals(4, result2.getCandy());
    }

    // Testing IPokemonFactory
    // custom Pokedex implements IPokedex which extends IPokemonFactory
    // Testing invalid index
    @Test
    public void testingIPokemonFactoryInvalidIndex() {
        Pokemon result = pokedex.createPokemon(200, 2171, 105, 8000, 15);
//        assertNull("Expected null for out of bounds index!", result);
        assertNull(result);
    }

    // Testing IPokemonMetadataProvider
    // custom Pokedex implements IPokedex which extends IPokemonMetadataProvider
    @Test
    public void testingIPokemonMetadataProvider() throws PokedexException {
        PokemonMetadata metadata;
        metadata = pokedex.getPokemonMetadata(2);
        assertNotNull(metadata);
        assertEquals(metadata.getAttack(), pokedex.getPokemonMetadata(2).getAttack());
        assertEquals(metadata.getDefense(), pokedex.getPokemonMetadata(2).getDefense());
        assertEquals(metadata.getStamina(), pokedex.getPokemonMetadata(2).getStamina());

    }

    // Testing IPokemonMetadataProvider
    // custom Pokedex implements IPokedex which extends IPokemonMetadataProvider
    // Testing Invalid index
    @Test(expected = PokedexException.class)
    public void testingIPokemonMetadataProviderInvalidIndex() throws PokedexException {
        pokedex.getPokemonMetadata(200);
    }

    // Testing IPokedexFactory
    @Test
    public void testPokedexFactoryInstancing() {
        PokedexFactory pokedexFactory = new PokedexFactory();
        IPokedex newPokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);
        assertNotNull(newPokedex);
        assertTrue(newPokedex instanceof Pokedex);
    }

    // Testing IPokedexFactory
    @Test(expected = IllegalArgumentException.class)
    public void testPokedexFactoryInstancingBothNull() {
        PokedexFactory pokedexFactory = new PokedexFactory();
        IPokedex newPokedex = pokedexFactory.createPokedex(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPokedexFactoryInstancingFirstNull() {
        PokedexFactory pokedexFactory = new PokedexFactory();
        IPokedex newPokedex = pokedexFactory.createPokedex(null, pokemonFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPokedexFactoryInstancingSecondNull() {
        PokedexFactory pokedexFactory = new PokedexFactory();
        IPokedex newPokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, null);
    }

    // Testing IPokedexFactory and Ipokedex unique functions
    @Test
    public void testIPokedex() throws PokedexException {
        PokedexFactory pokedexFactory = new PokedexFactory();
        IPokedex newPokedex = pokedexFactory.createPokedex(pokedex, pokedex);

        // IPokedex.getPokemons()
        assertNotNull(newPokedex.getPokemons());
        assertTrue(newPokedex.getPokemons().isEmpty());
        assertEquals(0, newPokedex.size());

        // IPokedex.addPokemon()
        newPokedex.addPokemon(pokemon1);
        newPokedex.addPokemon(pokemon2);
        Pokemon pokemon3 = newPokedex.createPokemon(26, 283, 16, 1468, 5);
        newPokedex.addPokemon(pokemon3);
        assertTrue(newPokedex.getPokemons().contains(pokemon1));

        // IPokedex.getPokemon()
        assertTrue(newPokedex.getPokemon(0) instanceof Pokemon);
        assertTrue(newPokedex.getPokemon(2) instanceof Pokemon);
        assertEquals(pokemon1, newPokedex.getPokemon(0));
        assertEquals(pokemon2, newPokedex.getPokemon(1));
        assertEquals(24, newPokedex.getPokemon(0).getIndex());
        assertEquals("Arbok", newPokedex.getPokemon(0).getName());
        assertEquals(13, newPokedex.getPokemon(0).getAttack());
        assertEquals(13, newPokedex.getPokemon(0).getDefense());
        assertEquals(12, newPokedex.getPokemon(0).getStamina());
        assertEquals(281, newPokedex.getPokemon(0).getCp());
        assertEquals(36, newPokedex.getPokemon(0).getHp());
        assertEquals(1324, newPokedex.getPokemon(0).getDust());
        assertEquals(2, newPokedex.getPokemon(0).getCandy());
        assertEquals((13 + 13 + 12) / 45.0, newPokedex.getPokemon(0).getIv(), 0.001);

        assertEquals(26, newPokedex.getPokemon(2).getIndex());
        assertEquals("Raichu", newPokedex.getPokemon(2).getName());
        assertEquals(8, newPokedex.getPokemon(2).getAttack());
        assertEquals(9, newPokedex.getPokemon(2).getDefense());
        assertEquals(7, newPokedex.getPokemon(2).getStamina());
        assertEquals(283, newPokedex.getPokemon(2).getCp());
        assertEquals(16, newPokedex.getPokemon(2).getHp());
        assertEquals(1468, newPokedex.getPokemon(2).getDust());
        assertEquals(5, newPokedex.getPokemon(2).getCandy());
        assertEquals((7 + 8 + 9) / 45.0, newPokedex.getPokemon(2).getIv(), 0.0001);

        // IPokedex.size()
        assertEquals(3, newPokedex.size());
        List<Pokemon> list = new ArrayList<>();
        list.add(pokemon1);
        list.add(pokemon2);
        list.add(pokemon3);

        // IPokedex.getPokemons()
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), newPokedex.getPokemons().get(i));
        }


        // IPokedex.getPokemons(Comparator<Pokemon> order)
        // pokemon1     024	Arbok	13	13	12	281	36	1324	2
        // pokemon2     025	Pikachu	5	2	5	207	52	5836	5
        // pokemon3     026	Raichu	8	9	7	283	16	1468	5

        // Comparate Names
        List<Pokemon> descendingList = new ArrayList<>();
        descendingList.add(pokemon3); // Raichu
        descendingList.add(pokemon2); // Pikachu
        descendingList.add(pokemon1); // Arbok
        Comparator<Pokemon> nameComparator = Comparator.comparing(Pokemon::getName);
        List<Pokemon> orderedName = newPokedex.getPokemons(nameComparator);
        assertEquals(orderedName.get(0).getName(), "Arbok");
        assertEquals(orderedName.get(1).getName(), "Pikachu");
        assertEquals(orderedName.get(2).getName(), "Raichu");
        assertEquals(orderedName.size(), 3); // New list should the same size as original
        // Comparate Attack
        Comparator<Pokemon> attackComparator = Comparator.comparing(Pokemon::getAttack);
        List<Pokemon> orderedAttack = newPokedex.getPokemons(attackComparator);
        assertEquals(orderedAttack.get(0).getAttack(), 5);
        assertEquals(orderedAttack.get(1).getAttack(), 8);
        assertEquals(orderedAttack.get(2).getAttack(), 13);

    }

    // Test Pokedex
    @Test
    public void testPokedexBaseValues() throws PokedexException {
        assertNotNull(Pokedex.BASE_VALUES);
        assertEquals(151, Pokedex.BASE_VALUES.size());
        assertEquals(pokedex.getPokemonMetadata(24).getName(), Pokedex.BASE_VALUES.get(24 - 1).getName());
    }

    // Test Pokedex
    @Test(expected = PokedexException.class)
    public void testPokedexGetPokemonOutOfBounds() throws PokedexException {
        pokedex.getPokemon(-20);
    }

    // Test Pokedex
    @Test(expected = IllegalArgumentException.class)
    public void testAddPokemon_NullPokemon() {
        pokedex.addPokemon(null);
    }

    // Test Pokedex
    @Test
    public void testGetPokemonsComparateEmptyList() {
        PokedexFactory pokedexFactory = new PokedexFactory();
        IPokedex newPokedex = pokedexFactory.createPokedex(pokedex, pokedex);
        assertEquals(0, newPokedex.size());
        Comparator<Pokemon> attackComparator = Comparator.comparing(Pokemon::getAttack);
        List<Pokemon> emptyList = newPokedex.getPokemons(attackComparator);
    }

    // Test Pokedex
    @Test(expected = IllegalArgumentException.class)
    public void testAddPokemonsNullComparator() {
        List<Pokemon> newList = pokedex.getPokemons(null);
    }

    // Testing IPokemonTrainerFactory
    @Test
    public void testCreateTrainer_ValidInputs() {
        // Arrange
        String name = "Ash";
        Team team = Team.INSTINCT;

        // Act
        PokemonTrainer trainer = trainerFactory.createTrainer(name, team, pokedexFactory);

        // Assert
        assertNotNull(trainer);
        assertTrue(trainer instanceof PokemonTrainer);
        assertEquals("Ash", trainer.getName());
        assertEquals(Team.INSTINCT, trainer.getTeam());

    }

    // Testing IPokemonTrainerFactory
    @Test(expected = IllegalArgumentException.class)
    public void testCreateTrainer_EmptyName() {
        PokemonTrainer trainer = trainerFactory.createTrainer("", Team.MYSTIC, pokedexFactory);
    }

    // Testing IPokemonTrainerFactory
    @Test(expected = IllegalArgumentException.class)
    public void testCreateTrainer_NullName() {
        PokemonTrainer trainer = trainerFactory.createTrainer(null, Team.MYSTIC, pokedexFactory);
    }

    // Testing IPokemonTrainerFactory
    @Test(expected = IllegalArgumentException.class)
    public void testCreateTrainer_NullTeam() {
        PokemonTrainer trainer = trainerFactory.createTrainer("Ash", null, pokedexFactory);
    }

    // Testing IPokemonTrainerFactory
    @Test(expected = IllegalArgumentException.class)
    public void testCreateTrainer_NullPokedexFactory() {
        PokemonTrainer trainer = trainerFactory.createTrainer("Ash", Team.MYSTIC, null);
    }

    // TP 6 Tests for RocketFactory

    /**
     * Test de RocketPokemonFactory avec mes tests standards.
     * Le test a échoué pour les valeurs de base (attaque, endurance, défense) car elles sont censées être comprises entre 0 et 15. La valeur réelle créée par cette implémentation est 49.
     * Leur implémentation avait une liste de tâches à effectuer inachevée qui devait inclure les 151 valeurs de base des Pokémon. Pour cette raison, nous ne testons que les 3 premières valeurs fournies : -1, 0 et 1.
     */
    @Test
    public void testCreatePokemonWithValidIndex() {
        // Act
        Pokemon pokemon = rocketFactory.createPokemon(1, 500, 100, 3000, 3);

        // Assert
        assertNotNull(pokemon);
        assertEquals(1, pokemon.getIndex());
        assertEquals("Bulbasaur", pokemon.getName());
        assertEquals(500, pokemon.getCp());
        assertEquals(100, pokemon.getHp());
        assertEquals(3000, pokemon.getDust());
        assertEquals(3, pokemon.getCandy());
        assertEquals(5, pokemon.getAttack());
        assertEquals(5, pokemon.getStamina());
        assertEquals(1.0, pokemon.getIv(), 0.0001);
    }

    /**
     * 
     */
    @Test
    public void testCreatePokemonWithNegativeIndex() {
        // Act
        Pokemon pokemon = rocketFactory.createPokemon(-1, 700, 150, 4000, 5);

        // Assert
        assertNotNull(pokemon);
        assertEquals(-1, pokemon.getIndex());
        assertEquals("Ash's Pikachu", pokemon.getName());
        assertEquals(0.0, pokemon.getIv(), 0.0001);
        assertEquals(1000, pokemon.getAttack());

    }

    @Test
    public void testCreatePokemonWithIndexZero() {
        // Act
        Pokemon pokemon = rocketFactory.createPokemon(0, 500, 100, 3000, 3);

        // Assert
        assertNotNull(pokemon);
        assertEquals(0, pokemon.getIndex());
        assertEquals("MISSINGNO", pokemon.getName());

    }

    /**
     * Lors de l'analyse de la méthode generateRandomStat(), la plage supposée attendue est de 0 à 100, mais le résultat réel obtenu à partir des tests est toujours le même, 49.
     * On peut même essayer de faire un assert que le résultat sera exactement de 49 et obtenir un test positif.
     */
    @Test
    public void testCreatePokemonForRandomValues() {
        Pokemon pokemon = rocketFactory.createPokemon(1, 500, 100, 3000, 3);

        // Assert
        assertNotNull(pokemon);
        assertEquals(49, pokemon.getAttack());
        assertTrue(pokemon.getAttack() >= 0 && pokemon.getAttack() <= 100);


    }

}
