package P201_P250;
/*
 * Wolfram:     4^x-2^x = a  =>  x = (log(1+√(1+4a))-log(2))/log(2) 
 * test():      2^x ∈ N => x = 2,6,12,20,30,42,56,72,90,110,132,156,182,210,...
 * oeis.org:    x is a pronic number
 * 
 * Info: http://www.wolframalpha.com/input/?i=4%5Ex-2%5Ex%3D7
 * Info: http://oeis.org/A002378
 */

public class PE207 {

    public static void main(String[] args) {
        //new PE207().test();
        new PE207().solve(1, 12345);
    }

    void solve(long num, long denom) {
        long k = 0, step = 2;
        long part = 0, perfPart = 0, nextPerf = 2;
        int t = 1;
        while (denom * perfPart >= num * part) {
            k += step;
            step += 2;
            part++;
            if (k == nextPerf) {
                perfPart++;
                t++;
                nextPerf = (1L << (2 * t)) - (1L << t);
            }
        }
        System.out.println(k + " " + perfPart + "/" + part);
    }

    void test() {
        for (int n = 1; n < 300; n++) {
            double log2 = Math.log(2);
            double x = (Math.log(1 + Math.sqrt(4 * n + 1)) - log2) / log2;
            double powX = Math.pow(2, x);
            if (isInteger(powX)) {
                System.out.println(n);
            }
        }
    }

    boolean isInteger(double d) {
        double i = Math.rint(d);
        double abs = Math.abs(d - i);
        double range = 0.00000000000001D;
        return abs <= range;
    }

}
