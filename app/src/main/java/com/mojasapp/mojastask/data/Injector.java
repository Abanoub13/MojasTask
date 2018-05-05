package com.mojasapp.mojastask.data;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojasapp.mojastask.data.mapper.ListMapper;
import com.mojasapp.mojastask.data.remote.AlbumRepository;
import com.mojasapp.mojastask.data.remote.RemoteDataSource;
import com.mojasapp.mojastask.data.remote.Repository;
import com.mojasapp.mojastask.data.remote.RetrofitAlbumApi;
import com.mojasapp.mojastask.data.remote.RetrofitRemoteDataSource;
import com.mojasapp.mojastask.displayalbum.DisplayAlbumPresenter;
import com.mojasapp.mojastask.displayalbum.DisplayableAlbum;
import com.mojasapp.mojastask.displayalbum.DisplayableAlbumMapper;
import com.mojasapp.mojastask.filteralbum.FilterAlbumContract;
import com.mojasapp.mojastask.filteralbum.FilterAlbumPresenter;
import com.mojasapp.mojastask.filteralbum.FilterableAlbum;
import com.mojasapp.mojastask.filteralbum.FilterableAlbumMapper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injector {

    private static Repository<Integer, AlbumRaw> repository;
    private static final String URL = "https://jsonplaceholder.typicode.com";

    public static FilterAlbumContract.Presenter createFilterAlbumPresenter() {
        return new FilterAlbumPresenter(
                injectRepository(),
                injectFilterableMapper()
        );
    }

    public static DisplayAlbumPresenter createDisplayAlbumPresenter() {
        return new DisplayAlbumPresenter(
                injectRepository(),
                injectDisplayableMapper()
        );
    }

    private static Repository<Integer, AlbumRaw> injectRepository() {
        if (repository == null) {
            synchronized (Injector.class) {
                if (repository == null) {
                    repository = new AlbumRepository(
                            injectRemoteDataSource()
                    );
                }
            }
        }
        return repository;
    }

    private static ListMapper<AlbumRaw, FilterableAlbum> injectFilterableMapper() {
        return new ListMapper<>(new FilterableAlbumMapper());
    }

    private static ListMapper<AlbumRaw, DisplayableAlbum> injectDisplayableMapper() {
        return new ListMapper<>(new DisplayableAlbumMapper());
    }

    private static RemoteDataSource<AlbumRaw> injectRemoteDataSource() {
        return new RetrofitRemoteDataSource(
                injectRetrofitAlbumAPI()
        );
    }

    private static RetrofitAlbumApi injectRetrofitAlbumAPI() {
        return createRetrofit().create(RetrofitAlbumApi.class);
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(injectGsonConverterFactory()).
                build();
    }

    private static Gson injectGson() {
        return new GsonBuilder().
                registerTypeAdapter(AlbumRaw.class, new AlbumRawInstanceCreator()).
                create();
    }

    private static GsonConverterFactory injectGsonConverterFactory() {
        return GsonConverterFactory.create(injectGson());
    }
}
