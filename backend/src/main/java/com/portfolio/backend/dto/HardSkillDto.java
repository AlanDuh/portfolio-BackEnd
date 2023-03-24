package com.portfolio.backend.dto;

import com.portfolio.backend.model.Point;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HardSkillDto extends CardDto implements Serializable {
    
    private Long id;
    private String name;
    private int idx;
    private String type;
    private Double val;
    private ImageDto background;
    private List<Point> points;

    public HardSkillDto() {
    }

    public HardSkillDto(Long id, String name, int idx, String type, Double val, ImageDto background, List<Point> points) {
        this.id = id;
        this.name = name;
        this.idx = idx;
        this.type = type;
        this.val = val;
        this.background = background;
        this.points = points;
    }
    
}
