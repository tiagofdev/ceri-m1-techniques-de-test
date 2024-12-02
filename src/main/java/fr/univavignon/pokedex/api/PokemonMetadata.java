package fr.univavignon.pokedex.api;

/**
 * Pokemon metadata POJO.
 *
 * @author fv
 */
public class PokemonMetadata {

    /**
     * Pokemon index.
     **/
    private final int index;

    /**
     * Pokemon name.
     **/
    private final String name;

    /**
     * Pokemon attack level.
     **/
    private final int attack;

    /**
     * Pokemon defense level.
     **/
    private final int defense;

    /**
     * Pokemon stamina level.
     **/
    private final int stamina;

    /**
     * Default constructor.
     *
     * @param pindex   Pokemon index.
     * @param pname    Pokemon name.
     * @param pattack  Attack level.
     * @param pdefense Defense level.
     * @param pstamina Stamina level.
     */
    public PokemonMetadata(final int pindex,
                           final String pname,
                           final int pattack,
                           final int pdefense,
                           final int pstamina) {
        this.index = pindex;
        this.name = pname;
        this.attack = pattack;
        this.defense = pdefense;
        this.stamina = pstamina;
    }

    /**
     * Index getter.
     * @return index
     **/
    public int getIndex() {
        return index;
    }

    /**
     * Name getter.
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * Attack level getter.
     * @return attack
     **/
    public int getAttack() {
        return attack;
    }

    /**
     * Defense level getter.
     * @return defense
     **/
    public int getDefense() {
        return defense;
    }

    /**
     * Stamina level getter.
     * @return stamina
     **/
    public int getStamina() {
        return stamina;
    }

}
