package com.example.project;
import java.util.ArrayList;


public class Game {
    // determining the winner of the game based on their cards
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards) {
        // initializing both players max cards in case they have the same hand ranking
        int maxP1 = 0;
        int maxP2 = 0;
        // acquiring both players hand rankings for comparison
        int p1HandRank = Utility.getHandRanking(p1.playHand(communityCards));
        int p2HandRank = Utility.getHandRanking(p2.playHand(communityCards));
        // comparing both players hand rankings
        if (p1HandRank > p2HandRank) {
            return "Player 1 wins!";
        } else if (p2HandRank > p1HandRank) {
            return "Player 2 wins!";
        } else {
            // if both players have the same hand rank, this checks each players maximum in their hand
            for (Card card : p1.getHand()) {
                maxP1 = Math.max(maxP1, Utility.getRankValue(card.getRank()));
            }
            for (Card card : p2.getHand()) {
                maxP2 = Math.max(maxP2, Utility.getRankValue(card.getRank()));
            }
            // if both players have the same high card and the same hand ranking, it is officially a tie
            if (maxP1 == maxP2) {
                return "Tie!";
            // if both have the same hand ranking, one player ultimately wins depending on who has the highest card in their hand
            } else if (maxP1 > maxP2) {
                return "Player 1 wins!";
            } else {
                return "Player 2 wins!";
            }
        }
    }

    public static void play() { //simulate card playing 
    }
}