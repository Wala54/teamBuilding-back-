package com.stage.teamb.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Published {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String description;

    @OneToMany(mappedBy = "published")
    private List<Event> events = new ArrayList<>();
    @ManyToOne
    private Employee employee;

    @OneToMany
    private List<Rating> rating= new ArrayList<>();
}

