package com.mojasapp.mojastask.filteralbum;

import com.mojasapp.mojastask.data.AlbumRaw;
import com.mojasapp.mojastask.data.mapper.Mapper;

public class FilterableAlbumMapper implements Mapper<AlbumRaw, FilterableAlbum> {
    @Override
    public FilterableAlbum map(AlbumRaw albumRaw) {
        return new FilterableAlbum(
                albumRaw.getId(),
                albumRaw.getTitle(),
                albumRaw.getImageUrl()
        );
    }
}
