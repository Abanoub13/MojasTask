package com.mojasapp.mojastask.filteralbum;

import android.support.annotation.NonNull;

import com.mojasapp.mojastask.data.Album;

/**
 * {@link FilterableAlbum} class represents a Filterable album entity
 */
public class FilterableAlbum extends Album{

    private boolean isSelected;

    public FilterableAlbum(int id, @NonNull String title, @NonNull String imageUrl, boolean isSelected) {
        super(id, title, imageUrl);
        this.isSelected = isSelected;
    }

    public FilterableAlbum(int id, @NonNull String title, @NonNull String imageUrl) {
        this(id, title, imageUrl, false);
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return this.isSelected;
    }
}
