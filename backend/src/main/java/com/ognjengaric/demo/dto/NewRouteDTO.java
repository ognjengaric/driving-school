package com.ognjengaric.demo.dto;

import com.ognjengaric.demo.domain.Point;
import com.ognjengaric.demo.enums.CategoryType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewRouteDTO {

    private CategoryType category;
    private int time;
    private int distance;
    private Set<String> streets = new HashSet<>();
    private List<Point> routePath = new ArrayList<>();

    public NewRouteDTO() {
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
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

    public Set<String> getStreets() {
        return streets;
    }

    public void setStreets(Set<String> streets) {
        this.streets = streets;
    }

    public List<Point> getRoutePath() {
        return routePath;
    }

    public void setRoutePath(List<Point> routePath) {
        this.routePath = routePath;
    }
}
