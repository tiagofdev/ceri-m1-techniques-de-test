package fr.univavignon.pokedex.api;

public class TrainerFactory implements IPokemonTrainerFactory {

    /**
     * name.
     */
    private String name;
    /**
     * team.
     */
    private Team team;
    /**
     * metadataProvider.
     */
    private IPokemonMetadataProvider metadataProvider;
    /**
     * pokemonFactory.
     */
    private IPokemonFactory pokemonFactory;

    /**
     * Constructor.
     */
    public TrainerFactory() {
        metadataProvider = new PokemonMetadataProvider();
        pokemonFactory = new PokemonFactory();
    }

    /**
     * .
     * @param name           Name of the created trainer.
     * @param team           Team of the created trainer.
     * @param pokedexFactory Factory to use for creating associated pokedex instance.
     * @return
     */
    @Override
    public PokemonTrainer createTrainer(final String name, final Team team, final IPokedexFactory pokedexFactory) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Trainer name cannot be null or empty");
        }
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        if (pokedexFactory == null) {
            throw new IllegalArgumentException("PokedexFactory cannot be null");
        }


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
