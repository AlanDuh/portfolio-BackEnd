package com.portfolio.backend.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OwnerInfoDto extends CardDto implements Serializable {
    
    private Long id;
    private String name;
    private int idx;
    private String type;
    private String description;
    private List<ImageDto> images;
    private ImageDto photo;

    public OwnerInfoDto() {
    }

    public OwnerInfoDto(Long id, String name, int idx, String type, String description, List<ImageDto> images, ImageDto photo) {
        this.id = id;
        this.name = name;
        this.idx = idx;
        this.type = type;
        this.description = description;
        this.images = images;
        this.photo = photo;
    }
    
}
