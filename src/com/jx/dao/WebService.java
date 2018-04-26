package com.jx.dao;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

  private static WebService instance;

  private final HashMap<String, Object> cacheDao = new HashMap();
  private final Retrofit retrofit;

  private WebService() {
    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

    retrofit = new Retrofit.Builder()
        .baseUrl("http://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
  }

  public static <T> T getDao(Class<T> service) {
    WebService ws = getInstance();
    T dao = (T) ws.cacheDao.get(service.getCanonicalName());
    if (dao == null) {
      dao = ws.retrofit.create(service);
      ws.cacheDao.put(service.getCanonicalName(), dao);
    }
    return dao;
  }

  public static WebService getInstance() {
    if (instance == null) {
      instance = new WebService();
    }
    return instance;
  }
}
