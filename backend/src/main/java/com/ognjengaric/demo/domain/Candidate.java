package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.enums.CategoryType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorColumn(name = "U")
public class Candidate extends User{

    @Column
    private CategoryType currentLicence;

    @ElementCollection
    private List<CategoryType> ownedLicences = new ArrayList<>();

}
