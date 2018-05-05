package com.mojasapp.mojastask.filteralbum;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mojasapp.mojastask.R;
import com.mojasapp.mojastask.data.Injector;

import static com.mojasapp.mojastask.util.ActivityUtil.addFragmentToActivity;

public class FilterAlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_album);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        FilterAlbumFragment fragment =
                (FilterAlbumFragment) getSupportFragmentManager().findFragmentById(R.id.frame);
        if (fragment == null) {
            fragment = new FilterAlbumFragment();
            fragment.setPresenter(Injector.createFilterAlbumPresenter());
            addFragmentToActivity(
                    getSupportFragmentManager(),
                    fragment,
                    R.id.frame
            );
        }
    }
}
