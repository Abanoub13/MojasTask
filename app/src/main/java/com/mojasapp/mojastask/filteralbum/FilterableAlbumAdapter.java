package com.mojasapp.mojastask.filteralbum;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mojasapp.mojastask.R;

import static com.mojasapp.mojastask.CustomView.CheckLimitedCheckBox.CheckLimit;
import static com.mojasapp.mojastask.filteralbum.FilterableAlbumViewHolder.FilterListener;

public class FilterableAlbumAdapter extends RecyclerView.Adapter<FilterableAlbumViewHolder> {

    private FilterableAlbum[] albums;
    private CheckLimit checkLimit;
    private FilterListener filterListener;

    public FilterableAlbumAdapter(FilterableAlbum[] albums) {
        this.albums = albums;
        checkLimit = new CheckLimit(10);
    }

    public void setFilterListener(FilterListener filterListener) {
        this.filterListener = filterListener;
    }

    public FilterableAlbum getAlbumAtIndex(int index) {
        if (index >= albums.length) {
            throw new RuntimeException("Invalid index "+index);
        }
        return albums[index];
    }

    @NonNull
    @Override
    public FilterableAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.view_holder_filterable_album, parent, false);
        FilterableAlbumViewHolder holder = new FilterableAlbumViewHolder(rootView, checkLimit);
        holder.setFilterListener(new FilterableAlbumViewHolder.FilterListener() {
            @Override
            public void onIndexFiltered(int index, boolean isChecked) {
                albums[index].setSelected(isChecked);
                if (filterListener != null) {
                    filterListener.onIndexFiltered(index, isChecked);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterableAlbumViewHolder holder, int position) {
        holder.bindAlbum(albums[position]);
    }

    @Override
    public int getItemCount() {
        return albums.length;
    }
}
