package fr.univavignon.pokedex.api;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    /**
     * .
     * @param index Index of the pokemon to retrieve metadata for.
     * @return
     * @throws PokedexException
     */
    @Override
    public PokemonMetadata getPokemonMetadata(final int index) throws PokedexException {
        try {
            return Pokedex.BASE_VALUES.get(index - 1);
        } catch (IndexOutOfBoundsException iob) {
            throw new PokedexException("Invalid Pokemon Index");
        }
    }
}
