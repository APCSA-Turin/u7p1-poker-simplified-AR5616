package com.example.project;
import java.util.ArrayList;


public class Player {
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player() {
        hand = new ArrayList<>();
        allCards = new ArrayList<Card>();
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
        int indexOne = 0;
        int indexTwo = 1;
        for (int i = 1; i < allCards.size(); i++) {
            int originalOne = indexOne;
            int originalTwo = indexTwo;
            while (Utility.getRankValue(allCards.get(indexTwo).getRank()) < Utility.getRankValue(allCards.get(indexOne).getRank())) {
                Card swap = allCards.get(indexTwo);
                allCards.set(indexTwo, allCards.get(indexOne));
                allCards.set(indexOne, swap);
                if (indexTwo > 0) {
                    indexTwo--;
                }
                if (indexOne > 0) {
                    indexOne--;
                }
            }
            indexOne = originalOne + 1;
            indexTwo = originalTwo + 1;
        }
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
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        frequency = findRankingFrequency();
        for (int i = 0; i < allCards.size(); i++) {
            if (frequency.get(i) == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isTwoPair() {
        int numPairs = 0;
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = i + 1; j < allCards.size(); j++) {
                if (Utility.getRankValue(allCards.get(i).getRank()) == Utility.getRankValue(allCards.get(j).getRank())) {
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
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        frequency = findRankingFrequency();
        for (int i = 0; i < allCards.size(); i++) {
            if (frequency.get(i) == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean isStraight() {
        sortAllCards();
        int times = 0;
        for (int i = 0; i < allCards.size() - 1; i++) {
            if (Utility.getRankValue(allCards.get(i + 1).getRank()) == (Utility.getRankValue(allCards.get(i).getRank()) + 1)) {
                times++;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isFlush() {
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        frequency = findSuitFrequency();
        for (int i = 0; i < allCards.size(); i++) {
            if (frequency.get(i) == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean isFullHouse() {
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        boolean threeRank = false;
        boolean twoRank = false;
        frequency = findRankingFrequency();
        for (int i = 0; i < allCards.size(); i++) {
            if (frequency.get(i) == 3) {
                threeRank = true;
            }
            if (frequency.get(i) == 2) {
                twoRank = true;
            }
        }
        if (threeRank && twoRank) {
            return true;
        }
        return false;
    }

    public boolean isQuads() {
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        frequency = findRankingFrequency();
        for (int i = 0; i < allCards.size(); i++) {
            if (frequency.get(i) == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean isStraightFlush() {
        if (isStraight() && isFlush()) {
            return true;
        }
        return false;
    }

    public boolean isRoyalFlush() {
        boolean is
        return true;
    }
}
