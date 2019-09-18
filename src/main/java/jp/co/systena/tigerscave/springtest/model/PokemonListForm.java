package jp.co.systena.tigerscave.springtest.model;

import java.util.List;
import javax.validation.Valid;

public class PokemonListForm {

  @Valid
  private List<Pokemon> pokemonList;

  public List<Pokemon> getPokemonList() {
    return pokemonList;
  }

  public void setPokemonList(List<Pokemon> pokemonList) {
    this.pokemonList = pokemonList;
  }
}
