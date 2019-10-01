package jp.co.systena.tigerscave.springtest.model;

import javax.validation.constraints.Pattern;

public class Pokemon {

  //プライメード変数宣言
  @Pattern(regexp="^[0-9]*$")
  private String pokemonId;//ID管理用

  /*@Pattern(regexp="^[0-9]*$")
  private  pokemonNumber;//図鑑ナンバー*/

  private String pokemonName;//名前

  @Pattern(regexp="^[0-9]*$")
  private String level;//レベル

  //private Timestamp pokemonGettime;//ポケモンを追加したタイムスタンプ

  //パブリック関数宣言
  //IDのアクセッサ
  public String getPokemonId() {
    return pokemonId;
  }
  public void setPokemonId(String pokemonId) {
    this.pokemonId = pokemonId;
  }

  //図鑑ナンバーのアクセッサ
  public String getPokemonName() {
    return pokemonName;
  }
  public void setPokemonName(String pokemonName) {
    this.pokemonName = pokemonName;
  }

  //レベルのアクセッサ
  public String getLevel() {
    return level;
  }
  public void setLevel(String level) {
    this.level = level;
  }

  //ポケモン取得時間のアクセッサ
  /*public Timestamp getPokemonGettime() {
    return pokemonGettime;
  }
  public void setPokemonGettime(Timestamp pokemonGettime) {
    this.pokemonGettime = pokemonGettime;
  }*/
}
