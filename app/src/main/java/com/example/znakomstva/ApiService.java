package com.example.znakomstva;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/api/users")
    Call<UserDto> registerUser (@Body UserDto user);

    @GET("/api/users/check")
    Call<Boolean> checkUserExists(@Query("username") String username, @Query("password") String password);
}