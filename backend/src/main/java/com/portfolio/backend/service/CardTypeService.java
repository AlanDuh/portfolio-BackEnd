package com.portfolio.backend.service;

import com.portfolio.backend.model.CardType;
import com.portfolio.backend.repository.CardTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardTypeService implements ICardTypeService {
    
    @Autowired
    CardTypeRepository CTRepo;

    @Override
    public CardType findCardType(String type) {
        CardType CT = CTRepo.findCardTypeByType(type);
        return CT;
    }

    @Override
    public List<CardType> getCardTypes() {
        List<CardType> CTList = CTRepo.findAll();
        return CTList;
    }
    
}
