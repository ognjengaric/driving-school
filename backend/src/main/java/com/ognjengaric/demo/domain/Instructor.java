package com.ognjengaric.demo.domain;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorColumn(name = "I")
public class Instructor extends User{

    @OneToMany
    private List<Candidate> candidates = new ArrayList<>();

    public Instructor() {
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
