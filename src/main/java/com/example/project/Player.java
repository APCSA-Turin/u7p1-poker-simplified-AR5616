package com.example.project;
import java.util.ArrayList;


public class Player {
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {return hand;}
    public ArrayList<Card> getAllCards() {return allCards;}

    public void addCard(Card c) {
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards) {
        allCards.add(hand.get(0));
        allCards.add(hand.get(1));
        allCards.add(communityCards.get(0));
        allCards.add(communityCards.get(1));   
        allCards.add(communityCards.get(2));     

        return "Nothing";
    }

    public void sortAllCards() {
        
    } 

    public ArrayList<Integer> findRankingFrequency() {
        ArrayList<Integer> rankFrequency = new ArrayList<Integer>();
        int frequency = 0;
        for (int i = 2; i <= 14; i++) {
            for (int j = 0; j < allCards.size(); j++) {
                if (allCards.get(j).getRank() == Integer.toString(i)) {
                    frequency++;
                }
            }
            rankFrequency.add(frequency);
            frequency = 0;
        }
        return rankFrequency; 
    }

    public ArrayList<Integer> findSuitFrequency() {
        ArrayList<Integer> suitFrequency = new ArrayList<Integer>();
        int frequency = 0;
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < allCards.size(); j++) {
                if (allCards.get(j).getSuit() == suits[i]) {
                    frequency++;
                }
            }
            suitFrequency.add(frequency);
            frequency = 0;
        }
        return suitFrequency;  
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }

    public boolean isPair() {
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = 1; j < allCards.size(); j++) {
                if (allCards.get(i) == allCards.get(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isTwoPair() {
        int numPairs = 0;
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = 1; j < allCards.size(); j++) {
                if (allCards.get(i) == allCards.get(j)) {
                    numPairs++;
                }
            }
        }
        if (numPairs == 2) {
            return true;
        }
        return false;
    }

    public boolean isThreeOfAKind() {
        int numRepetitions = 0;
        int maxReps = 0;
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = 1; j < allCards.size(); j++) {
                if (allCards.get(i) == allCards.get(j)) {
                    numRepetitions++;
                }
            }
            if (numRepetitions > maxReps) {
                maxReps = numRepetitions;
            }
            numRepetitions = 0;
        }
        if (maxReps == 3) {
            return true;
        }
        return false;
    }

    public boolean isStraight() {
        sortAllCards();
        
        return true;
    }

    



}
