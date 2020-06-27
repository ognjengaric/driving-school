package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.enums.RoadType;
import com.ognjengaric.demo.enums.TrafficIntensityType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Street {

    @Id
    private String name;

    @Column
    private TrafficIntensityType intensity;

    @Column
    private RoadType roadType;

    public Street() {
    }

    public Street(String name) {
        this.name = name;
        this.intensity = TrafficIntensityType.UNDEFINED;
        this.roadType = RoadType.UNDEFINED;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrafficIntensityType getIntensity() {
        return intensity;
    }

    public void setIntensity(TrafficIntensityType intensity) {
        this.intensity = intensity;
    }

    public RoadType getRoadType() {
        return roadType;
    }

    public void setRoadType(RoadType roadType) {
        this.roadType = roadType;
    }
}
