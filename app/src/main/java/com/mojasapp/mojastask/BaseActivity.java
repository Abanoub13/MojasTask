package com.mojasapp.mojastask;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * BaseActivity is the superclass for all activities in the application
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setupToolBar();
        setTitle();
        setupFragment();
    }

    /**
     * Sets up the toolbar
     */
    protected void setupToolBar() {
        Toolbar toolbar = getToolBar();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * Sets the toolbar title text
     */
    protected void setTitle() {
        getTitleTextView().setText(getTitleText());
    }

    /**
     * Returns the layout file id
     */
    protected abstract int getLayoutId();

    /**
     * Sets up the activity fragment
     */
    protected abstract void setupFragment();

    /**
     * Returns the toolbar
     */
    protected abstract Toolbar getToolBar();

    /**
     * Returns the title's TextView
     */
    protected abstract TextView getTitleTextView();

    /**
     * Returns the title's text
     */
    protected abstract String getTitleText();
}
