package P101_P150;

import java.util.ArrayList;
import java.util.List;
import util.Util;

/*
 * Considering 4-digit primes containing repeated digits it is clear that they 
 * cannot all be the same: 1111 is divisible by 11, 2222 is divisible by 22, 
 * and so on. But there are nine 4-digit primes containing three ones:
 * 1117, 1151, 1171, 1181, 1511, 1811, 2111, 4111, 8111
 * 
 * We shall say that M(n,d) represents the maximum number of repeated digits 
 * for an n-digit prime where d is the repeated digit, N(n,d) represents the 
 * number of such primes, and S(n,d) represents the sum of these primes.
 * 
 * So M(4,1) = 3 is the maximum number of repeated digits for a 4-digit prime 
 * where one is the repeated digit, there are N(4,1) = 9 such primes, and the 
 * sum of these primes is S(4,1) = 22275. It turns out that for d = 0, it is only 
 * possible to have M(4,0) = 2 repeated digits, but there are N(4,0) = 13 such cases.
 * 
 * In the same way we obtain the following results for 4-digit primes.
 * Digit d    M(4,d)    N(4,d)      S(4,d)
 *       0        2        13	    67061
 *       1	  3	    9	    22275
 *       2	  3	    1	     2221
 *       3	  3	   12	    46214
 *       4	  3	    2	     8888
 *       5	  3	    1	     5557
 *       6	  3	    1	     6661
 *       7	  3	    9	    57863
 *       8	  3	    1	     8887
 *       9	  3	    7	    48073
 * For d = 0 to 9, the sum of all S(4,d) is 273700.
 * 
 * Find the sum of all S(10,d).
 */
public class P111 {

    public static long solve(int dig) {
        long totSum = 0;
        System.out.format("d\tM(%d,d)\tN(%d,d)\tS(%d,d)%n", dig, dig, dig);
        for (int d = 0; d < 10; d++) {
            long sum = 0, count = 0;
            int diff = 0;
            while (count == 0) {
                diff++;
                long pMin = (long) Math.pow(10, dig - 1);
                List<List<Integer>> choose = new ArrayList<>();
                Util.choose(dig, diff, choose);
                for (List<Integer> list : choose) {
                    System.out.println(list);
                    for (int i = 0; i < Math.pow(9, diff); i++) {
                        int[] arr = new int[diff];
                        int k = i;
                        for (int j = 0; j < diff; j++) {
                            int mod = k % 9;
                            arr[j] = mod >= d ? mod + 1 : mod;
                            k /= 9;
                        }
                        long p = 0;
                        k = 0;
                        for (int j = 0; j < dig; j++) {
                            p *= 10;
                            if (list.contains(j + 1)) {
                                p += arr[k++];
                            } else {
                                p += d;
                            }
                        }
                        if (p > pMin && Util.isPrime(p)) {
                            sum += p;
                            count++;
                        }
                    }
                }
            }
            totSum += sum;
            System.out.format("%d\t%d\t%d\t%d%n", d, dig - diff, count, sum);
        }
        return totSum;
    }

    public static void main(String[] args) {
        System.out.println(P111.solve(4));
    }
}
