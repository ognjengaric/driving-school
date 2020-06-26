package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.dto.NewRouteDTO;
import com.ognjengaric.demo.enums.CategoryType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private CategoryType categoryType;

    @ElementCollection
    private List<Point> routePath = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Street> streets = new ArrayList<>();

    @Column
    private int time;

    @Column
    private  int distance;

    public Route() {
    }

    public Route(NewRouteDTO newRouteDTO){
        this.categoryType = newRouteDTO.getCategory();
        this.time = newRouteDTO.getTime();
        this.distance = newRouteDTO.getDistance();
        this.routePath = newRouteDTO.getRoutePath();
        this.streets = newRouteDTO.getStreets().stream().map(Street::new).collect(Collectors.toList());
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

    public List<Point> getRoutePath() {
        return routePath;
    }

    public void setRoutePath(List<Point> routePath) {
        this.routePath = routePath;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
