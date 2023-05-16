package com.portfolio.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MoveMessage {
    
    private Long cardId;
    private int newPosition;
    
}
