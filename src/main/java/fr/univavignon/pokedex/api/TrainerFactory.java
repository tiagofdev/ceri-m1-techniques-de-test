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
     * @param pname           Name of the created trainer.
     * @param pteam           Team of the created trainer.
     * @param pokedexFactory Factory to use for creating associated pokedex instance.
     * @return
     */
    @Override
    public PokemonTrainer createTrainer(final String pname, final Team pteam, final IPokedexFactory pokedexFactory) {
        if (pname == null || pname.trim().isEmpty()) {
            throw new IllegalArgumentException("Trainer name cannot be null or empty");
        }
        if (pteam == null) {
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
        return new PokemonTrainer(pname, pteam, pokedex);
    }
}

// PokemonTrainer <- TrainerFactory <- PokedexFactory <- MetadataProvider + PokemonFactory
