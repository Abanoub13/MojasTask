package com.mojasapp.mojastask.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListUtil {

    public static <T>ArrayList<T> createArrayListFromList(List<T> list) {
        ArrayList<T> arrayList = new ArrayList<>();
        arrayList.addAll(list);
        return arrayList;
    }

    public static <T>ArrayList<T> createArrayListFromArray(T[] array) {
        ArrayList<T> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(array));
        return arrayList;
    }
}
