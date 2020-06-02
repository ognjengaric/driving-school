package com.ognjengaric.demo.domain;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ognjengaric.demo.enums.CategoryType;

import java.util.List;

@Table
public class DrivingSchool {

    @Id
    private long id;

    @Column
    private String name;

    @Column
    private String shortName;

    @ElementCollection
    private List<CategoryType> availableCategories;


}
