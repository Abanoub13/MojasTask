package com.mojasapp.mojastask.displayalbum;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mojasapp.mojastask.BaseActivity;
import com.mojasapp.mojastask.R;
import com.mojasapp.mojastask.data.Injector;

import java.util.ArrayList;

import static com.mojasapp.mojastask.util.ActivityUtil.addFragmentToActivity;

public class DisplayAlbumActivity extends BaseActivity {

    public static final String ID_ARRAY_KEY = "ArrayKey";

    private ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupIdArray(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    private void setupIdArray(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            ids = savedInstanceState.getIntegerArrayList(ID_ARRAY_KEY);
        } else {
            ids = getIntent().getExtras().getIntegerArrayList(ID_ARRAY_KEY);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    protected void setupFragment() {
        DisplayAlbumFragment fragment =
                (DisplayAlbumFragment) getSupportFragmentManager().findFragmentById(R.id.frame);
        if (fragment == null) {
            fragment = new DisplayAlbumFragment();
            Bundle arguments = new Bundle();
            arguments.putIntegerArrayList(DisplayAlbumFragment.ID_ARRAY_KEY, ids);
            fragment.setArguments(arguments);
            fragment.setPresenter(Injector.createDisplayAlbumPresenter());
            addFragmentToActivity(
                    getSupportFragmentManager(),
                    fragment,
                    R.id.frame
            );
        }
    }

    @Override
    protected Toolbar getToolBar() {
        return findViewById(R.id.toolbar);
    }

    @Override
    protected TextView getTitleTextView() {
        return findViewById(R.id.titleTextView);
    }

    @Override
    protected String getTitleText() {
        return getApplicationContext().getString(R.string.displayable_activity_title);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(ID_ARRAY_KEY, ids);
        super.onSaveInstanceState(outState);
    }
}
