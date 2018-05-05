package com.mojasapp.mojastask.displayalbum;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mojasapp.mojastask.R;

import static com.mojasapp.mojastask.util.Checks.assertNotNull;
import static android.view.View.VISIBLE;
import static android.view.View.GONE;
import static com.mojasapp.mojastask.displayalbum.DisplayAlbumContract.Presenter;


public class DisplayAlbumFragment extends Fragment implements DisplayAlbumContract.View {

    private Presenter presenter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Integer[] ids;

    public static final String ID_ARRAY_KEY = "ArrayKey";

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View rootView = inflater.inflate(
                R.layout.fragment_display_album,
                container,
                false
        );
        recyclerView = rootView.findViewById(R.id.albumsRecyclerView);
        progressBar = rootView.findViewById(R.id.progresBar);
        ids = getArguments().
                getIntegerArrayList(ID_ARRAY_KEY).
                toArray(new Integer[0]);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        presenter.start(this);
        presenter.loadFilteredAlbums(ids);
        return rootView;
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = assertNotNull(presenter);
    }

    @Override
    public void showLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(VISIBLE);
            recyclerView.setVisibility(GONE);
        } else {
            progressBar.setVisibility(GONE);
        }
    }

    @Override
    public void displayAlbums(DisplayableAlbum[] albums) {
        recyclerView.setVisibility(VISIBLE);
        recyclerView.setAdapter(new DisplayableItemAdapter(albums));
    }

    @Override
    public void displayLoadingErrorMessage() {
        new AlertDialog.Builder(getActivity()).
                setTitle(R.string.loading_error_message_title).
                setMessage(R.string.loading_error_message_description).
                setNegativeButton(R.string.no, null).
                setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.loadFilteredAlbums(ids);
                    }
                }).
                create().
                show();
    }

    @Override
    public void onDestroyView() {
        presenter.stop();
        super.onDestroyView();
    }
}
