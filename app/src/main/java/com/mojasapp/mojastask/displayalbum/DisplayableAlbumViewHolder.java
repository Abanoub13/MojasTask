package com.mojasapp.mojastask.displayalbum;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mojasapp.mojastask.R;
import com.squareup.picasso.Picasso;

public class DisplayableAlbumViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView idTextView;
    private ImageView albumImageView;

    public DisplayableAlbumViewHolder(View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        idTextView = itemView.findViewById(R.id.idTextView);
        albumImageView = itemView.findViewById(R.id.albumImageView);
    }

    public void bindAlbum(DisplayableAlbum album) {
        titleTextView.setText(album.getTitle());
        idTextView.setText(String.valueOf(album.getId()));
        albumImageView.setImageResource(0);
        Picasso.get().
                load(album.getImageUrl()).
                into(albumImageView);
    }
}
