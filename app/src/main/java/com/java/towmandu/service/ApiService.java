package com.java.towmandu.service;

import com.java.towmandu.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("signup") // Endpoint relative to BASE_URL
    Call<String> signup(@Body User user); // Returns plain text ("success" or "failed")
}