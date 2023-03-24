package com.portfolio.backend.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EducExpDto extends CardDto implements Serializable {
    
    private Long id;
    private String name;
    private int idx;
    private String type;
    private String title;
    private String institution;
    private ImageDto institutionImage;
    private Date date1;
    private Date date2;
    private String description;

    public EducExpDto() {
    }

    public EducExpDto(Long id, String name, int idx, String type, String title, String institution, ImageDto institutionImage, Date date1, Date date2, String description) {
        this.id = id;
        this.name = name;
        this.idx = idx;
        this.type = type;
        this.title = title;
        this.institution = institution;
        this.institutionImage = institutionImage;
        this.date1 = date1;
        this.date2 = date2;
        this.description = description;
    }
    
}
