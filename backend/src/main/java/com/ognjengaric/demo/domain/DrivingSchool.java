package com.ognjengaric.demo.domain;

import javax.persistence.*;

import com.ognjengaric.demo.enums.CategoryType;

import java.util.ArrayList;
import java.util.List;

@Entity
public class DrivingSchool {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String shortName;

    @ElementCollection
    private List<CategoryType> availableCategories;

    public DrivingSchool() {
        this.availableCategories = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<CategoryType> getAvailableCategories() {
        return availableCategories;
    }

    public void setAvailableCategories(List<CategoryType> availableCategories) {
        this.availableCategories = availableCategories;
    }
}
