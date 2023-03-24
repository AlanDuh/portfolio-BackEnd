package com.portfolio.backend.service;

import com.portfolio.backend.model.CardType;
import java.util.List;

public interface ICardTypeService {
    
    public List<CardType> getCardTypes();
    public CardType findCardType(String type);
    
}
