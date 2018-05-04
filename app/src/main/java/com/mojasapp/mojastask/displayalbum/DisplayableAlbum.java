package com.mojasapp.mojastask.displayalbum;

import android.support.annotation.NonNull;

import com.mojasapp.mojastask.data.Album;

/**
 * {@link DisplayableAlbum} represent a displayable album entity
 */

public class DisplayableAlbum extends Album {

    public DisplayableAlbum(int id, @NonNull String title, @NonNull String imageUrl) {
        super(id, title, imageUrl);
    }
}
