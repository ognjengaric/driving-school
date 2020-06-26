package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.enums.CategoryType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Route {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private CategoryType categoryType;

    @ElementCollection
    private List<Point> points = new ArrayList<>();

    @Column
    private int time;

    @Column
    private  int distance;

    public Route() {
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
