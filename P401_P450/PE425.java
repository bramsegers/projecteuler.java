package P401_P450;

import java.util.Arrays;
import java.util.BitSet;
import java.util.PriorityQueue;
import java.util.Queue;
import util.Util;

public final class PE425 {

    public static void main(String[] args) {
        new PE425().solve(10000000);
    }

    /* 
     * Finding all the relatives of 2 can be seen as a single-source shortest path problem,
     * which we solve here using Dijkstra's algorithm. The key insight is that at each node (prime number),
     * we consider the connection path from 2 to it, and store the maximum path number at the node.
     * It is amenable to dynamic programming because it's always best to minimize the maximum path number.
     * 
     * For example, 2 is connected to 103 because 2 <-> 3 <-> 13 <-> 113 <-> 103.
     * The maximum number along this path is 113, and among all paths
     * this is the minimum possible maximum, so 103 is not a relative of 2.
     */
    public void solve(int limit) {
        BitSet primes = Util.getPrimesBS(limit);
        // pathMax[i] = Integer.MAX_VALUE if i is not prime or i is not connected to 2.
        // Otherwise, considering all connection paths from 2 to i and for each path computing
        // the maximum number, pathMax[i] is the minimum number among all these maxima.
        int[] pathMax = new int[limit];
        Arrays.fill(pathMax, Integer.MAX_VALUE);
        // Process paths in increasing order of maximum number
        Queue<IntPair> queue = new PriorityQueue<>();
        queue.add(new IntPair(2, 2));
        while (!queue.isEmpty()) {
            IntPair item = queue.remove();
            int n = item.b;
            int pmax = item.a;
            if (pmax >= pathMax[n]) {
                // This happens if at the time this update was queued, a better
                // or equally good update was queued ahead but not processed yet
                continue;
            }
            // Update the target node and explore neighbors
            pathMax[n] = pmax;
            // Try all replacements of a single digit, including the leading zero.
            // This generates exactly all (no more, no less) the ways that a number m is connected to n.
            int[] digits = toDigits(n);
            int[] tempDigits = digits.clone();
            for (int i = 0; i < tempDigits.length; i++) {  // For each digit position
                for (int j = 0; j < 10; j++) {  // For each digit value
                    tempDigits[i] = j;
                    int m = toNumber(tempDigits);
                    int nextPmax = Math.max(m, pmax);
                    if (m < limit && primes.get(m) && nextPmax < pathMax[m]) {
                        queue.add(new IntPair(nextPmax, m));
                    }
                }
                tempDigits[i] = digits[i];  // Restore the digit
            }
        }

        long sum = 0;
        for (int i = 0; i < limit; i++) {
            if (primes.get(i) && pathMax[i] > i) {
                sum += i;
            }
        }
        System.out.format("P(%d)=%d%n",limit,sum);
    }

    // Returns the given non-negative integer as an array of digits, in big endian, with an extra leading zero.
    // e.g. 0 -> {0,0}; 1 -> {0,1}; 8 -> {0,8}; 42 -> {0,4,2}; 596 -> {0,5,9,6}.
    private static int[] toDigits(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        // Extract base-10 digits in little endian
        int[] temp = new int[11];
        int len = 0;
        do {
            temp[len] = n % 10;
            n /= 10;
            len++;
        } while (n > 0);
        // Trim and reverse
        int[] result = new int[len + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = temp[len - i];
        }
        return result;
    }

    private static int toNumber(int[] digits) {
        int result = 0;
        for (int x : digits) {
            result = result * 10 + x;
        }
        return result;
    }

    private static class IntPair implements Comparable<IntPair> {

        public final int a;
        public final int b;

        public IntPair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(IntPair other) {
            if (a < other.a) {
                return -1;
            } else if (a > other.a) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
