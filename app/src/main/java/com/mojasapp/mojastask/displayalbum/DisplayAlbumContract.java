package com.mojasapp.mojastask.displayalbum;


import com.mojasapp.mojastask.BasePresenter;
import com.mojasapp.mojastask.BaseView;

public interface DisplayAlbumContract {

    interface Presenter extends BasePresenter<View> {

        void loadFilteredAlbums(Integer[] ids);

    }

    interface View extends BaseView<Presenter> {

        /**
         * Show/hide the loading view
         */
        void showLoading(boolean isLoading);


        /**
         * Start displaying the passed albums
         */
        void displayAlbums(DisplayableAlbum[] albums);

        /**
         * Display data loading error message
         */
        void displayLoadingErrorMessage();
    }
}
