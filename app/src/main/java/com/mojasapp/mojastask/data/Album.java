package com.mojasapp.mojastask.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import static com.mojasapp.mojastask.util.Checks.assertNotNull;

/**
 * Album is the base immutable Album entity
 */

public class Album {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String imageUrl;

    public Album(int id, @NonNull String title, @NonNull String imageUrl) {
        this.id = id;
        this.title = assertNotNull(title);
        this.imageUrl = assertNotNull(imageUrl);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
