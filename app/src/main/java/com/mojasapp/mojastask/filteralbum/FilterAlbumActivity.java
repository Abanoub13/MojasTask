package com.mojasapp.mojastask.filteralbum;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mojasapp.mojastask.BaseActivity;
import com.mojasapp.mojastask.R;
import com.mojasapp.mojastask.data.Injector;

import static com.mojasapp.mojastask.util.ActivityUtil.addFragmentToActivity;

public class FilterAlbumActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    protected void setupFragment() {
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
        return getApplicationContext().getString(R.string.filter_activity_title);
    }
}
