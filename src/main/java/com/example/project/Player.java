package com.example.project;
import java.util.ArrayList;


public class Player {
    private ArrayList<Card> hand; // hand
    private ArrayList<Card> allCards; // the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    // player constructor; each player has their own hand (2 cards) and allCards (their hand + communityCards)
    public Player() {
        hand = new ArrayList<Card>();
        allCards = new ArrayList<Card>();
    }

    // returns the players hand
    public ArrayList<Card> getHand() {return hand;}
    // returns all the players cards (hand + communityCards)
    public ArrayList<Card> getAllCards() {return allCards;}

    // adds Card c to hand
    public void addCard(Card c) {
        hand.add(c);
    }

    // returns the highest possible hand ranking for a player
    public String playHand(ArrayList<Card> communityCards) {
        // clearing allCards and adding the players hand and the community cards
        allCards.clear();
        allCards.addAll(hand);
        allCards.addAll(communityCards);
            sortAllCards();
            // checking for each hand ranking individually
            // checking for higher ranks before lower ranks because we want to ensure the highest rank possible is returned
            if (isRoyalFlush()) {
                return ("Royal Flush");
            } else if (isStraightFlush()) {
                return ("Straight Flush");
            } else if (isQuads()) {
                return ("Four of a Kind");
            } else if (isFullHouse()) {
                return ("Full House");
            } else if (isFlush()) {
                return ("Flush");
            } else if (isStraight()) {
                return ("Straight");
            } else if (isThreeOfAKind()) {
                return ("Three of a Kind");
            } else if (isTwoPair()) {
                return ("Two Pair");
            } else if (isPair()) {
                return ("A Pair");
            } else if (isHighCard()) {
                return ("High Card");
            } else {
                return ("Nothing");
            }
    }

    public void sortAllCards() {
        // utilizing Insertion sort to sort all the cards
        // used the same thing that I used in the Insertion sort lab, just with different names to match this code
        int indexOne = 0;
        int indexTwo = 1;
        // initializing two variables for both indices that will be compared
        // iterating through allCards to sort it
        for (int i = 1; i < allCards.size(); i++) {
            // originalOne and originalTwo will ensure that all indices of allCards is checked and sorted
            int originalOne = indexOne;
            int originalTwo = indexTwo;
            // while indexTwo is less than indexOne, they get swapped
            // if either one is greater than 0, we subtract 1 from it to ensure every single index is checked
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

    // this method finds the frequency of each rank in allCards and returns it in the form of an arraylist
    public ArrayList<Integer> findRankingFrequency() {
        // initializing a frequency arraylist
        ArrayList<Integer> rankFrequency = new ArrayList<Integer>();
        // iterating through ranks and setting the frequency arraylist all to 0 to make sure it has the correct amount of indices
        for (int i = 0; i < ranks.length; i++) {
            rankFrequency.add(0);
        }
        // nested-for loop through allCards and ranks
        // allCards ensures each card is checked and its rank is added
        // ranks ensures each rank is checked and added if found in allCards
            for (int i = 0; i < allCards.size(); i++) {
                for (int j = 0; j < ranks.length; j++) {
                    if (allCards.get(i).getRank().equals(ranks[j]))  {
                        // if a match is found, the index where the frequency of that rank is found has 1 added to it
                        rankFrequency.set(j, rankFrequency.get(j) + 1);
                    }
                }
            }
        return rankFrequency; 
    }

    // this method finds the frequency of each suit in allCards and returns it in the form of an arraylist
    public ArrayList<Integer> findSuitFrequency() {
        // initializing a frequency arraylist
        ArrayList<Integer> suitFrequency = new ArrayList<Integer>();
        // iterating through suits and adding 0 to make sure there are the correct amount of indices
        for (int i = 0; i < suits.length; i++) {
            suitFrequency.add(0);
        }
        // nested-for loop through allCards and suits
        // allCards ensures each card is checked
        // suits ensures each suit is checked
            for (int i = 0; i < allCards.size(); i++) {
                for (int j = 0; j < suits.length; j++) {
                    if (allCards.get(i).getSuit().equals(suits[j])) {
                        // if a match is found, the index where the suit is found has 1 added to it
                        suitFrequency.set(j, suitFrequency.get(j) + 1);
                    }
                }
            }
        return suitFrequency;  
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }

    public boolean isHighCard() {
        // initializing highCard to the last element of allCards because the cards will be sorted
        Card highCard = allCards.get(allCards.size() - 1);
        // initializing isHighCard to false
        boolean isHighCard = false;
        // iterating through the players hand
        for (int i = 0; i < hand.size(); i++) {
            // if the highCard is found in the players hand then the boolean is set to true because the player has the high card, not the community cards
            if (hand.get(i).getRank().equals(highCard.getRank())) {
                isHighCard = true;
            }
        }
        return isHighCard;
    }

    // returns a boolean based on if a pair is found in allCards
    public boolean isPair() {
        // a frequency arraylist is initialized to count the rank frequencies in allCards
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        frequency = findRankingFrequency();
        // iterating through every rank's frequency
        for (int i = 0; i < frequency.size(); i++) {
            // if one rank has a frequency of 2 that indicates a pair so we return true
            if (frequency.get(i) == 2) {
                return true;
            }
        }
        return false;
    }

    // returns a boolean based on if two pairs are found in allCards
    public boolean isTwoPair() {
        // frequency arraylist initalized to count the rank frequencies
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        frequency = findRankingFrequency();
        // numPairs is initialized to count the ranks that have frequencies of 2
        int numPairs = 0;
        // iterating through frequency
        for (int i = 0; i < frequency.size(); i++) {
            if (frequency.get(i) == 2) {
                numPairs++;
            }
        }
        // if there are two pairs, i.e. two ranks each having a frequency of 2, true is returned
        if (numPairs == 2) {
            return true;
        }
        return false;
    }

    // returns a boolean based on if a rank is found to have a frequency of 3
    public boolean isThreeOfAKind() {
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        frequency = findRankingFrequency();
        for (int i = 0; i < frequency.size(); i++) {
            // if a rank frequency of 3 is found, true is returned
            if (frequency.get(i) == 3) {
                return true;
            }
        }
        return false;
    }

    // returns a boolean based on if allCards has 5 cards in order based on rank
    public boolean isStraight() {
        // sorting allCards because a straight is easily detected when it is in order (that is what a straight is supposed to be)
        sortAllCards();
        // times is initialized as a placeholder to use while making sure each subsequent rank is 1 ahead of another
        int times = 0;
        // iterating through allCards
        for (int i = 0; i < allCards.size() - 1; i++) {
            if (Utility.getRankValue(allCards.get(i + 1).getRank()) == (Utility.getRankValue(allCards.get(i).getRank()) + 1)) {
                times++;
            } else {
                return false;
            }
        }
        // if all ranks are 1 ahead of another, true is returned
        return true;
    }

    // returns a boolean based on if every card in allCards has the same suit
    public boolean isFlush() {
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        frequency = findSuitFrequency();
        for (int i = 0; i < frequency.size(); i++) {
            // if one suit has all 5 cards then true is returned, otherwise false
            if (frequency.get(i) == 5) {
                return true;
            }
        }
        return false;
    }

    // returns a boolean based on if there is a threeOfAKind and a Pair both in allCards
    public boolean isFullHouse() {
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        boolean threeRank = false;
        boolean twoRank = false;
        frequency = findRankingFrequency();
        // iterating through the rank frequencies
        for (int i = 0; i < frequency.size(); i++) {
            // checking for threeOfAKind
            if (frequency.get(i) == 3) {
                threeRank = true;
            }
            // checking for a Pair
            if (frequency.get(i) == 2) {
                twoRank = true;
            }
        }
        // if it has both then true is returned
        if (threeRank && twoRank) {
            return true;
        }
        return false;
    }

    // returns a boolean based on if there is 4 occurrences of one rank in allCards
    public boolean isQuads() {
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        frequency = findRankingFrequency();
        for (int i = 0; i < frequency.size(); i++) {
            // checks for 4 in every frequency
            if (frequency.get(i) == 4) {
                return true;
            }
        }
        return false;
    }

    // returns a boolean based on if there is a straight (cards are in order) and a flush (all cards are the same suit) at the same time
    public boolean isStraightFlush() {
        if (isStraight() && isFlush()) {
            return true;
        }
        return false;
    }

    // returns a boolean based on if the cards are in order from 10 to A and they are all the same suit
    public boolean isRoyalFlush() {
        boolean is10ToAce = false;
        // checking for a straight and checking if allCards begins at rank value 10
        if (isStraight() && allCards.get(0).getRank().equals("10")) {
            is10ToAce = true;
        }
        // if a straight is found from 10 to Ace and all cards are the same suit, true is returned
        if (is10ToAce && isFlush()) {
            return true;
        }
        return false;
    }
}
