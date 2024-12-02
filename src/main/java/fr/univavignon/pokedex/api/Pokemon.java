package fr.univavignon.pokedex.api;

/**
 * Pokemon POJO.
 *
 * @author fv
 */
public final class Pokemon extends PokemonMetadata {

    /**
     * Combat Point of the pokemon.
     **/
    private final int cp;

    /**
     * HP of the pokemon.
     **/
    private final int hp;

    /**
     * Required dust for upgrading this pokemon.
     **/
    private final int dust;

    /**
     * Required candy for upgrading this pokemon.
     **/
    private final int candy;

    /**
     * IV perfection percentage.
     **/
    private final double iv;

    /**
     * Default constructor.
     *
     * @param index   Pokemon index.
     * @param name    Pokemon name.
     * @param attack  Attack level.
     * @param defense Defense level.
     * @param stamina Stamina level.
     * @param pcp      Pokemon cp.
     * @param php      Pokemon hp.
     * @param pdust    Required dust for upgrading this pokemon.
     * @param pcandy   Required candy for upgrading this pokemon.
     * @param piv      IV perfection percentage.
     */
    public Pokemon(
            final int index,
            final String name,
            final int attack,
            final int defense,
            final int stamina,
            final int pcp,
            final int php,
            final int pdust,
            final int pcandy,
            final double piv) {
        super(index, name, attack, defense, stamina);
        this.cp = pcp;
        this.hp = php;
        this.dust = pdust;
        this.candy = pcandy;
        this.iv = piv;
    }

    /**
     * Combat Point getter getter.
     * @return cp
     */
    public int getCp() {
        return cp;
    }

    /**
     * HP getter.
     * @return hp
     **/
    public int getHp() {
        return hp;
    }

    /**
     * Dust getter.
     * @return dust
     **/
    public int getDust() {
        return dust;
    }

    /**
     * Candy getter.
     * @return candy
     **/
    public int getCandy() {
        return candy;
    }

    /**
     * IV getter.
     * @return iv
     **/
    public double getIv() {
        return iv;
    }

}
