package com.stage.teamb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Employee extends Users {

  @ManyToOne
  private Departement departement;

  @OneToMany(mappedBy = "employee")
  private List<Adresse> adresses = new ArrayList<>();

  @OneToMany(mappedBy = "employee")
  private List<Rating> ratings = new ArrayList<>();

  @OneToMany(mappedBy = "employee")
  private List<Published> publications = new ArrayList<>();


}
