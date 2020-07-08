package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.enums.CategoryType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "C")
public class Candidate extends User{

    @Column
    private CategoryType currentLicence;

    @ManyToOne(fetch = FetchType.EAGER)
    private Instructor instructor;

    @ElementCollection
    private List<CategoryType> ownedLicences = new ArrayList<>();

    public Candidate() {
    }

    public CategoryType getCurrentLicence() {
        return currentLicence;
    }

    public void setCurrentLicence(CategoryType currentLicence) {
        this.currentLicence = currentLicence;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<CategoryType> getOwnedLicences() {
        return ownedLicences;
    }

    public void setOwnedLicences(List<CategoryType> ownedLicences) {
        this.ownedLicences = ownedLicences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate )) return false;
        return super.getId() != null && super.getId().equals(((Candidate) o).getId());
    }
}
