package com.ognjengaric.demo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "I")
public class Instructor extends User{

    @OneToMany(mappedBy = "instructor")
    private List<Candidate> candidates = new ArrayList<>();

    public Instructor() {
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public void addCandidate(Candidate candidate){
        this.candidates.add(candidate);
        candidate.setInstructor(this);
    }

    public void removeCandidate(Candidate candidate){
        this.candidates.remove(candidate);
        candidate.setInstructor(null);
    }
}
