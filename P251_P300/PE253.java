package P251_P300;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

public class PE253 {

    public static void main(String[] args) {
        new PE253().simulate(40, 6);
    }

    // for N=40 and 6 digit accuracy, simulation gets stable 
    // after ~4e9 simulations (M=11.492847), taking ~200 minutes
    void simulate(int N, int D) {

        BitSet bs = new BitSet();
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            nums.add(i);
        }
        
        long reps = 0;
        long tmax = 0;
        String out = "(reps,avg)=(%d,%." + D + "f)%n";

        while (true) {

            bs.clear();            
            Collections.shuffle(nums);

            int max = 0;
            for (int i = 0; i < N; i++) {
                bs.set(nums.get(i));
                max = Math.max(max, pieces(bs));
            }
            tmax += max;
            
            if (++reps % 1000000 == 0) {
                System.out.format(out, reps, 1D * tmax / reps); 
            }

        }

    }

    int pieces(BitSet bs) {
        int sum = 0, i = 0;
        while (true) {
            i = bs.nextSetBit(i);
            if (i < 0) {
                return sum;
            }
            i = bs.nextClearBit(i);
            sum++;
        }
    }

}
