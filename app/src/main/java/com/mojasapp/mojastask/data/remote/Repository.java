package com.mojasapp.mojastask.data.remote;

import java.util.List;

public interface Repository<ID, T> extends RemoteDataSource<T>{

    void loadElementById(ID id, SingleElementListener<T> listener, Object tag);
    void loadElementById(ID id, SingleElementListener<T> listener);
    void loadElementsById(List<ID> ids, ListDataListener<T> listener, Object tag);
    void loadElementsById(List<ID> ids, ListDataListener<T> listener);
    void clearCache();

    interface SingleElementListener<T> {
        void onSuccess(T element);
        void onFailure(Throwable throwable);
    }
}
