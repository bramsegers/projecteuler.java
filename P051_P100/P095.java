package P051_P100;

import java.util.ArrayList;
import java.util.List;

/*
 * The proper divisors of a number are all the divisors excluding the number itself. 
 * Interestingly the sum of the proper divisors of 220 is 284 and the sum of the 
 * proper divisors of 284 is 220, forming a chain of two numbers. For this reason, 
 * 220 and 284 are called an amicable pair.
 * 
 * Perhaps less well known are longer chains. For example, starting with 12496, 
 * we form the chain: 12496 -> 14288 -> 15472 -> 14536 -> 14264 ( -> 12496... )
 * Since this chain returns to its starting point, it is called an amicable chain.
 * 
 * Find the smallest member of the longest amicable chain with no element exceeding one million.
 */
public class P095 {

    private static int solve(int nMax) {
        int[] divSum = new int[nMax];
        for (int i = 1; i < nMax; i++) {
            for (int j = i * 2; j < nMax; j += i) {
                divSum[j] += i;
            }
        }
        int maxNum = 0, maxLen = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i < nMax; i++) {
            list.clear();
            int last = i;
            while (last < nMax && !list.contains(last)) {
                list.add(last);
                last = divSum[last];
            }
            if (last == list.get(0)) {
                System.out.println(list.size() + " " + list);
                if (list.size() > maxLen) {
                    maxLen = list.size();
                    maxNum = i;
                }
            }
        }
        System.out.println("Max chain length " + maxLen + " found at " + maxNum);
        return maxNum;
    }

    public static void main(String[] args) {
        System.out.println(P095.solve(1000000));
    }
}
