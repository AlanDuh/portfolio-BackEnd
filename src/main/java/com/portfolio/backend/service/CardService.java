package com.portfolio.backend.service;

import com.portfolio.backend.model.Card;
import com.portfolio.backend.repository.CardRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService implements ICardService {
    
    @Autowired
    CardRepository cardRepo;

    @Override
    public List<Card> getCards() {
        List<Card> cardList = cardRepo.findAll();
        return cardList;
    }

    @Override
    public Card getCard(Long id) {
        Card card = cardRepo.findById(id).orElse(null);
        return card;
    }

    @Override
    public void addCard(Card c) {
        cardRepo.save(c);
    }

    @Override
    public void deleteCard(Long id) {
        cardRepo.deleteById(id);
    }

    @Override
    public List<Card> getCards(List<Long> ids) {
        List<Card> cardList = cardRepo.findAllById(ids);
        return cardList;
    }

    @Override
    public void addCards(List<Card> cards) {
        cardRepo.saveAll(cards);
    }
    
}
