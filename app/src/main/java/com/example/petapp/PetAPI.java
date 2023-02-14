package com.example.petapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.PUT;

interface PetAPI {
    @GET("pet/{id}")
            Call<PetInfo> getPet();

    @PUT("pet")
            Call<PetInfo> updatePet();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://petstore.swagger.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
