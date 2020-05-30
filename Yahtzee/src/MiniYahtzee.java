import javax.swing.*;
import java.util.*;

/**
 * Lets the user play Yahtzee with five dice and keep score.
 */
public class MiniYahtzee {
    //This is the main thing where everything runs.
    public static void main(String[] args) {
        //Instance variables
        Winnings winnings = new Winnings();
        Window wi2 = new Window();
        int play = 1, scorea = 0, sum = 0;
        int[] wins = new int[15];

        //While the condition is true the code inside will continually loop
        while ((play == 1) && (sum < 15)) {
            sum = 0;
            int[] aDice = new int[] { 0, 0, 0, 0, 0 };// creates an array
            int roll = 0;
            int x, y, w, z;
            int rerolla = 0, rerollb = 03;
            Die die = new Die();
            for (x = 0; x < 5; x++) {
                die.roll();
                aDice[x] = die.get();// sets the dice values
            }

            //While the condition is true the code inside will continually loop, but even if it isn't true the code will still execute once
            do {
                 String b = wi2.in("Die 1: " + aDice[0] +"\nDie 2: " + aDice[1] +"\nDie 3: " + aDice[2] +"\nDie 4: " + aDice[3] +"\nDie 5: " + aDice[4]+ "\nHow many dice do you want to reroll? (0-5)");
                 rerolla = Integer.parseInt(b);
                if (rerolla > 0) {
                    int[] reroll = new int[rerolla];
                    for (y = 0; y < rerolla; y++) {
                        String a = wi2.in("Which dice would you like the change? (1-5)");
                        rerollb = Integer.parseInt(a);
                        reroll[y] = rerollb;
                    }
                    for (w = 0; w < rerolla; w++) {
                        if (reroll[w] == 1) {
                            die.roll();
                            aDice[0] = die.get();
                        }
                        if (reroll[w] == 2) {
                            die.roll();
                            aDice[1] = die.get();
                        }
                        if (reroll[w] == 3) {
                            die.roll();
                            aDice[2] = die.get();
                        }
                        if (reroll[w] == 4) {
                            die.roll();
                            aDice[3] = die.get();
                        }
                        if (reroll[w] == 5) {
                            die.roll();
                            aDice[4] = die.get();
                        }
                    }
                    roll++;
                    JOptionPane.showMessageDialog(null,"Die 1: " + aDice[0] +"\nDie 2: " + aDice[1] +"\nDie 3: " + aDice[2] +"\nDie 4: " + aDice[3] +"\nDie 5: " + aDice[4]);
                }
            } while ((roll < 2) && (rerolla > 0));

            Winnings prize = new Winnings();
            prize.checkWinnings(aDice, wins);
            wins[prize.choice() - 1] = 1;
            for (z = 0; z < 15; z++) {
                sum += wins[z];
            }
            scorea += prize.score();
            wi2.msg("Your total score is: " + scorea);
            if (winnings.endgame == 15) {
                wi2.msg("you have completed the game");
                System.exit(0);
            } else {
                play = 1;
            }
        }
    }
}

/* The class for handling each of the dice */
class Die {
    //Instance variables
    private int value;
    private Random rand;

    //Constructor
    public Die() {
        value = 0;
        rand = new Random();
    }

    public void roll() {
        value = 1 + rand.nextInt(6);
    }

    //Getter
    public int get() {
        return (value);
    }
}

/* The class for determining what you have (i.e. a pair, straight, etc) */
class Winnings {
    //Instance Variables
    int endgame = 0;
    private int score;
    private int choice;
    String one = "\n1 - Yahtzee";
    String two = "\n2 - Full House";
    String three = "\n3 - Large Straight";
    String four = "\n4- Small Straight";
    String five = "\n5- Four of a Kind";
    String six ="\n6 - Three of a Kind";
    String seven = "\n7 - Pair";
    String eight = "\n8 - Two Pair";
    String nine = "\n9 - Number of 1's";
    String ten = "\n10 - Number of 2's";
    String eleven = "\n11 - Number of 3's";
    String twelve = "\n12 - Number of 4's";
    String thirteen = "\n13 - Number of 5's";
    String fourteen = "\n14 - Number of 6's";
    String fifteen = "\n15 - Chance";

    //Constructor
    public Winnings() {
        score = 0;
    }

    //This method takes two int arrays and checks the winnings
    public void checkWinnings(int[] aDice, int[] wins) {
        Window w = new Window();
        w.msg("Which one do you want to see if you have?" + one +
                two + three + four + five + six + seven + eight +
                nine +ten +eleven +twelve +thirteen + fourteen +fifteen);

       String c = w.in("");
        choice = Integer.parseInt(c);
        int x = 0, y = 0, winings = 0, winingsa = 0;
        int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0;
        Arrays.sort(aDice);

        //Numbers
        for (y = 0; y < 5; y++) {
            if (aDice[y] == 1) {
                ones++;
            }
            if (aDice[y] == 2) {
                twos++;
            }
            if (aDice[y] == 3) {
                threes++;
            }
            if (aDice[y] == 4) {
                fours++;
            }
            if (aDice[y] == 5) {
                fives++;
            }
            if (aDice[y] == 6) {
                sixes++;
            }
        }

        //Straights
        if ((aDice[0] == aDice[1] - 1) && (aDice[1] == aDice[2] - 1)
                && (aDice[2] == aDice[3] - 1) && (aDice[3] == aDice[4] - 1)
                && (choice == 3)) {
            winingsa = 1;
        } else if ((ones > 0) && (twos > 0) && (threes > 0) && (fours > 0)) {
            winingsa = 2;
        } else if ((threes > 0) && (fours > 0) && (fives > 0) && (sixes > 0)) {
            winingsa = 2;
        } else if ((twos > 0) && (threes > 0) && (fours > 0) && (fives > 0)) {
            winingsa = 2;
        }

        //Pairs
        for (x = 0; x < 5; x++) {
            if (x != 0) {
                if ((aDice[0] == aDice[x])) {
                    winings++;
                }
            }
            if ((x != 0) && (x != 1)) {
                if ((aDice[1] == aDice[x])) {
                    winings++;
                }
            }
            if ((x != 0) && (x != 1) && (x != 2)) {
                if ((aDice[2] == aDice[x])) {
                    winings++;
                }
            }
            if ((x != 0) && (x != 1) && (x != 2) && (x != 3)) {
                if ((aDice[3] == aDice[x])) {
                    winings++;
                }
            }
        }

        //Winnings
        if ((winingsa == 1) && (choice == 3)) {
            w.msg("You have a Large Straight.");
            score = 40;
        } else if ((winingsa == 2) && (choice == 4)) {
            w.msg("You have a Small Straight.");
            score = 30;
        } else if ((winings == 10) && (choice == 1)) {
            w.msg("YAHTZEE!!");
            score = 50;
        } else if ((choice == 6) && (winings >= 3)) {
            w.msg("You have Three of a Kind.");
            score = aDice[0] + aDice[1] + aDice[2] + aDice[3] + aDice[4];
        } else if ((choice == 7) && (winings > 0)) {
            w.msg("You have a Pair.");
            score = 5;
        } else if ((winings == 2) && (choice == 8)) {
            w.msg("You have Two Pairs.");
            score = 10;
        } else if ((winings == 4) && (choice == 2)) {
            w.msg("You have a Full House.");
            score = 25;
        } else if ((winings >= 6) && (choice == 5)) {
            w.msg("You have Four of a Kind.");
            score = aDice[0] + aDice[1] + aDice[2] + aDice[3] + aDice[4];
        } else if (choice == 9) {
            w.msg("You have " + ones + " ones.");
            score = ones;
        } else if (choice == 10) {
            w.msg("You have " + twos + " twos.");
            score = twos * 2;
        } else if (choice == 11) {
            w.msg("You have " + threes + " threes.");
            score = threes * 3;
        } else if (choice == 12) {
            w.msg("You have " + fours + " fours.");
            score = fours * 4;
        } else if (choice == 13) {
            w.msg("You have " + fives + " fives.");
            score = fives * 5;
        } else if (choice == 14) {
            w.msg("You have " + sixes + " sixes.");
            score = sixes * 6;
        } else if (choice == 15) {
            score = aDice[0] + aDice[1] + aDice[2] + aDice[3] + aDice[4];
            w.msg("You have " + score + " points.");
        } else {
            w.msg("You got nothing!");
            score = 0;
        }
        if(choice == 1) {
            endgame++;
            one = null;
        }if(choice == 2){
            endgame++;
            two = null;
        }if(choice == 3){
            endgame++;
            three = null;
        }if(choice == 4){
            endgame++;
            four = null;
        }if(choice == 5){
            endgame++;
            five = null;
        }if(choice == 6){
            endgame++;
            six = null;
        }if(choice == 7){
            endgame++;
            seven = null;
        }if(choice == 8){
            endgame++;
            eight = null;
        }if(choice == 9){
            endgame++;
            nine = null;
        }if(choice == 10){
            endgame++;
            ten = null;
        }if(choice == 11){
            endgame++;
            eleven = null;
        }if(choice == 12){
            endgame++;
            twelve = null;
        }if(choice == 13){
            endgame++;
            thirteen = null;
        }if(choice == 14){
            endgame++;
            fourteen = null;
        }if(choice == 15){
            endgame++;
            fifteen = null;
        }
    }

    //getters
    public int score() {
        return (score);
    }
    public int choice() {
        return (choice);
    }
}