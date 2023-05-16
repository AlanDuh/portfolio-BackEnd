package com.portfolio.backend.dto;

import com.portfolio.backend.model.SubSkill;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SoftSkillDto extends CardDto implements Serializable {
    
    private Long id;
    private String name;
    private int idx;
    private String type;
    private String description;
    private List<SubSkill> subSkills;

    public SoftSkillDto() {
    }

    public SoftSkillDto(Long id, String name, int idx, String type, String description, List<SubSkill> subSkills) {
        this.id = id;
        this.name = name;
        this.idx = idx;
        this.type = type;
        this.description = description;
        this.subSkills = subSkills;
    }
    
}
