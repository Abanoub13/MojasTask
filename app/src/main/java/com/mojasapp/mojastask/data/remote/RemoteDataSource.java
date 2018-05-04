package com.mojasapp.mojastask.data.remote;

/**
 * RemoteDataSource interface is used for retrieving data from the REST API
 */
public interface RemoteDataSource<T> {

    void loadData(ListDataListener<T> listener, Object tag);

    void loadData(ListDataListener<T> listener);
    void cancelRequestWithTag(Object tag);

    interface ListDataListener<T> {
        void onSuccess(T[] data);
        void onFailure(Throwable throwable);
    }
}
