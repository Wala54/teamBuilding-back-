package com.stage.teamb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Event {
    @Id
    private Long id;
    private String titre;
    private Date dateE;
    @ManyToOne
    private Published published;
    @ManyToOne
    private Responsable responsable;
}
