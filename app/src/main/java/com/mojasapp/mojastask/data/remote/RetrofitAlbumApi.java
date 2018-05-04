package com.mojasapp.mojastask.data.remote;

import com.mojasapp.mojastask.data.AlbumRaw;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * RetrofitAlbumApi is API that the retrofit client uses to retrieve album data
 */
public interface RetrofitAlbumApi {

    @GET("photos")
    Call<List<AlbumRaw>> loadAlbums();
}
