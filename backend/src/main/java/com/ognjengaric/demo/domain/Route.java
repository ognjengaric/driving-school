package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.dto.NewRouteDTO;
import com.ognjengaric.demo.enums.LicenceCategory;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private LicenceCategory category;

    @Column
    @Type(type = "text")
    private String encodedCoordinates;

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
        this.category = newRouteDTO.getCategory();
        this.duration = newRouteDTO.getDuration();
        this.distance = newRouteDTO.getDistance();
        this.encodedCoordinates = newRouteDTO.getEncodedCoordinates();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LicenceCategory getCategory() {
        return category;
    }

    public void setCategory(LicenceCategory category) {
        this.category = category;
    }

    public String getEncodedCoordinates() {
        return encodedCoordinates;
    }

    public void setEncodedCoordinates(String encodedCoordinates) {
        this.encodedCoordinates = encodedCoordinates;
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
