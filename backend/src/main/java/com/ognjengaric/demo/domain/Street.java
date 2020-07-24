package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.enums.RoadType;
import com.ognjengaric.demo.enums.TrafficIntensityType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NaturalId
    private String name;

    @Column
    private TrafficIntensityType intensity;

    @Column
    private RoadType roadType;

    @ManyToMany(mappedBy = "streets")
    private Set<Route> routes = new HashSet<>();

    public Street() {
    }

    public Street(String name, Route route) {
        this.name = name;
        this.intensity = TrafficIntensityType.UNDEFINED;
        this.roadType = RoadType.UNDEFINED;
        this.addRoute(route);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    public void addRoute(Route route){this.routes.add(route);};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Street street = (Street) o;
        return Objects.equals(name, street.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
