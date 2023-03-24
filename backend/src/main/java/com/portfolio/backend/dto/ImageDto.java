package com.portfolio.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ImageDto {
    
    private Long id;
    private String name;
    private String path;

    public ImageDto() {
    }

    public ImageDto(Long id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }
    
}
