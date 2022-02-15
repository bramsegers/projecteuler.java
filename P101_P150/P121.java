package P101_P150;

/*
 * A bag contains one red disc and one blue disc. In a game of chance a player takes 
 * a disc at random and its colour is noted. After each turn the disc is returned 
 * to the bag, an extra red disc is added, and another disc is taken at random.
 * 
 * The player pays £1 to play and wins if they have taken more blue discs 
 * than red discs at the end of the game.
 * 
 * If the game is played for four turns, the probability of a player winning is 
 * exactly 11/120, and so the maximum prize fund the banker should allocate for 
 * winning in this game would be £10 before they would expect to incur a loss. 
 * Note that any payout will be a whole number of pounds and also includes the 
 * original £1 paid to play the game, so in the example given the player actually wins £9.
 * 
 * Find the maximum prize fund that should be allocated to a single game in which fifteen turns are played.
 */
public class P121 {

    public static long solve(long turns) {
        // p(rbbb)  1/2 * 1/3 * 1/4 * 1/5  = 1/120
        // p(brbb)  1/2 * 2/3 * 1/4 * 1/5  = 2/120
        // p(bbrb)  1/2 * 1/3 * 3/4 * 1/5  = 3/120
        // p(bbbr)  1/2 * 1/3 * 1/4 * 4/5  = 4/120
        // p(bbbb)  1/2 * 1/3 * 1/4 * 1/5  = 1/120
        // p(win) =  11/120
        // p(loss) = 109/120
        // 120x spelen = 11x uitkeren en 120x 1 euro innen 
        // max 120 euro uitkeren in 11 winstpartijen = 10 euro per partij
        // max payout = 10 euro
        long win = 0;
        for (long i = 0; i < Math.pow(2, turns); i++) {
            if (2 * Long.bitCount(i) > turns) {
                long j = i;
                long prod = 1;
                for (long k = 0; k < turns; k++) {
                    prod *= (j & 1) == 1 ? 1 : k + 1;
                    j /= 2;
                }
                win += prod;
            }
        }
        long fac = 1;
        for (long i = 2; i <= turns + 1; i++) {
            fac *= i;
        }
        System.out.println("P(win) = " + win + "/" + fac);
        System.out.println("payout = " + fac / win);
        return fac / win;
    }

    public static void main(String[] args) {
        System.out.println(P121.solve(15));
    }
}
