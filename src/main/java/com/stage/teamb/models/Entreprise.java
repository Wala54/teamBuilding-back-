package com.stage.teamb.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entreprise_sequence")
    private long id;
    private String nomEnreprise;
    private String localEnreprise;


    @OneToMany(cascade = CascadeType.ALL,mappedBy="entreprise")
    private List<Departement> departement  = new ArrayList<>();

}


