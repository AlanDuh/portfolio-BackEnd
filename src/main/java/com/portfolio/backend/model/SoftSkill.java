package com.portfolio.backend.model;

import com.portfolio.backend.dto.SoftSkillDto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class SoftSkill {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String description;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "card_id")
    private List<SubSkill> subSkills;

    public SoftSkill() {
    }

    public SoftSkill(String description, List<SubSkill> subSkills) {
        this.description = description;
        this.subSkills = subSkills;
    }
    
    public SoftSkillDto getContentDto(Long cardId, String cardName, int cardIdx) {
        
        SoftSkillDto content = new SoftSkillDto(
                cardId, 
                cardName, 
                cardIdx,
                "SoftSkill", 
                description, 
                subSkills
        );
        return content;
        
    }
    
}
