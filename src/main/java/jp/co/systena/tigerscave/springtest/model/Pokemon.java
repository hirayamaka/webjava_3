package jp.co.systena.tigerscave.springtest.model;

import javax.validation.constraints.Pattern;

public class Pokemon {

  @Pattern(regexp="^[0-9]*$")
  private String pokemonId;
  private String pokemonName;
  @Pattern(regexp="^[0-9]*$")
  private String cp;
  public String getPokemonId() {
    return pokemonId;
  }
  public void setPokemonId(String pokemonId) {
    this.pokemonId = pokemonId;
  }
  public String getPokemonName() {
    return pokemonName;
  }
  public void setPokemonName(String pokemonName) {
    this.pokemonName = pokemonName;
  }
  public String getCp() {
    return cp;
  }
  public void setCp(String cp) {
    this.cp = cp;
  }
}
