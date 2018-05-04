package com.mojasapp.mojastask.data.mapper;

import java.util.ArrayList;
import java.util.List;

import static com.mojasapp.mojastask.util.Checks.assertNotNull;

/**
 * ListMapper maps a list of objects of certain type to a list of objects of another type
 */
public class ListMapper<FROM, TO> {

    private Mapper<FROM, TO> singleMapper;

    public ListMapper(Mapper<FROM, TO> singleMapper) {
        this.singleMapper =  assertNotNull(singleMapper);
    }

    /**
     * converts an list of instances to a list instances of another type
     */
    public List<TO> map(List<FROM> fromList) {
        assertNotNull(fromList);
        ArrayList<TO> toList = new ArrayList<>();
        for (FROM from : fromList) {
            toList.add(singleMapper.map(from));
        }
        return toList;
    }
}
