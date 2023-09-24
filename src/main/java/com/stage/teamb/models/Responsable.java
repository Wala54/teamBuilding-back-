package com.stage.teamb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Responsable extends Users {
    private int matricule;
    @OneToMany(mappedBy = "responsable")
    public List<Event> events = new ArrayList<>();

}

