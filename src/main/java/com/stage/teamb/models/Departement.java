package com.stage.teamb.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
public class Departement {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="departement_sequence"
    )
    private Long id;
    private String nomDep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;
    @OneToMany(mappedBy = "departement")
    private List<Employee> employees = new ArrayList<>();

}
