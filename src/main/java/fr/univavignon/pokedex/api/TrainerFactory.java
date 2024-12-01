package fr.univavignon.pokedex.api;

public class TrainerFactory implements IPokemonTrainerFactory {

    String name;
    Team team;

    IPokemonMetadataProvider metadataProvider;
    IPokemonFactory pokemonFactory;


    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Trainer name cannot be null or empty");
        }
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        if (pokedexFactory == null) {
            throw new IllegalArgumentException("PokedexFactory cannot be null");
        }

        // Initialize metadataProvider and pokemonFactory (consider default values or mock for testing)
        PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
        PokemonFactory pokemonFactory = new PokemonFactory();

        // Create Pokedex using the factory
        Pokedex pokedex = (Pokedex) pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        if (pokedex == null) {
            throw new IllegalStateException("Failed to create Pokedex from the factory");
        }

        // Return the PokemonTrainer object with valid parameters
        return new PokemonTrainer(name, team, pokedex);
    }
}


// PokemonTrainer <- TrainerFactory <- PokedexFactory <- MetadataProvider + PokemonFactory