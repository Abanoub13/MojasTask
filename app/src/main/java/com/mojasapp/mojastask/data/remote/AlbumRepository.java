package com.mojasapp.mojastask.data.remote;

import android.annotation.SuppressLint;

import com.mojasapp.mojastask.data.AlbumRaw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mojasapp.mojastask.util.Checks.isNotNull;


/**
 * AlbumRepository is a repository for providing and caching elements
 */

public class AlbumRepository implements Repository<Integer, AlbumRaw> {

    private RemoteDataSource<AlbumRaw> dataSource;
    private Map<Integer, AlbumRaw> cache;
    private boolean isCacheValid;

    @SuppressLint("UseSparseArrays")
    public AlbumRepository(RemoteDataSource<AlbumRaw> dataSource) {
        this.dataSource = dataSource;
        cache = new HashMap<>();
        isCacheValid = false;
    }

    private void loadDataToCache(final CacheListener listener, Object tag) {
        dataSource.loadData(new ListDataListener<AlbumRaw>() {
            @Override
            public void onSuccess(AlbumRaw[] data) {
                refreshCache(data);
                isCacheValid = true;
                listener.onSuccess(data);
            }

            @Override
            public void onFailure(Throwable throwable) {
                listener.onFailure(throwable);
            }
        }, tag);
    }

    private void refreshCache(AlbumRaw[] albums) {
        cache.clear();
        for (AlbumRaw album: albums) {
            cache.put(album.getId(), album);
        }
    }

    private void loadElementByIdFromCache(Integer integer, SingleElementListener<AlbumRaw> listener) {
        AlbumRaw album = cache.get(integer);
        listener.onSuccess(album);
    }

    private void loadElementsByIdFromCache(List<Integer> ids, ListDataListener<AlbumRaw> listener) {
        List<AlbumRaw> albumList = new ArrayList<>();
        for (Integer id: ids) {
            if (isNotNull(id)) {
                AlbumRaw album = cache.get(id);
                if (isNotNull(album)) {
                    albumList.add(album);
                }
            }
        }
        listener.onSuccess(albumList.toArray(new AlbumRaw[0]));
    }

    private void loadAlbumsFromCache(ListDataListener<AlbumRaw> listener) {
        listener.onSuccess(cache.values().toArray(new AlbumRaw[0]));
    }

    @Override
    public void loadElementById(
            final Integer integer,
            final SingleElementListener<AlbumRaw> listener,
            final Object tag
    ) {
        if (isCacheValid) {
            loadElementByIdFromCache(integer, listener);
        } else {
            loadDataToCache(new CacheListener() {
                @Override
                public void onSuccess(AlbumRaw[] data) {
                    loadElementByIdFromCache(integer, listener);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    listener.onFailure(throwable);
                }
            }, tag);
        }
    }

    @Override
    public void loadElementById(Integer integer, SingleElementListener<AlbumRaw> listener) {
        loadElementById(integer, listener, null);
    }

    @Override
    public void loadElementsById (
            final List<Integer> integers,
            final ListDataListener<AlbumRaw> listener,
            Object tag
    ) {
        if (isCacheValid) {
            loadElementsByIdFromCache(integers, listener);
        } else {
            loadDataToCache(new CacheListener() {
                @Override
                public void onSuccess(AlbumRaw[] data) {
                    loadElementsByIdFromCache(integers, listener);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    listener.onFailure(throwable);
                }
            }, tag);
        }
    }

    @Override
    public void loadElementsById(List<Integer> integers, ListDataListener<AlbumRaw> listener) {
        loadElementsById(integers, listener, null);
    }

    @Override
    public void clearCache() {
        isCacheValid = false;
        cache.clear();
    }

    @Override
    public void loadData(final ListDataListener<AlbumRaw> listener, final Object tag) {
        if (isCacheValid) {
            loadAlbumsFromCache(listener);
        } else {
            loadDataToCache(new CacheListener() {
                @Override
                public void onSuccess(AlbumRaw[] data) {
                    loadAlbumsFromCache(listener);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    listener.onFailure(throwable);
                }
            }, tag);
        }
    }

    @Override
    public void loadData(ListDataListener<AlbumRaw> listener) {
        loadData(listener, null);
    }

    @Override
    public void cancelRequestWithTag(Object tag) {
        dataSource.cancelRequestWithTag(tag);
    }

    interface CacheListener {
        void onSuccess(AlbumRaw[] data);
        void onFailure(Throwable throwable);
    }
}
