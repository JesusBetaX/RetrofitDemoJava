
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main {

  private Retrofit retrofit;

  private Main() {
    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    retrofit = new Retrofit.Builder()
            .baseUrl("http://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
  }

  private void loadJSON() {
    RestClient restClient = retrofit.create(RestClient.class);
    Call<PokemonFeed> call = restClient.getData();

    call.enqueue(new Callback<PokemonFeed>() {
      @Override
      public void onResponse(Call<PokemonFeed> call, Response<PokemonFeed> response) {
        switch (response.code()) {
          case 200:
            PokemonFeed data = response.body();
            System.out.println(data.getResults());
            break;
          case 401:

            break;
          default:

            break;
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
  }
}
