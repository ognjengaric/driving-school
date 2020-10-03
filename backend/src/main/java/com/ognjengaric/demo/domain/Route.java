package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.dto.NewRouteDTO;
import com.ognjengaric.demo.enums.CategoryType;

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
    private CategoryType categoryType;

    @ElementCollection
    private List<Point> points = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "route_streets",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "street_name")
    )
    private Set<Street> streets = new HashSet<>();

    @Column
    private int time;

    @Column
    private  float distance;

    public Route() {
    }

    public Route(NewRouteDTO newRouteDTO){
        this.categoryType = newRouteDTO.getCategory();
        this.time = newRouteDTO.getTime();
        this.distance = newRouteDTO.getDistance();
        this.points = newRouteDTO.getPoints();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Set<Street> getStreets() {
        return streets;
    }

    public void setStreets(Set<Street> streets) {
        this.streets = streets;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
