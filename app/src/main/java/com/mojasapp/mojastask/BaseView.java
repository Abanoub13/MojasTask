package com.mojasapp.mojastask;


/**
 * BaseView is the base view for all (MVP) Views in this module
 */
public interface BaseView<PRESENTER extends BasePresenter> {

    /**
     * Sets the presenter for the view
     */
    void setPresenter(PRESENTER presenter);
}
