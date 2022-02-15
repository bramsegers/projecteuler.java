package P351_P400;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PE393 {

    //https://oeis.org/A216678
    //112398351350823112 (total time: 12 minutes 42 seconds)
    public static void main(String[] args) {
        new PE393().solve(8);
    }

    int len;
    long[][] mem;
    Map<Long, Integer> index = new HashMap<>();
    Map<Integer, List<Integer>> blocks = new HashMap<>();

    void solve(int m) {
        len = m;
        mem = new long[len * len][1600000];
        long c = solve(0, 0);
        System.out.println(c);
    }

    long solve(int p, long blox) {
        if (index.get(blox) == null) {
            index.put(blox, index.size());
        }
        int i = index.get(blox);
        if (mem[p][i] == 0) {
            for (int b : getBlocks(p)) {
                if (validLeft(b, blox & 15, p) && validUp(b, blox >> (4 * (len - 1)), p)) {
                    long blox2 = ((blox << 4) + b) & ((1L << (4 * len)) - 1);
                    mem[p][i] += (p + 1 == len * len) ? 1 : solve(p + 1, blox2);
                }
            }
        }
        return mem[p][i];
    }

    boolean validLeft(int b, long left, int p) {
        if (p % len == 0) {
            return true;
        }
        boolean inv = (left & 3) == 2 && b >> 2 != 0;
        inv |= (left & 3) != 2 && b >> 2 == 0;
        inv |= left >> 2 == 2 && (b & 3) != 0;
        inv |= left >> 2 != 2 && (b & 3) == 0;
        return !inv;
    }

    boolean validUp(int b, long up, int p) {
        if (p / len == 0) {
            return true;
        }
        boolean inv = (up & 3) == 3 && b >> 2 != 1;
        inv |= (up & 3) != 3 && b >> 2 == 1;
        inv |= up >> 2 == 3 && (b & 3) != 1;
        inv |= up >> 2 != 3 && (b & 3) == 1;
        
        return !inv;
    }

    List<Integer> getBlocks(int p) {
        if (blocks.get(p) == null) {
            int px = p % len;
            int py = p / len;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    boolean skip = (i == j);
                    skip |= (px == 0 && (i == 0 || j == 0));
                    skip |= (py == 0 && (i == 1 || j == 1));
                    skip |= (px == len - 1 && (i == 2 || j == 2));
                    skip |= (py == len - 1 && (i == 3 || j == 3));
                    if (!skip) {
                        list.add(4 * i + j);
                    }
                }
            }
            blocks.put(p, list);
        }
        return blocks.get(p);
    }

}
