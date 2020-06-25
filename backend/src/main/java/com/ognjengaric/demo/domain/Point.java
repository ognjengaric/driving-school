package com.ognjengaric.demo.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Point {

    private float longitude;
    private float latitude;

    public Point() {
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
