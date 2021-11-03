package com.example.toyproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    @GET("info/find/room/{co}")
    Call<Integer> findRoom(@Path("co") String co);
}
