package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public void initializeDeck() { //hint.. use the utility class
        // i represents the 4 different suits and j represents the 13 different ranks
    }

    public void shuffleDeck() { //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.remove(0);
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }
}