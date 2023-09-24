package com.stage.teamb.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PublishedDTO {
    private Long id;
    private String nom;
    private String description;
    private Long employeeId;
    private List<Long> ratingIds;
    private List<Long> eventIds;
}
