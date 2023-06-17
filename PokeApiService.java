package mavericks.PokeDexDemo;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApiService {

    String BASE_URL = "https://pokeapi.co/api/v2/";

    @GET("pokemon/{name}")
    Call<PokemonResponse> getPokemonByName(@Path("name") String name);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
