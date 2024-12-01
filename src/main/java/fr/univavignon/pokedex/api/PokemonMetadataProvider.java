package fr.univavignon.pokedex.api;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        try {
            return Pokedex.BASE_VALUES.get(index - 1);
        } catch (IndexOutOfBoundsException iob) {
            throw new PokedexException("Invalid Pokemon Index");
        }
    }
}
