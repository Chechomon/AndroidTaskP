package com.example.secureapi.network.services;

import com.example.secureapi.network.data.LoginWrapper;
import com.example.secureapi.network.services.data.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthService {

    @POST("user/login")
    Call<Token> login(@Body LoginWrapper login);
}
