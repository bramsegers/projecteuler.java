package P051_P100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import util.Util;

/*
 In the card game poker, a hand consists of five cards and are ranked, 
 from lowest to highest, in the following way:
 - High Card: Highest value card.
 - One Pair: Two cards of the same value.
 - Two Pairs: Two different pairs.
 - Three of a Kind: Three cards of the same value.
 - Straight: All cards are consecutive values.
 - Flush: All cards of the same suit.
 - Full House: Three of a kind and a pair.
 - Four of a Kind: Four cards of the same value.
 - Straight Flush: All cards are consecutive values of same suit.
 - Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
 
 The cards are valued in the order:
 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.

 If two players have the same ranked hands then the rank made up of the highest 
 value wins. But if two ranks tie, for example, both players have a pair of queens, 
 then highest cards in each hand are compared. If the highest cards tie then 
 the next highest cards are compared, and so on.

 The file, P054.txt, contains one-thousand random hands dealt to two players. 
 Each line of the file contains ten cards. The first five are Player 1's cards 
 and the last five are Player 2's cards. In each hand there is a clear winner.

 How many hands does Player 1 win?
 */
public class P054 {

    public static int solve(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            String[] cards = line.split(" ");
            Hand h1 = new Hand(1, cards[0], cards[1], cards[2], cards[3], cards[4]);
            Hand h2 = new Hand(2, cards[5], cards[6], cards[7], cards[8], cards[9]);
            boolean h1Wins = h1.wins(h2);
            sum += h1Wins ? 1 : 0;
            System.out.println("Winner: " + (h1Wins ? "P1" : "P2"));
        }
        return sum;
    }

    private static class Hand {

        private static final Map<Character, Integer> map = new HashMap<>();

        static {
            map.put('2', 2);
            map.put('3', 3);
            map.put('4', 4);
            map.put('5', 5);
            map.put('6', 6);
            map.put('7', 7);
            map.put('8', 8);
            map.put('9', 9);
            map.put('T', 10);
            map.put('J', 11);
            map.put('Q', 12);
            map.put('K', 13);
            map.put('A', 14);
        }
        private static final String[] rankName = {
            "High Card ",
            "One Pair  ",
            "Two pairs ",
            "3 Of A K  ",
            "Straight  ",
            "Flush     ",
            "Full H    ",
            "4 Of A K  ",
            "Straight F"
        };
        private String[] cards;
        private int rank = 0;
        private List<Integer> hiValues;
        /*
         rank   hiValues 
         0  x   x        - High Card: Highest value card.
         1  x   x        - One Pair: Two cards of the same value.
         2  x   x        - Two Pairs: Two different pairs.
         3  x   x        - Three of a Kind: Three cards of the same value.
         4  x   x        - Straight: All cards are consecutive values.
         5  x   x        - Flush: All cards of the same suit.
         6  x   x        - Full House: Three of a kind and a pair.
         7  x   x        - Four of a Kind: Four cards of the same value.
         8  x   x        - Straight Flush: All cards are consecutive values of same suit.
         8  x   x        - Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
         */

        public Hand(int p, String... c) {
            cards = c;
            checkFlush();
            String out = "P" + p + ": " + Arrays.toString(cards) + " " + rankName[rank] + " " + hiValues;
            while (out.length() < 60) {
                out += " ";
            }
            System.out.print(out);
        }

        public boolean wins(Hand h2) {
            if (rank == h2.rank) {
                for (int i = hiValues.size() - 1; i >= 0; i--) {
                    if (!Objects.equals(hiValues.get(i), h2.hiValues.get(i))) {
                        return hiValues.get(i) > h2.hiValues.get(i);
                    }
                }
                // no clear winner
                System.exit(0);
            }
            return rank > h2.rank;
        }

        private void checkFlush() {
            Set<Character> suits = new HashSet<>();
            hiValues = new ArrayList<>();
            for (String card : cards) {
                suits.add(card.charAt(1));
                hiValues.add(map.get(card.charAt(0)));
            }
            if (suits.size() == 1) {
                Collections.sort(hiValues);
                if (hiValues.get(4) - hiValues.get(0) == 4) {
                    // straight flush
                    rank = 8;
                } else {
                    // flush
                    rank = 5;
                }
            } else {
                checkOfAKind();
            }
        }

        private void checkOfAKind() {
            Map<Integer, Integer> values = new HashMap<>();
            for (String card : cards) {
                int val = map.get(card.charAt(0));
                Integer i = values.get(val);
                values.put(val, i == null ? 1 : i + 1);
            }
            if (values.values().contains(3) && values.values().contains(2)) {
                // full house
                rank = 6;
                int value = 0;
                for (int val : values.keySet()) {
                    if (values.get(val) == 3) {
                        value = val;
                    }
                }
                hiValues = new ArrayList<>();
                for (String card : cards) {
                    int v = map.get(card.charAt(0));
                    if (v != value) {
                        hiValues.add(v);
                    }
                }
                for (String card : cards) {
                    int v = map.get(card.charAt(0));
                    if (v == value) {
                        hiValues.add(v);
                    }
                }
            } else if (values.values().contains(4) && values.values().contains(1)) {
                // four of a kind
                rank = 7;
                int value = 0;
                for (int val : values.keySet()) {
                    if (values.get(val) == 4) {
                        value = val;
                    }
                }
                hiValues = new ArrayList<>();
                for (String card : cards) {
                    int v = map.get(card.charAt(0));
                    if (v != value) {
                        hiValues.add(v);
                    }
                }
                for (String card : cards) {
                    int v = map.get(card.charAt(0));
                    if (v == value) {
                        hiValues.add(v);
                    }
                }
            } else {
                if (!checkStraight()) {

                    if (values.values().contains(3)) {
                        // three of a kind
                        rank = 3;
                        int value = 0;
                        for (int val : values.keySet()) {
                            if (values.get(val) == 3) {
                                value = val;
                            }
                        }
                        hiValues = new ArrayList<>();
                        for (String card : cards) {
                            int v = map.get(card.charAt(0));
                            if (v != value) {
                                hiValues.add(v);
                            }
                        }
                        for (String card : cards) {
                            int v = map.get(card.charAt(0));
                            if (v == value) {
                                hiValues.add(v);
                            }
                        }
                        int c1 = hiValues.get(0);
                        int c2 = hiValues.get(1);
                        hiValues.set(0, Math.min(c1, c2));
                        hiValues.set(1, Math.max(c1, c2));
                    } else if (values.size() == 3) {
                        // two pairs
                        rank = 2;
                        int value = 0;
                        for (int val : values.keySet()) {
                            if (values.get(val) == 1) {
                                value = val;
                            }
                        }
                        hiValues = new ArrayList<>();
                        for (String card : cards) {
                            int v = map.get(card.charAt(0));
                            if (v != value) {
                                hiValues.add(v);
                            }
                        }
                        Collections.sort(hiValues);
                        for (String card : cards) {
                            int v = map.get(card.charAt(0));
                            if (v == value) {
                                hiValues.add(0, v);
                            }
                        }
                    } else if (values.size() == 4) {
                        // one pair
                        rank = 1;
                        int value = 0;
                        for (int val : values.keySet()) {
                            if (values.get(val) == 2) {
                                value = val;
                            }
                        }
                        hiValues = new ArrayList<>();
                        for (String card : cards) {
                            int v = map.get(card.charAt(0));
                            if (v != value) {
                                hiValues.add(v);
                            }
                        }
                        Collections.sort(hiValues);
                        for (String card : cards) {
                            int v = map.get(card.charAt(0));
                            if (v == value) {
                                hiValues.add(v);
                            }
                        }
                    } else if (values.size() == 5) {
                        // hi card
                        rank = 0;
                    }
                }
            }
        }

        public boolean checkStraight() {
            hiValues = new ArrayList<>();
            for (String card : cards) {
                hiValues.add(map.get(card.charAt(0)));
            }
            Collections.sort(hiValues);
            for (int i = 0; i < hiValues.size() - 1; i++) {
                if (hiValues.get(i) + 1 != hiValues.get(i + 1)) {
                    return false;
                }
            }
            // straight
            rank = 4;
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(P054.solve(Util.readText("files/P054.txt")));
    }
}
