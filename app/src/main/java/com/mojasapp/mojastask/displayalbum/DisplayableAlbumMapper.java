package com.mojasapp.mojastask.displayalbum;

import com.mojasapp.mojastask.data.AlbumRaw;
import com.mojasapp.mojastask.data.mapper.Mapper;


public class DisplayableAlbumMapper implements Mapper<AlbumRaw, DisplayableAlbum> {

    @Override
    public DisplayableAlbum map(AlbumRaw albumRaw) {
        return new DisplayableAlbum(
                albumRaw.getId(),
                albumRaw.getTitle(),
                albumRaw.getImageUrl()
        );
    }
}
