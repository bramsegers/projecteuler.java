package P101_P150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * In a triangular array of positive and negative integers, we wish to find a 
 * sub-triangle such that the sum of the numbers it contains is the smallest possible.
 * 
 * We wish to make such a triangular array with one thousand rows, so we generate 500500 
 * pseudo-random numbers sk in the range -2^19 to 2^19, using a type of random number 
 * generator as follows:
 * 
 * for k = 1 up to k = 500500
 *  t := (615949*t + 797807) modulo 2^20 
 *  sk := t - 2^19
 * 
 * Thus: s1 = 273519, s2 = -153582, s3 = 450905 etc
 * 
 * Sub-triangles can start at any element of the array and extend down as far as we like 
 * The "sum of a sub-triangle" is defined as the sum of all the elements it contains.
 * Find the smallest possible sub-triangle sum.
 */
public class P150 {

    public static long solve(int num) {

        // init
        Map<Integer, List<Long>> map = new HashMap<>();
        List<Long> sumList = new ArrayList<>();
        sumList.add(0L);
        long t = 0, sum = 0;
        int row = 0, pos = 0;

        // generate sum triangle
        for (long k = 1; k <= num * (num + 1) / 2; k++) {
            t = (615949 * t + 797807) % 1048576;
            sum += t - 524288;
            sumList.add(sum);
            if (++pos > row) {
                map.put(++row, sumList);
                sumList = new ArrayList<>();
                sumList.add(0L);
                sum = 0;
                pos = 0;
            }
        }

        // generate minimal sum
        long minSum = Long.MAX_VALUE;
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= i; j++) {
                sum = 0;
                for (int k = i; k <= num; k++) {
                    sum += map.get(k).get(j + k - i) - map.get(k).get(j - 1);
                    minSum = Math.min(minSum, sum);
                }
            }
        }

        return minSum;
    }
 

    public static void main(String[] args) {
        System.out.println(P150.solve(1000)); 
    }
}
