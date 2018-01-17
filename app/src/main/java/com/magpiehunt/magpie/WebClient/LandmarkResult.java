package com.magpiehunt.magpie.WebClient;


import com.magpiehunt.magpie.Entities.Landmark;

import java.util.List;

/**
 * Created by James on 1/16/2018.
 */

public class LandmarkResult extends ApiResponse {

    private List<Landmark> Landmarks;

    public List<Landmark> getLandmarks() {
        return Landmarks;
    }

    public void setLandmarks(List<Landmark> landmarks) {
        Landmarks = landmarks;
    }
}
