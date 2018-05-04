package com.mojasapp.mojastask.data.mapper;

/**
 * Mapper interface maps an instance an instance of another type
 */
public interface Mapper<FROM, TO> {
    /**
     * converts an instance to an instance of another type
     */
    public TO map(FROM from);
}
