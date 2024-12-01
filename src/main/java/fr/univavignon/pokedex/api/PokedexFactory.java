package fr.univavignon.pokedex.api;

public class PokedexFactory implements IPokedexFactory {
    IPokedex iPokedex;

    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        if (metadataProvider == null || pokemonFactory == null) {
            throw new IllegalArgumentException("Metadata provider and Pokemon factory cannot be null");
        }
        return new Pokedex(metadataProvider, pokemonFactory);
    }
}
