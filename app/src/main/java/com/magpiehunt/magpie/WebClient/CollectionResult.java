package com.magpiehunt.magpie.WebClient;

import com.magpiehunt.magpie.Entities.Collection;

import java.util.List;

/**
 * Created by James on 1/16/2018.
 */

public class CollectionResult extends ApiResponse {

    private List<Collection> Collections;

    public List<Collection> getCollections() {
        return Collections;
    }

    public void setCollections(List<Collection> collections) {
        Collections = collections;
    }
}
