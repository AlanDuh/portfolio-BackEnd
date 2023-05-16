package com.portfolio.backend.model;

import com.portfolio.backend.dto.HardSkillDto;
import com.portfolio.backend.dto.ImageDto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class HardSkill {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private Double val;
    @OneToOne
    @JoinColumn(name = "background_id", referencedColumnName = "id")
    private Image background;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "card_id")
    private List<Point> points;

    public HardSkill() {
    }

    public HardSkill(Double val, Image background, List<Point> points) {
        this.val = val;
        this.background = background;
        this.points = points;
    }
    
    public HardSkillDto getContentDto(Long cardId, String cardName, int cardIdx) {
        
        ImageDto bgDto = null;
        if (background != null) {
            bgDto = background.getImageDto();
        }
        HardSkillDto content = new HardSkillDto(
                cardId, 
                cardName, 
                cardIdx,
                "HardSkill", 
                val,
                bgDto, 
                points
        );
        return content;
        
    }
    
}
