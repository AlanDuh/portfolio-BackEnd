package com.portfolio.backend.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectDto extends CardDto implements Serializable {
    
    private Long id;
    private String name;
    private int idx;
    private String type;
    private String description;
    private Date date;
    private List<ImageDto> images;
    private String pageLink;
    private String gitHubLink;

    public ProjectDto() {
    }

    public ProjectDto(Long id, String name, int idx, String type, String description, Date date, List<ImageDto> images, String pageLink, String gitHubLink) {
        this.id = id;
        this.name = name;
        this.idx = idx;
        this.type = type;
        this.description = description;
        this.date = date;
        this.images = images;
        this.pageLink = pageLink;
        this.gitHubLink = gitHubLink;
    }
    
}
