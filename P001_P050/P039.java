package P001_P050;

/*
 If p is the perimeter of a right angle triangle with integral 
 length sides, {a,b,c}, there are exactly three solutions for p = 120.
 {20,48,52}, {24,45,51}, {30,40,50}
 
 For which value of p<1000, is the number of solutions maximised?
 */
public class P039 {

    public static String solve(int num) {
        int maxSolutions = 0;
        int maxValue = 0;
        for (int i = 3; i < num; i++) {
            int solutions = 0;
            for (int a = 1; a < i; a++) {
                for (int b = a; b < i - a - b; b++) {
                    int c = i - a - b;
                    if ((a * a) + (b * b) == c * c) {
                        System.out.println(i + ": (a,b,c)=" + a + "," + b + "," + c);
                        solutions++;
                    }
                }
            }
            if (solutions > maxSolutions) {
                maxSolutions = solutions;
                maxValue = i;
            }
        }
        return maxValue + " (" + maxSolutions + ")";
    }

    public static int solve2(int nMax) {
        int[] count = new int[nMax + 1];
        solve2(nMax, count, 3, 4, 5);
        int max = 0, maxLen = 0;
        for (int n = 0; n <= nMax; n++) {
            if (count[n] > max) {
                max = count[n];
                maxLen = n;
            }
        }
        return maxLen;
    }

    private static void solve2(int nMax, int[] count, int a, int b, int c) {
        int len = a + b + c;
        if (len <= nMax) {
            while (len <= nMax) {
                count[len]++;
                len += a + b + c;
            }
            solve2(nMax, count, a - b - b + c + c, a + a - b + c + c, a + a - b - b + c + c + c);
            solve2(nMax, count, a + b + b + c + c, a + a + b + c + c, a + a + b + b + c + c + c);
            solve2(nMax, count, -a + b + b + c + c, -a - a + b + c + c, -a - a + b + b + c + c + c);
        }
    }

    public static void main(String[] args) {
        //System.out.println(P039.solve(1000));
        System.out.println(P039.solve2(1000));
    }
}
