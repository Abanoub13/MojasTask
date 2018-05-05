package com.mojasapp.mojastask.displayalbum;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mojasapp.mojastask.R;
import com.mojasapp.mojastask.data.Injector;

import java.util.ArrayList;

import static com.mojasapp.mojastask.util.ActivityUtil.addFragmentToActivity;

public class DisplayAlbumActivity extends AppCompatActivity {

    public static final String ID_ARRAY_KEY = "ArrayKey";

    private ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_album);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        if (savedInstanceState != null) {
            ids = savedInstanceState.getIntegerArrayList(ID_ARRAY_KEY);
        } else {
            ids = getIntent().getExtras().getIntegerArrayList(ID_ARRAY_KEY);
        }
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
    protected void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(ID_ARRAY_KEY, ids);
        super.onSaveInstanceState(outState);
    }
}
