import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.util.Random;
import java.io.*;
import java.lang.Math.*;
import java.util.Scanner;

public class Main {

    //PLAYER HP
    public static int player1Hp;
    public static int player2Hp;

    public static void main(String[] args) {
        //PLAYER 1 DECK CREATION
        ArrayList<Card> Deck1 = new ArrayList<>();

        Deck1.add(new Card("Pig", 1, 4, 1));
        Deck1.add(new Card("Goat", 2, 4, 1));
        Deck1.add(new Card("Rhino", 4, 4, 2));
        Deck1.add(new Card("Chicken", 2, 1, 1));
        Deck1.add(new Card("Frog", 2, 3, 2));
        Deck1.add(new Card("Ant", 1, 1, 1));
        Deck1.add(new Card("Dog", 2, 2, 1));
        Deck1.add(new Card("Fox", 2, 4, 1));
        Deck1.add(new Card("Monkey", 3, 3, 1));
        Deck1.add(new Card("Shark", 5, 2, 2));
        Deck1.add(new Card("Hawk", 1, 5, 2));

        //PLAYER 2 DECK CREATION
        ArrayList<Card> Deck2 = new ArrayList<>();

        Deck2.add(new Card("Octopus", 3, 5, 2));
        Deck2.add(new Card("Hippo", 3, 4, 2));
        Deck2.add(new Card("Snake", 2, 2, 1));
        Deck2.add(new Card("Fish", 1, 3, 1));
        Deck2.add(new Card("Cat", 2, 1, 1));
        Deck2.add(new Card("Ant", 1, 1, 1));
        Deck2.add(new Card("Dog", 2, 2, 1));
        Deck2.add(new Card("Beaver", 3, 2, 1));
        Deck2.add(new Card("Wasp", 3, 1, 1));
        Deck2.add(new Card("Wolf", 4, 3, 1));
        Deck2.add(new Card("Anteater", 2, 3, 1));

        //CREATE BOARD 2D ARRAY
        Card board[][] = new Card[2][4];

        player1Hp = 15;
        player2Hp = 15;
        int turns = 0;

        //PRINT BOARD
        printBoard2(board);

        //EACH PLAYER DRAWS FIVE CARDS FROM THEIR DECK
        ArrayList<Card> hand1 = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            int pick = (int) (Math.random() * Deck1.size());
            hand1.add(Deck1.remove(pick));
        }

        ArrayList<Card> hand2 = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            int pick = (int) (Math.random() * Deck2.size());
            hand2.add(Deck2.remove(pick));
        }

        //WHILE LOOP THAT RUNS AS LONG AS NO PLAYER'S HP IS EQUAL TO OR LESS THAN 0
        boolean play = true;

        while(play == true) {
            turns++;

            //PLAYER 1 TURN

            String[] options = new String[hand1.size()];
            for (int i = 0; i < hand1.size(); i++) {
                options[i] = hand1.get(i).getCardName() + "/" + hand1.get(i).getAttack() + "/" + hand1.get(i).getHealth();
            }

            String move;

            String displayBoard = returnBoard(board);

            //DISPLAYS CARD OPTIONS FOR PLAYER 1 TO PLAY
            move = (String) JOptionPane.showInputDialog(null, "Which card would you like to play?\n\n" + displayBoard + "\n\n",
                    "PLAYER 1", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String[] moveCut = move.split("/");

            int arraySize = 0;
            for (int i = 0; i < 4; i++) {
                if (board[1][i] == null) {
                    arraySize++;
                }
            }

            Integer[] spotOptions = new Integer[arraySize];
            if (arraySize > 0) {
                int j = 0;
                for (int i = 0; i < 4; i++) {
                    if (board[1][i] == null) {
                        spotOptions[j] = i + 1;
                        j++;
                    }
                }
            }

            // DISPLAYS OPEN LOCATIONS AVAILABLE TO BE PLAYED AT. THIS IS SKIPPED IF NO SPOTS ARE OPEN
            try {
                Integer spotOnBoard = (Integer) JOptionPane.showInputDialog(null, "Where would you like to play the " + moveCut[0] + "?\n\n" + displayBoard + "\n\n",
                        "PLAYER 1", JOptionPane.QUESTION_MESSAGE, null, spotOptions, spotOptions[0]);
                for (int i = 0; i < hand1.size(); i++) {
                    if (hand1.get(i).getCardName().equals(moveCut[0])) {
                        board[1][spotOnBoard - 1] = hand1.remove(i);
                        break;
                    }
                }
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "NO WHERE TO PLAY!",
                        "PLAYER 1", JOptionPane.ERROR_MESSAGE);
            }

            //PLAYER 1 DRAWS A CARD FROM THEIR DECK
            if(Deck1.size() > 0) {
                int p1pick = (int) (Math.random() * Deck1.size());
                hand1.add(Deck1.remove(p1pick));
            }

            //PRINT BOARD
            printBoard2(board);

            //PLAYER 2 TURN

            String[] options2 = new String[hand2.size()];
            for(int i = 0; i < hand2.size(); i++) {
                options2[i] = hand2.get(i).getCardName() + "/" + hand2.get(i).getAttack() + "/" +  hand2.get(i).getHealth();
            }

            String move2;

            String displayBoard2 = returnBoard(board);

            //DISPLAYS CARD OPTIONS FOR PLAYER 2 TO PLAY
            move2 = (String) JOptionPane.showInputDialog(null, "Which card would you like to play?\n\n" + displayBoard2 + "\n\n",
                    "PLAYER 2", JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);

            String[] moveCut2 = move2.split("/");

            int arraySize2 = 0;
            for(int i = 0; i < 4; i++) {
                if(board[0][i] == null) {
                    arraySize2++;
                }
            }

            Integer[] spotOptions2 = new Integer[arraySize2];
            if(arraySize2 > 0) {
                int j2 = 0;
                for (int i = 0; i < 4; i++) {
                    if (board[0][i] == null) {
                        spotOptions2[j2] = i + 1;
                        j2++;
                    }
                }
            }

            // DISPLAYS OPEN LOCATIONS AVAILABLE TO BE PLAYED AT. THIS IS SKIPPED IF NO SPOTS ARE OPEN
            try {
                Integer spotOnBoard2 = (Integer) JOptionPane.showInputDialog(null, "Where would you like to play the " + moveCut2[0] + "?\n\n" + displayBoard2 + "\n\n",
                        "PLAYER 2", JOptionPane.QUESTION_MESSAGE, null, spotOptions2, spotOptions2[0]);
                for (int i = 0; i < hand2.size(); i++) {
                    if (hand2.get(i).getCardName().equals(moveCut2[0])) {
                        board[0][spotOnBoard2 - 1] = hand2.remove(i);
                        break;
                    }
                }
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "NO WHERE TO PLAY!",
                        "PLAYER 2", JOptionPane.ERROR_MESSAGE);
            }

            //PLAYER 1 DRAWS A CARD FROM THEIR DECK
            if(Deck2.size() > 0) {
                int p2pick = (int) (Math.random() * Deck2.size());
                hand2.add(Deck2.remove(p2pick));
            }

            //PRINT BOARD
            printBoard2(board);

            JOptionPane.showMessageDialog(null, "CARDS ATTACK!!!",
                    "BATTLE PHASE", JOptionPane.PLAIN_MESSAGE, null);

            //System.out.println("\n\n--------------------BATTLE PHASE--------------------\n\n");

            battlePhase(board);

            JOptionPane.showMessageDialog(null, "\nPLAYER 1'S HP: " + player1Hp + "\n" + "PLAYER 2'S HP: " + player2Hp + "\n",
                    "HP REMAINING", JOptionPane.PLAIN_MESSAGE, null);

            //CHECKS IF EITHER PLAYER HAS 0 OR LESS HP
            if(player1Hp > 0 && player2Hp > 0) {
                printBoard2(board);
            }
            if(player1Hp <= 0 || player2Hp <= 0) {
                play = false;
            }
        }

        //WIN MESSAGE WHEN GAME IS OVER
        if(player1Hp <= 0 && player2Hp > 0) {
            JOptionPane.showMessageDialog(null, "PLAYER 2 WINS!",
                    "CONGRATULATIONS", JOptionPane.PLAIN_MESSAGE, null);
            //System.out.println("Player 2 wins!");
        }
        else if (player2Hp <= 0 && player1Hp > 0) {
            JOptionPane.showMessageDialog(null, "PLAYER 1 WINS!",
                    "CONGRATULATIONS", JOptionPane.PLAIN_MESSAGE, null);
            //System.out.println("Player 1 wins!");
        }
        else {
            JOptionPane.showMessageDialog(null, "Tie...",
                    "That's Awkward...", JOptionPane.PLAIN_MESSAGE, null);
            //System.out.println("Draw!");
        }

    }

    //FUNCTION TO MAKE THE CARDS BATTLE
    private static void battlePhase(Card[][] board) {
        for(int i = 0; i < board[0].length; i++) {
            if(board[0][i] != null && board[1][i] == null) {
                player1Hp -= board[0][i].getAttack();
            }
            else if(board[0][i] == null && board[1][i] != null) {
                player2Hp -= board[1][i].getAttack();
            }
            else if(board[0][i] != null && board[1][i] != null) {
                board[0][i].setHealth(board[0][i].getHealth() - board[1][i].getAttack());
                board[1][i].setHealth(board[1][i].getHealth() - board[0][i].getAttack());

                if(board[0][i].getHealth() <= 0 ) {
                    board[0][i] = null;
                }
                if(board[1][i].getHealth() <= 0 ) {
                    board[1][i] = null;
                }
            }
        }
    }

    //FUNCTION TO RETURN A LONG STRING OF BOARD INFORMATION TO BE PLACED IN A GUI ON LINES 85 AND 145
    static String returnBoard(Card[][] board) {
        StringBuilder sb = new StringBuilder();

        sb.append("PLAYER 1'S HP: " + player1Hp + "\n" + "PLAYER 2'S HP: " + player2Hp + "\n\n");

        for (Card temp : board[0]) {
            if(temp != null) {
                sb.append("[").append(temp.getCardName()).append("/").append(temp.getAttack()).append("/").append(temp.getHealth()).append("]");
            }
            else{
                sb.append("[           ]");
            }
        }
        sb.append("\n                         VS\n");
        for (Card temp : board[1]) {
            if(temp != null) {
                sb.append("[").append(temp.getCardName()).append("/").append(temp.getAttack()).append("/").append(temp.getHealth()).append("]");
            }
            else{
                sb.append("[           ]");
            }
        }

        String arrayAsString = sb.toString();

        return arrayAsString;
    }

    //FUNCTION TO PRINT THE BOARD MID-MOVES
    private static void printBoard2(Card[][] board) {

        StringBuilder sb = new StringBuilder();

        sb.append("PLAYER 1'S HP: " + player1Hp + "\n" + "PLAYER 2'S HP: " + player2Hp + "\n\n");

        for (Card temp : board[0]) {
            if(temp != null) {
                sb.append("[").append(temp.getCardName()).append("/").append(temp.getAttack()).append("/").append(temp.getHealth()).append("]");
            }
            else{
                sb.append("[           ]");
            }
        }
        sb.append("\n                         VS\n");
        for (Card temp : board[1]) {
            if(temp != null) {
                sb.append("[").append(temp.getCardName()).append("/").append(temp.getAttack()).append("/").append(temp.getHealth()).append("]");
            }
            else{
                sb.append("[           ]");
            }
        }

        String arrayAsString = sb.toString();


        JOptionPane.showMessageDialog(null, arrayAsString,
                "Board", JOptionPane.INFORMATION_MESSAGE);
    }
}