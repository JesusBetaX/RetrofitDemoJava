package com.jx.dao;


import com.jx.model.PokemonFeed;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonFeedDao {

  @GET("pokemon")
  Call<PokemonFeed> getData();
}