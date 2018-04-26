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

    /*
     * Envía de manera asíncrona la petición y notifica a tu aplicación con un 
     * callback cuando una respuesta regresa. Ya que esta petición es asíncrona,
     * Retrofit maneja la ejecución en el hilo de fondo para que el hilo de la 
     * UI principal no sea bloqueada o interfiera con esta.
     */
    call.enqueue(new Callback<PokemonFeed>() {
      
      /*
       * Invocado para una respuesta HTTP recibida. Este método es llamado para 
       * una respuesta que puede ser correctamente manejada incluso si el servidor 
       * devuelve un mensaje de error. Así que si obtienes un código de estado 
       * 404 o 500, este método aún será llamado. 
       */
      @Override
      public void onResponse(Call<PokemonFeed> call, Response<PokemonFeed> response) {
        if (response.code() == 200) {
          PokemonFeed data = response.body();
          System.out.println(data.getResults());
          
        } else {
          onFailure(call, new Exception("responseCode = " + response.code()));
        }
      }

      /**
       * invocado cuando una excepción de red ocurre comunicando al servidor o 
       * cuando una excepción inesperada ocurrió manejando la petición o 
       * procesando la respuesta.
       */
      @Override
      public void onFailure(Call<PokemonFeed> call, Throwable t) {
        t.printStackTrace(System.out);
      }
    });
  }

  public static void main(String[] args) {
    Main obj = new Main();
    obj.loadJSON();
    //obj.loadJSON();
  }
}
