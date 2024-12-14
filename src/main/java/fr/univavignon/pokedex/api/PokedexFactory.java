package fr.univavignon.pokedex.api;

public class PokedexFactory implements IPokedexFactory {

    /**
     * createPokedex().
     * @param metadataProvider Metadata provider the created pokedex will use.
     * @param pokemonFactory   Pokemon factory the created pokedex will use.
     * @return IPokedex
     */
    @Override
    public IPokedex createPokedex(final IPokemonMetadataProvider metadataProvider,
                                  final IPokemonFactory pokemonFactory) {
        if (metadataProvider == null || pokemonFactory == null) {
            throw new IllegalArgumentException("Metadata provider and Pokemon factory cannot be null");
        }
        return new Pokedex(metadataProvider, pokemonFactory);
    }
}
