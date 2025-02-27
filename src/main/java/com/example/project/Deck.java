package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Card newCard = new Card(Utility.getRanks()[j], Utility.getSuits()[i]);
                cards.add(newCard);
            }
        }
    }

    public void shuffleDeck() { //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        Card drawn = cards.get();
        return drawn;
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

   


}