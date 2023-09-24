package com.stage.teamb.models;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
@MappedSuperclass
@Data
public abstract class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="users_sequence")
    public Long id;
    private String email;
    private Date dateN;
    private String nom;
    private String prenom;
    private Integer tel;
    private String poste;


}
