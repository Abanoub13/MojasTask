package com.mojasapp.mojastask.filteralbum;

import com.mojasapp.mojastask.data.AlbumRaw;
import com.mojasapp.mojastask.data.mapper.ListMapper;
import com.mojasapp.mojastask.data.remote.RemoteDataSource;
import com.mojasapp.mojastask.data.remote.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mojasapp.mojastask.filteralbum.FilterAlbumContract.Presenter;
import static com.mojasapp.mojastask.filteralbum.FilterAlbumContract.View;
import static com.mojasapp.mojastask.util.Checks.assertNotNull;

public class FilterAlbumPresenter implements Presenter{

    private View view;
    private Repository<Integer, AlbumRaw> repository;
    private ListMapper<AlbumRaw, FilterableAlbum> listMapper;
    private List<Integer> filteredIds;

    private final Object tagObject = new Object();

    public FilterAlbumPresenter(
            Repository<Integer, AlbumRaw> repository,
            ListMapper<AlbumRaw, FilterableAlbum> listMapper
    ) {
        this.repository = assertNotNull(repository);
        this.listMapper = assertNotNull(listMapper);
        filteredIds = new ArrayList<>();
    }

    @Override
    public void start(View view) {
        this.view = assertNotNull(view);
        loadAlbums();
    }

    @Override
    public void stop() {
        repository.cancelRequestWithTag(tagObject);
        this.view = null;
    }

    @Override
    public void checkAlbumId(int albumId, boolean isChecked) {
        if (isChecked) {
            filteredIds.add(albumId);
        } else {
            filteredIds.remove(filteredIds.indexOf(albumId));
        }
    }

    @Override
    public void displayFilteredAlbums() {
        if (filteredIds.size() > 0) {
            view.navigateToDisplayActivity(filteredIds.toArray(new Integer[0]));
        } else {
            view.displayNoItemsSelectedErrorMessage();
        }
    }

    @Override
    public void loadAlbums() {
        view.showLoading(true);
        repository.loadData(new RemoteDataSource.ListDataListener<AlbumRaw>() {
            @Override
            public void onSuccess(AlbumRaw[] data) {
                view.showLoading(false);
                view.displayAlbums(
                        listMapper.map(Arrays.asList(data)).toArray(new FilterableAlbum[0])
                );
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showLoading(false);
                view.displayLoadingErrorMessage();
            }
        }, tagObject);
    }
}
