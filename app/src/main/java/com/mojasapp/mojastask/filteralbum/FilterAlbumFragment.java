package com.mojasapp.mojastask.filteralbum;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.mojasapp.mojastask.R;

import static com.mojasapp.mojastask.filteralbum.FilterAlbumContract.Presenter;
import static android.view.View.VISIBLE;
import static android.view.View.GONE;
import static com.mojasapp.mojastask.util.Checks.assertNotNull;
import static com.mojasapp.mojastask.filteralbum.FilterableAlbumViewHolder.FilterListener;


public class FilterAlbumFragment extends Fragment implements FilterAlbumContract.View {

    private Presenter presenter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button filterButton;

    @Nullable
    @Override
    public android.view.View onCreateView (
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View rootView = inflater.inflate (
                R.layout.fragment_filter_album,
                container,
                false
        );
        this.progressBar = rootView.findViewById(R.id.progresBar);
        this.filterButton = rootView.findViewById(R.id.filterButton);
        this.recyclerView = rootView.findViewById(R.id.albumsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.displayFilteredAlbums();
            }
        });
        presenter.start(this);
        return rootView;
    }

    @Override
    public void setPresenter(@NonNull Presenter presenter) {
        this.presenter = assertNotNull(presenter);
    }

    @Override
    public void showLoading(boolean isLoading) {
        if (isLoading) {
            recyclerView.setVisibility(GONE);
            progressBar.setVisibility(VISIBLE);
        } else {
            progressBar.setVisibility(GONE);
        }
    }

    @Override
    public void displayAlbums(FilterableAlbum[] albums) {
        recyclerView.setVisibility(VISIBLE);
        final FilterableAlbumAdapter adapter = new FilterableAlbumAdapter(albums);
        adapter.setFilterListener(new FilterListener() {
            @Override
            public void onIndexFiltered(int index, boolean isChecked) {
                presenter.checkAlbumId(
                        adapter.getAlbumAtIndex(index).getId(),
                        isChecked
                );
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void navigateToDisplayActivity(Integer[] filteredAlbumsIds) {

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
                        presenter.loadAlbums();
                    }
                }).
                create().
                show();
    }

    @Override
    public void displayNoItemsSelectedErrorMessage() {
        new AlertDialog.Builder(getActivity()).
                setTitle(R.string.no_selection_error_message_title).
                setMessage(R.string.no_selection_error_message_description).
                setNeutralButton(R.string.ok, null).
                create().
                show();
    }

    @Override
    public void onDestroyView() {
        presenter.stop();
        super.onDestroyView();
    }
}
