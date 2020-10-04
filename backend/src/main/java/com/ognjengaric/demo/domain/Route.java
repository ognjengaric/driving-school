package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.dto.NewRouteDTO;
import com.ognjengaric.demo.enums.LicenceCategory;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private LicenceCategory categoryType;

    @ElementCollection
    private List<Coordinate> coordinates = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "route_streets",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "street_name")
    )
    private Set<Street> streets = new HashSet<>();

    @Column
    private int duration;

    @Column
    private  float distance;

    public Route() {
    }

    public Route(NewRouteDTO newRouteDTO){
        this.categoryType = newRouteDTO.getCategory();
        this.duration = newRouteDTO.getDuration();
        this.distance = newRouteDTO.getDistance();
        this.coordinates = newRouteDTO.getCoordinates();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LicenceCategory getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(LicenceCategory categoryType) {
        this.categoryType = categoryType;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public Set<Street> getStreets() {
        return streets;
    }

    public void setStreets(Set<Street> streets) {
        this.streets = streets;
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
}
