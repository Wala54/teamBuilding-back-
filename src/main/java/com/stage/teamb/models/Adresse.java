package com.stage.teamb.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "adresse")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String rue;
    private String codeRue;
    private String ville;

    @ManyToOne
    private Employee employee;
}
