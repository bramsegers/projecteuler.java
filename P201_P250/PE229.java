package P201_P250;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class PE229 {

    public static void main(String[] args) {
        //new Test_PE229().solve(10000000);//Found:75373 Elapsed:530ms
        new PE229().solve(2000000000);//Found:11325263 Elapsed:221345ms 
    }

    void solve(int nMax) {
        long start = System.currentTimeMillis();
        List<Integer> squares = new ArrayList<>();
        BitSet squares1 = new BitSet(nMax + 1);
        BitSet squares2 = new BitSet(nMax + 1);
        BitSet squares3 = new BitSet(nMax + 1);
        BitSet squares7 = new BitSet(nMax + 1);
        int s = 1, n = 1;
        while (s <= nMax) {
            squares.add(s);
            s = ++n * n;
        }
        long sq, sqA;
        for (int i = 0; i < squares.size(); i++) {
            sqA = squares.get(i);
            for (int j = 0; j < squares.size() && (sq = sqA + squares.get(j)) <= nMax; j++) {
                squares1.set((int) sq);
                if ((sq += sqA) <= nMax) {
                    squares2.set((int) sq);
                    if ((sq += sqA) <= nMax) {
                        squares3.set((int) sq);
                        if ((sq += sqA << 2) <= nMax) {
                            squares7.set((int) sq);
                        }
                    }
                }
            }
        }
        squares1.and(squares2);
        squares1.and(squares3);
        squares1.and(squares7);
        long end = System.currentTimeMillis();
        System.out.format("Found:%d%nElapsed:%dms%n", squares1.cardinality(), end - start);
    }

}
