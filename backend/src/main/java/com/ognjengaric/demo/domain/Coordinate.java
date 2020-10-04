package com.ognjengaric.demo.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Coordinate {

    private float longitude;
    private float latitude;

    public Coordinate() {
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
