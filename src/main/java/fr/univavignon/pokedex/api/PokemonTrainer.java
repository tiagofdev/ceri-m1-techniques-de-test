package fr.univavignon.pokedex.api;

/**
 * Trainer POJO.
 *
 * @author fv
 */
public class PokemonTrainer {

    /**
     * Trainer name.
     **/
    private final String name;

    /**
     * Trainer team.
     **/
    private final Team team;

    /**
     * Trainer pokedex.
     **/
    private final IPokedex pokedex;

    /**
     * Default constructor.
     *
     * @param pname    Trainer name.
     * @param pteam    Trainer team.
     * @param ppokedex Trainer pokedex.
     */
    public PokemonTrainer(final String pname, final Team pteam, final IPokedex ppokedex) {
        this.name = pname;
        this.team = pteam;
        this.pokedex = ppokedex;
    }

    /**
     * Name getter.
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * Team getter.
     * @return team
     **/
    public Team getTeam() {
        return team;
    }

    /**
     * Pokedex getter.
     * @return pokedex
     **/
    public IPokedex getPokedex() {
        return pokedex;
    }

}

// PokemonTrainer <- TrainerFactory <- PokedexFactory <- MetadataProvider + PokemonFactory
