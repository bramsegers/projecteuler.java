package P101_P150;

import util.Util;

/*
 * A hexagonal tile with number 1 is surrounded by a ring of six hexagonal tiles, 
 * starting at "12 o'clock" and numbering the tiles 2 to 7 in an anti-clockwise 
 * direction. New rings are added in the same fashion, with the next rings being 
 * numbered 8 to 19, 20 to 37, 38 to 61, and so on.
 * 
 * By finding the difference between tile n and each its six neighbours we shall 
 * define PD(n) to be the number of those differences which are prime.
 * 
 * For example, working clockwise around tile 8 the differences are 12, 29, 11, 
 * 6, 1, and 13. So PD(8) = 3. In the same way, the differences around tile 17 
 * are 1, 17, 16, 1, 11, and 10, hence PD(17) = 2.
 * 
 * It can be shown that the maximum value of PD(n) is 3. If all of the tiles for 
 * which PD(n) = 3 are listed in ascending order to form a sequence, the 10th tile 
 * would be 271.
 * 
 * Find the 2000th tile in this sequence.
 */
public class P128 {

    public static void main(String[] args) {
        System.out.println(P128.solve(2000));
    }

    public static long solve(int limit) {
        int count = 1;
        long n = 0;
        long number = 0;
        while (count < limit) {
            n++;
            if (Util.isPrime(6 * n - 1) && Util.isPrime(6 * n + 1) && Util.isPrime(12 * n + 5)) {
                count++;
                number = (3 * n * n - 3 * n + 2);
            }
            if (count < limit && n != 1 && Util.isPrime(6 * n + 5) && Util.isPrime(6 * n - 1) && Util.isPrime(12 * n - 7)) {
                count++;
                number = (3 * n * n + 3 * n + 1);
            }
        }
        System.out.println("The " + limit + "th number is " + number);
        return number;
    }
}
