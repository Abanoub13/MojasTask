package com.mojasapp.mojastask.data.remote;

import com.mojasapp.mojastask.data.AlbumRaw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mojasapp.mojastask.util.Checks.isNotNull;

/**
 * {@link RemoteDataSource} implementation using retrofit
 */
public class RetrofitRemoteDataSource implements RemoteDataSource<AlbumRaw> {

    private RetrofitAlbumApi albumApi;
    private Map<Object, Call<List<AlbumRaw>>> calls;

    public RetrofitRemoteDataSource(RetrofitAlbumApi api) {
        this.albumApi = api;
        calls = new HashMap<>();
    }

    private void addCall(Object tag, Call<List<AlbumRaw>> call) {
        if (isNotNull(tag)) {
            calls.put(tag, call);
        }
    }

    private void removeCall(Object tag, Call<List<AlbumRaw>> call) {
        if (isNotNull(tag)) {
            calls.remove(tag);
        }
    }

    @Override
    public void loadData(final ListDataListener<AlbumRaw> listener,final Object tag) {
        Call<List<AlbumRaw>> currentCall = albumApi.loadAlbums();
        if (tag != null) {
            calls.put(tag, currentCall);
        }
        currentCall.enqueue(new Callback<List<AlbumRaw>>() {
            @Override
            public void onResponse(Call<List<AlbumRaw>> call, Response<List<AlbumRaw>> response) {
                if (call.isCanceled()) {
                    return;
                }
                removeCall(tag, call);
                List<AlbumRaw> albums = response.body();
                if (isNotNull(albums)) {
                    listener.onSuccess(albums.toArray(new AlbumRaw[0]));
                } else {
                    listener.onSuccess(new AlbumRaw[0]);
                }
            }

            @Override
            public void onFailure(Call<List<AlbumRaw>> call, Throwable t) {
                if (call.isCanceled()) {
                    return;
                }
                removeCall(tag, call);
                listener.onFailure(t);
            }
        });
    }

    @Override
    public void loadData(final ListDataListener<AlbumRaw> listener) {
        loadData(listener, null);
    }

    @Override
    public void cancelRequestWithTag(Object tag) {
        if (isNotNull(tag)) {
            Call<List<AlbumRaw>> call = calls.remove(tag);
            if (isNotNull(call)) {
                call.cancel();
            }
        }
    }
}
