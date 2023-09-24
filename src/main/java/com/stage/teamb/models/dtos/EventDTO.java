package com.stage.teamb.models.dtos;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class EventDTO {
    private Long id;
    private String titre;
    private Date dateE;
    private List<Long> publishedsId;
    private Long responsableID;


    public Long getPublishedId() {
        return null;
    }
}
