package com.stage.teamb.models.dtos;

import lombok.Data;

import java.util.List;

@Data

public class RatingDTO {
    private Long id;
    private Boolean value;
    private List<Long> publishedsId;
    private Long employeeId;
}
