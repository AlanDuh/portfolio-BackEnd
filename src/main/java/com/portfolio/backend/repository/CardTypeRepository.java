package com.portfolio.backend.repository;

import com.portfolio.backend.model.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Long> {
    
    public CardType findCardTypeByType(String type);
    
}
