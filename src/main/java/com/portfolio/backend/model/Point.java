package com.portfolio.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Point {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String description;
    private Boolean isPositive;

    public Point() {
    }

    public Point(String description, Boolean isPositive) {
        this.description = description;
        this.isPositive = isPositive;
    }
    
}
