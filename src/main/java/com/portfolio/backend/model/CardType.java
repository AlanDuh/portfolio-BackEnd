package com.portfolio.backend.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class CardType {
    
    @Id
    private Long id;
    private String type;
    @OneToMany (
            mappedBy = "type"
    )
    private List<Card> cards;
    
}
