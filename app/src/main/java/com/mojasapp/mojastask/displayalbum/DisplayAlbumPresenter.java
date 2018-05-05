package com.mojasapp.mojastask.displayalbum;


import com.mojasapp.mojastask.data.AlbumRaw;
import com.mojasapp.mojastask.data.mapper.ListMapper;
import com.mojasapp.mojastask.data.remote.RemoteDataSource;
import com.mojasapp.mojastask.data.remote.Repository;

import java.util.Arrays;

import static com.mojasapp.mojastask.util.Checks.assertNotNull;

public class DisplayAlbumPresenter implements DisplayAlbumContract.Presenter {

    private Repository<Integer, AlbumRaw> repository;
    private ListMapper<AlbumRaw, DisplayableAlbum> listMapper;
    private DisplayAlbumContract.View view;

    private static final Object tagObject = new Object();

    public DisplayAlbumPresenter(Repository<Integer, AlbumRaw> repository, ListMapper<AlbumRaw, DisplayableAlbum> listMapper) {
        this.repository = repository;
        this.listMapper = listMapper;
    }

    @Override
    public void start(DisplayAlbumContract.View view) {
        this.view = assertNotNull(view);
    }

    @Override
    public void stop() {
        repository.cancelRequestWithTag(tagObject);
        this.view = null;
    }

    @Override
    public void loadFilteredAlbums(Integer[] ids) {
        view.showLoading(true);
        repository.loadElementsById(
                Arrays.asList(ids),
                new RemoteDataSource.ListDataListener<AlbumRaw>() {

                    @Override
                    public void onSuccess(AlbumRaw[] data) {
                        view.showLoading(false);
                        view.displayAlbums(
                                listMapper.
                                        map(Arrays.asList(data)).
                                        toArray(new DisplayableAlbum[0])
                        );
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        view.showLoading(false);
                        view.displayLoadingErrorMessage();
                    }
                },
                tagObject);
    }
}
