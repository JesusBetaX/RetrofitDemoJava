package com.jx;

import com.jx.dao.PokemonFeedDao;
import com.jx.dao.WebService;
import com.jx.model.PokemonFeed;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main {

  
  private void loadJSON() {
    PokemonFeedDao dao = WebService.getDao(PokemonFeedDao.class);
    Call<PokemonFeed> call = dao.getData();

    call.enqueue(new Callback<PokemonFeed>() {
      @Override
      public void onResponse(Call<PokemonFeed> call, Response<PokemonFeed> response) {
        if (response.code() == 200) {
          PokemonFeed data = response.body();
          System.out.println(data.getResults());
          
        } else {
          onFailure(call, new Exception("responseCode = " + response.code()));
        }
      }

      @Override
      public void onFailure(Call<PokemonFeed> call, Throwable t) {
        t.printStackTrace(System.out);
      }
    });
  }

  public static void main(String[] args) {
    Main obj = new Main();
    obj.loadJSON();
    obj.loadJSON();
  }
}
