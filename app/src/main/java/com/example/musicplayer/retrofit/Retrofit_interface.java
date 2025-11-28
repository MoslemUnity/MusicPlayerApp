package com.example.musicplayer.retrofit;

import com.example.musicplayer.models.Music_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Retrofit_interface {
    @GET("get_Music.php")
    Call<List<Music_model>>getMusics();

    @POST("search.php")
    Call<List<Music_model>> search(@Query("key")String key);
}
