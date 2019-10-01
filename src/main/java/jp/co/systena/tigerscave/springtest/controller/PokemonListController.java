package jp.co.systena.tigerscave.springtest.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import jp.co.systena.tigerscave.springtest.model.Pokemon;
import jp.co.systena.tigerscave.springtest.model.PokemonListForm;

@Controller // Viewあり。Viewを返却するアノテーション
public class PokemonListController {

  @Autowired
  JdbcTemplate jdbcTemplate;


  /**
   * 初期表示用
   *
   * アイテムデータを取得して一覧表示する
   *
   * @param model
   * @return
   */
  @RequestMapping(value = "/pokemonlist", method = RequestMethod.GET) // URLとのマッピング
  public String index(Model model) {

    model.addAttribute("pokemons", getPokemonList());

    return "pokemonlist";
  }

  /**
   * データベースからアイテムデータ一覧を取得する
   *
   * @return
   */
  private List<Pokemon> getPokemonList() {

    //SELECTを使用してテーブルの情報をすべて取得する
    List<Pokemon> list = jdbcTemplate.query("SELECT * FROM pokemons ORDER BY pokemon_id", new BeanPropertyRowMapper<Pokemon>(Pokemon.class));

    return list;

    /*
    //結果はMapのリストとして取得することもできる
    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM items ORDER BY item_id");

    */
  }

  /**
   * 「更新」ボタン押下時の処理
   *
   * 入力された名前と価格をアイテムIDをキーとして更新する
   *
   * @param listForm
   * @param result
   * @param model
   * @return
   */
  @RequestMapping(value = "/pokemonlist", method = RequestMethod.POST) // URLとのマッピング
  public String update(@Valid PokemonListForm listForm,
                        BindingResult result,
                        Model model) {

    // listFormに画面で入力したデータが入っているので取得する
    List<Pokemon> pokemonList = listForm.getPokemonList();
    // ビューに受け渡し用にmodelにセット
    model.addAttribute("pokemons", pokemonList);
    model.addAttribute("listForm", listForm);


    //画面入力値にエラーがない場合
    if (!result.hasErrors()) {
      if (pokemonList != null) {
        //画面入力値1行ずつ処理をする
        for (Pokemon pokemon : pokemonList) {

          //1行分の値でデータベースをUPDATEする
          //item_idをキーに名称と価格を更新する
          //SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
          int updateCount = jdbcTemplate.update(
              "UPDATE pokemons SET pokemon_name = ?, level = ? WHERE pokemon_id = ?",
              pokemon.getPokemonName(),
              Integer.parseInt(pokemon.getLevel()),
              Integer.parseInt(pokemon.getPokemonId()));
        }
      }
    }
    return "pokemonlist";
  }

  /**
   * 「削除」リンク押下時の処理
   *
   * パラメータで受け取ったアイテムIDのデータを削除する
   *
   * @param itemId
   * @param model
   * @return
   */
  @RequestMapping(value = "/deletepokemon", method = RequestMethod.GET) // URLとのマッピング
  public String update(@RequestParam(name = "pokemon_id", required = true) String pokemonId,
      Model model) {


    // 本来はここで入力チェックなど


      // パラメータで受けとったアイテムIDのデータを削除する
    // SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
    int deleteCount = jdbcTemplate.update("DELETE FROM pokemons WHERE pokemon_id = ?", Integer.parseInt(pokemonId));


    return "redirect:/pokemonlist";

  }


  /**
   * 「登録」ボタン押下時の処理
   *
   * 入力されたアイテムID、名前、価格をデータベースに登録する
   *
   * @param form
   * @param result
   * @param model
   * @return
   */
  @RequestMapping(value = "/addpokemon", method = RequestMethod.POST) // URLとのマッピング
  public String insert(@Valid Pokemon form,
                        BindingResult result,
                        Model model) {

    //画面入力値にエラーがない場合
    if (!result.hasErrors()) {

          //1行分の値をデータベースにINSERTする
          //SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
          int insertCount = jdbcTemplate.update(
                "INSERT INTO pokemons VALUES( ?, ?, ? )",
                Integer.parseInt(form.getPokemonId()),
                form.getPokemonName(),
                Integer.parseInt(form.getLevel())
              );

    }

    return "redirect:/pokemonlist";

  }


}
