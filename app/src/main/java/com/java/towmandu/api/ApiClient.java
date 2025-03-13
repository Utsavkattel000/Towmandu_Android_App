package com.java.towmandu.api;

import com.java.towmandu.utils.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.Url) // http://192.168.1.64:8080/api/
                    .addConverterFactory(GsonConverterFactory.create()) // For JSON parsing
                    .build();
        }
        return retrofit;
    }

}
