package com.example.znakomstva;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import java.util.List;

public interface PersonApi {
    @POST("/api/users")
    Call<UserDto> createUser(@Body UserDto userDto);

    @GET("/api/users")
    Call<List<UserDto>> getAllUsers();
}