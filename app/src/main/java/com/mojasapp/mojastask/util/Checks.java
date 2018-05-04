package com.mojasapp.mojastask.util;


/**
 * Checks is a utility class for performing assertions and checks
 */
public class Checks {

    /**
     * Throws a run time exception if the passed reference is null otherwise returns it
     */
    public static <T> T assertNotNull(T reference) {
        return assertNotNull(reference, reference.getClass().getName()+" reference is null");
    }

    /**
     * Throws a run time exception with the provided errorMessage if the passed reference is null otherwise returns it
     */
    public static <T> T assertNotNull(T reference, String errorMessage) {
        if (reference == null) {
            throw new RuntimeException(errorMessage);
        }
        return reference;
    }
}
