package com.example.znakomstva;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/users")
    Call<UserDto> registerUser (@Body UserDto user);
}