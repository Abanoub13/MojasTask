package com.mojasapp.mojastask.filteralbum;


import com.mojasapp.mojastask.BasePresenter;
import com.mojasapp.mojastask.BaseView;

public interface FilterAlbumContract {

    interface Presenter extends BasePresenter<View> {

        /**
         * FLags the given albumId as checked/unChecked
         */
        void checkAlbumId(int albumId, boolean isChecked);

        /**
         * Display the filtered albums
         */
        void displayFilteredAlbums();

        /**
         * loadAlbums and send them to View
         */
        void loadAlbums();
    }

    interface View extends BaseView<Presenter> {

        /**
         * Show/hide the loading view
         */
        void showLoading(boolean isLoading);

        /**
         * Start displaying the passed albums
         */
        void displayAlbums(FilterableAlbum[] albums);

        /**
         * Navigates to the display screen with the passed filtered albums ids
         */
        void navigateToDisplayActivity(Integer[] filteredAlbumsIds);

        /**
         * Display data loading error message
         */
        void displayLoadingErrorMessage();

        /**
         * Display no items selected error message
         */
        void displayNoItemsSelectedErrorMessage();
    }
}
