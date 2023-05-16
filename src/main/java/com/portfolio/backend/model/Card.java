package com.portfolio.backend.model;

import com.portfolio.backend.dto.CardDto;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Card {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private int idx;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private CardType type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OI_id", referencedColumnName = "id")
    private OwnerInfo OIContent;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EE_id", referencedColumnName = "id")
    private EducExp EEContent;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HS_id", referencedColumnName = "id")
    private HardSkill HSContent;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SS_id", referencedColumnName = "id")
    private SoftSkill SSContent;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PJ_id", referencedColumnName = "id")
    private Project PJContent;

    public Card() {
    }

    public Card(String name, int idx, CardType type) {
        this.name = name;
        this.idx = idx;
        this.type = type;
    }
    
    public CardDto getCardDto() {
        
        CardDto cardDto;
        
        switch (type.getType()) {
            case "OwnerInfo":
                cardDto = OIContent.getContentDto(id, name, idx);
                break;
            case "EducExp":
                cardDto = EEContent.getContentDto(id, name, idx);
                break;
            case "HardSkill":
                cardDto = HSContent.getContentDto(id, name, idx);
                break;
            case "SoftSkill":
                cardDto = SSContent.getContentDto(id, name, idx);
                break;
            case "Project":
                cardDto = PJContent.getContentDto(id, name, idx);
                break;
            default:
                throw new AssertionError();
        }
        
        return cardDto;
        
    }
    
}
