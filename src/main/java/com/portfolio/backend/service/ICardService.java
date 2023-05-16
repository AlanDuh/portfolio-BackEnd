package com.portfolio.backend.service;

import com.portfolio.backend.model.Card;
import java.util.List;

public interface ICardService {
    
    public List<Card> getCards();
    public List<Card> getCards(List<Long> ids);
    public Card getCard(Long id);
    public void addCard(Card c);
    public void addCards(List<Card> cards);
    public void deleteCard(Long id);
    
}
