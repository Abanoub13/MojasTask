package com.mojasapp.mojastask.data;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

/**
 * AlbumRaw instance creator
 */
public class AlbumRawInstanceCreator implements InstanceCreator<AlbumRaw>{

    @Override
    public AlbumRaw createInstance(Type type) {
        return new AlbumRaw(
                0,
                0,
                "",
                "",
                null
        );
    }
}
