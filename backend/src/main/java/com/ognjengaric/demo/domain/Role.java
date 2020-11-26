package com.ognjengaric.demo.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role implements GrantedAuthority {

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Id
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public String getName() {
        return name.split("_")[1].toLowerCase();
    }

    public void setName(String name) {
        this.name = name;
    }
}
