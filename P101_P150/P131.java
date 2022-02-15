package P101_P150;

import util.Util;

/*
 * There are some prime values p, for which there exists a positive integer n, 
 * such that the expression n^3 + n^2*p is a perfect cube.
 * For example, when p = 19, 8^3 + 8^2*19 = 12^3.
 * 
 * What is perhaps most surprising is that for each prime with this property 
 * the value of n is unique, and there are only four such primes below one-hundred.
 * 
 * How many primes below one million have this remarkable property?
 */
public class P131 {

    public static void main(String[] args) {
        System.out.println("n^3 + n^2*p = k^3");
        System.out.println(P131.solve(1000000));
    }

    public static int solve(int num) {
        int result = 0;
        int i = 0;
        int p;
        while ((p = (++i + 1) * (i + 1) * (i + 1) - (i * i * i)) < num) {
            if (Util.isPrime(p)) {
                result++;
                int n = i * i * i;
                int k = (int) ((double) n * Math.pow(((double) p + (double) n) / (double) n, 1.0 / 3));
                System.out.println("(n,p,k)=(" + n + "," + p + "," + k + ")");
            }
        }
        return result;
    }
}
