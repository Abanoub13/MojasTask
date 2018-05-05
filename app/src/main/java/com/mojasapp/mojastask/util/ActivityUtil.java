package com.mojasapp.mojastask.util;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static com.mojasapp.mojastask.util.Checks.assertNotNull;

/**
 * {@link ActivityUtil} class is a utility class for Activity
 */
public class ActivityUtil {

    /**
     * Loads a fragment to activity
     */
    public static void addFragmentToActivity(
            FragmentManager fragmentManager,
            Fragment fragment,
            int fragmentId
    ) {
        assertNotNull(fragmentManager);
        assertNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(fragmentId, fragment);
        transaction.commit();
    }
}
