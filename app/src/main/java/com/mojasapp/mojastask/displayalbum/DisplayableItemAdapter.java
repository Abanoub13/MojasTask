package com.mojasapp.mojastask.displayalbum;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mojasapp.mojastask.R;

public class DisplayableItemAdapter extends RecyclerView.Adapter<DisplayableAlbumViewHolder> {

    private DisplayableAlbum[] albums;

    public DisplayableItemAdapter(DisplayableAlbum[] albums) {
        this.albums = albums;
    }

    @NonNull
    @Override
    public DisplayableAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.view_holder_displayable_album, parent, false);
        DisplayableAlbumViewHolder holder = new DisplayableAlbumViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayableAlbumViewHolder holder, int position) {
        holder.bindAlbum(albums[position]);
    }

    @Override
    public int getItemCount() {
        return albums.length;
    }
}
