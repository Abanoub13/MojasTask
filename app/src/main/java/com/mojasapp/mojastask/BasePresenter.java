package com.mojasapp.mojastask;

/**
 * BasePresenter is the base presenter class for all Presenters in this module
 */
public interface BasePresenter<VIEW extends BaseView> {

    /**
     * Starts the presenter while providing the view instance
     */
    void start(VIEW view);

    /**
     * Stops the presenter
     */
    void stop();
}
