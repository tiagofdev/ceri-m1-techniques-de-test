package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory {
    /* I can either instantiate a pokemon using the Pokemon constructor or using the IPokemonFactory interface.
     The second method only accepts parameters for the individual values.
     The base values for a species are not input.
     Therefore, I'm going to assume that using the factory, the individual metadata for attack, defense and
     stamina, which range from 1 to 15 are going to be the base values. As the trainer plays the game , these values are
     changed by other methods during gameplay.
     iv is calculated as attack + defense + stamina / 45
     if all 3 stats are 15 then the sum of them 45 / 45 will a perfect 100% iv */

    /**
     * .
     * @return IV_TOTAL
     */
    public double getIvTotal() {
        return ivTotal;
    }

    /**
     * IV_TOTAL default 45.
     */
    private final double ivTotal = 45.0;

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
        PokemonMetadata metadata;
        try {
            metadata = Pokedex.BASE_VALUES.get(index - 1);
            double iv = (metadata.getAttack() + metadata.getDefense() + metadata.getStamina()) / ivTotal;
            return new Pokemon(metadata.getIndex(), metadata.getName(), metadata.getAttack(), metadata.getDefense(),
                    metadata.getStamina(), cp, hp, dust, candy, iv);
        } catch (IndexOutOfBoundsException iob) {
            return null;
        }
    }
}
