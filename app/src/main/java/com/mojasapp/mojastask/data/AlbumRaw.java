package com.mojasapp.mojastask.data;

import android.support.annotation.NonNull;

/**
 * {@link AlbumRaw} class represents an immutable Raw album entity
 */
public class AlbumRaw extends Album{

    int albumId;
    String thumbnailUrl;

    public AlbumRaw(
            int id,
            int albumId,
            @NonNull String title,
            @NonNull String imageUrl,
            String thumbnailUrl
    ) {
        super(id, title, imageUrl);
        this.albumId = albumId;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
