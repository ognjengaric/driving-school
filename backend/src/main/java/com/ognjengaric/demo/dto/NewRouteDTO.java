package com.ognjengaric.demo.dto;

import com.ognjengaric.demo.domain.Coordinate;
import com.ognjengaric.demo.enums.LicenceCategory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewRouteDTO {

    private LicenceCategory category;
    private int duration;
    private float distance;
    private Set<String> streets = new HashSet<>();
    private List<Coordinate> coordinates = new ArrayList<>();

    public NewRouteDTO() {
    }

    public LicenceCategory getCategory() {
        return category;
    }

    public void setCategory(LicenceCategory category) {
        this.category = category;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Set<String> getStreets() {
        return streets;
    }

    public void setStreets(Set<String> streets) {
        this.streets = streets;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
