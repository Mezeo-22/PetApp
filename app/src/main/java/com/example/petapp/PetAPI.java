package com.example.petapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

interface PetAPI {
    @GET("pet/{petId}")
            Call<PetInfo> getPet(@Path("petId") Integer id);

    @PUT("pet")
            Call<PetInfo> updatePet();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://petstore.swagger.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
