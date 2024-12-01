package fr.univavignon.pokedex.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pokedex implements IPokedex {

    public static final List<PokemonMetadata> baseValues;

    static {
        baseValues = new ArrayList<>();
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
                baseValues.add(new PokemonMetadata(index, name, attack, defense, stamina));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    private final List<Pokemon> pokemonList;
    private final IPokemonFactory iPokemonFactory;
    private final IPokemonMetadataProvider iPokemonMetadataProvider;

    Pokedex() {
        pokemonList = new ArrayList<>();
        iPokemonFactory = new PokemonFactory();
        iPokemonMetadataProvider = new PokemonMetadataProvider();
    }

    Pokedex(IPokemonMetadataProvider pokemonMetadataProvider, IPokemonFactory pokemonFactory) {
        pokemonList = new ArrayList<>();
        this.iPokemonFactory = pokemonFactory;
        this.iPokemonMetadataProvider = pokemonMetadataProvider;
    }

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
    public int addPokemon(Pokemon pokemon) {
        if (pokemon == null) {
            throw new IllegalArgumentException("Pokemon cannot be null");
        }
        if (pokemonList == null) {
            throw new IllegalStateException("Pokemon list is not initialized");
        }
        pokemonList.add(pokemon);
        return pokemonList.size() - 1;
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        try {
            return pokemonList.get(id);
        } catch (IndexOutOfBoundsException iob) {
            throw new PokedexException("Invalid Pokemon id");
        }
    }

    @Override
    public List<Pokemon> getPokemons() {
        return this.pokemonList;
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        if (order == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        List<Pokemon> sortedPokemons = new ArrayList<>(pokemonList); // Create a copy
        sortedPokemons.sort(order); // Sort using the provided comparator
        return sortedPokemons; // Return the sorted copy
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return iPokemonMetadataProvider.getPokemonMetadata(index);
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return iPokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }
}