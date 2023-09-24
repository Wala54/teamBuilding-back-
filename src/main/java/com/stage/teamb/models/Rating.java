package com.stage.teamb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class Rating {
    @Id
    private Long id;
    private String value;
    @ManyToOne
    private Published publisheds;
    @ManyToOne
    private Employee employee;

}


