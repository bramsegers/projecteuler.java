package P051_P100;

import java.util.HashMap;
import java.util.Map;

/*
 It turns out that 12 cm is the smallest length of wire that can be bent to 
 form an integer sided right angle triangle in exactly one way, 
 but there are many more examples.
 12 cm: (3,4,5)
 24 cm: (6,8,10)
 30 cm: (5,12,13)
 36 cm: (9,12,15)
 40 cm: (8,15,17)
 48 cm: (12,16,20)

 In contrast, some lengths of wire, like 20 cm, cannot be bent to form an integer 
 sided right angle triangle, and other lengths allow more than one solution 
 to be found; for example, using 120 cm it is possible to form exactly three 
 different integer sided right angle triangles.
 120 cm: (30,40,50), (20,48,52), (24,45,51)

 Given that L is the length of the wire, for how many values of L <= 1500000 
 can exactly one integer sided right angle triangle be formed?
 */
public class P075 {

    public static long solve(long lMax) {
        Map<Long, Long> map = new HashMap<>();
        for (long a = 1; a < lMax / 3; a++) {
            for (long b = a; b < lMax - a - b; b++) {
                long c2 = (a * a) + (b * b);
                long c = (long) Math.sqrt(c2);
                if (c * c == c2 && (a + b + c) <= lMax) {
                    Long len = map.get(a + b + c); 
                    System.out.println("(a,b,c)=(" + a + "," + b + "," + c + ")");
                    map.put((a + b + c), len == null ? 1 : len + 1);
                }
            }
        }
        long sum = 0;
        for (long i : map.keySet()) {
            if (map.get(i) == 1) {
                sum++;
            }
        }
        return sum;
    }

    public static long solve2(int lMax) {
        int[] count = new int[lMax + 1];
        count(lMax, count, 3, 4, 5);
        long sum = 0;
        for (int n = 0; n <= lMax; n++) {
            sum += count[n] == 1 ? 1 : 0;
        }
        return sum;
    }

    private static void count(int lMax, int[] count, int a, int b, int c) {
        int len = a + b + c;
        if (len <= lMax) {
            while (len <= lMax) {
                count[len]++;
                len += a + b + c;
            }
            count(lMax, count, a - b - b + c + c, a + a - b + c + c, a + a - b - b + c + c + c);
            count(lMax, count, a + b + b + c + c, a + a + b + c + c, a + a + b + b + c + c + c);
            count(lMax, count, -a + b + b + c + c, -a - a + b + c + c, -a - a + b + b + c + c + c);
        }
    }

    public static void main(String[] args) {
        /*
         161667
         BUILD SUCCESSFUL (total time: 46 minutes 17 seconds)
         */
        //System.out.println(P075.solve(1500000));
        System.out.println(P075.solve2(1500000));
    }
}
