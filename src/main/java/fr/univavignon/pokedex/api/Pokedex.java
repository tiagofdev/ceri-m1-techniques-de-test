package fr.univavignon.pokedex.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Pokedex implements IPokedex.
 */
public class Pokedex implements IPokedex {

    /**
     * Static final base values.
     */
    public static final List<PokemonMetadata> BASE_VALUES;

    static {
        BASE_VALUES = new ArrayList<>();
        String filePath = "src/test/java/fr/univavignon/pokedex/api/baseValues.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split("\t");
                int index = Integer.parseInt(values[0]);
                String name = values[1];
                int attack = Integer.parseInt(values[2]);
                int defense = Integer.parseInt(values[3]);
                int stamina = Integer.parseInt(values[4]);
                BASE_VALUES.add(new PokemonMetadata(index, name, attack, defense, stamina));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * List<Pokemon> pokemonList.
     */
    private final List<Pokemon> pokemonList;
    /**
     * iPokemonFactory.
     */
    private final IPokemonFactory iPokemonFactory;
    /**
     * iPokemonMetadataProvider.
     */
    private final IPokemonMetadataProvider iPokemonMetadataProvider;

    /**
     *
     * @param pokemonMetadataProvider
     * @param pokemonFactory
     */
    Pokedex(final IPokemonMetadataProvider pokemonMetadataProvider, final IPokemonFactory pokemonFactory) {
        pokemonList = new ArrayList<>();
        this.iPokemonFactory = pokemonFactory;
        this.iPokemonMetadataProvider = pokemonMetadataProvider;
    }

    /**
     * Returns the size of the internal pokemonList.
     * @return int
     */
    @Override
    public int size() {
        return pokemonList.size();
    }

    /**
     * Adds the given <tt>pokemon</tt> to this pokedex and returns
     * it unique index.
     *
     * @param pokemon Pokemon to add to this pokedex.
     * @return Index of this pokemon relative to this pokedex.
     */
    @Override
    public int addPokemon(final Pokemon pokemon) {
        if (pokemon == null) {
            throw new IllegalArgumentException("Pokemon cannot be null");
        }
        if (pokemonList == null) {
            throw new IllegalStateException("Pokemon list is not initialized");
        }
        pokemonList.add(pokemon);
        return pokemonList.size() - 1;
    }

    /**
     * .
     * @param id Unique pokedex relative identifier.
     * @return
     * @throws PokedexException
     */
    @Override
    public Pokemon getPokemon(final int id) throws PokedexException {
        try {
            return pokemonList.get(id);
        } catch (IndexOutOfBoundsException iob) {
            throw new PokedexException("Invalid Pokemon id");
        }
    }

    /**
     * .
     * @return List<Pokemon>
     */
    @Override
    public List<Pokemon> getPokemons() {
        return this.pokemonList;
    }

    /**
     * .
     * @param order Comparator instance used for sorting the created view.
     * @return List<Pokemon>
     */
    @Override
    public List<Pokemon> getPokemons(final Comparator<Pokemon> order) {
        if (order == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        List<Pokemon> sortedPokemons = new ArrayList<>(pokemonList); // Create a copy
        sortedPokemons.sort(order); // Sort using the provided comparator
        return sortedPokemons; // Return the sorted copy
    }

    /**
     * .
     * @param index Index of the pokemon to retrieve metadata for.
     * @return
     * @throws PokedexException
     */
    @Override
    public PokemonMetadata getPokemonMetadata(final int index) throws PokedexException {
        return iPokemonMetadataProvider.getPokemonMetadata(index);
    }

    /**
     * .
     * @param index Pokemon index.
     * @param cp    Pokemon CP.
     * @param hp    Pokemon HP.
     * @param dust  Required dust for upgrading pokemon.
     * @param candy Required candy for upgrading pokemon.
     * @return
     */
    @Override
    public Pokemon createPokemon(final int index, final int cp, final int hp, final int dust, final int candy) {
        return iPokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }
}
