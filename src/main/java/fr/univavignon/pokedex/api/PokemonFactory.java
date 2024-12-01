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
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        PokemonMetadata metadata;
        try {
            metadata = Pokedex.BASE_VALUES.get(index - 1);
            double iv = (metadata.getAttack() + metadata.getDefense() + metadata.getStamina()) / 45.0;
            return new Pokemon(metadata.getIndex(), metadata.getName(), metadata.getAttack(), metadata.getDefense(),
                    metadata.getStamina(), cp, hp, dust, candy, iv);
        } catch (IndexOutOfBoundsException iob) {
            return null;
        }
    }
}
