package P001_P050;

/*
 2520 is the smallest number that can be divided by each of the numbers 
 from 1 to 10 without any remainder. What is the smallest positive number 
 that is evenly divisible by all of the numbers from 1 to 20?
 */
public class P005 {

    public static long solve(int nMax) {
        int[] fact = new int[nMax + 1];
        for (int i = 2; i <= nMax; i++) {
            int n = i;
            int p = 2;
            while (n > 1) {
                int f = 0;
                while (n % p == 0) {
                    n /= p;
                    f++;
                }
                fact[p] = Math.max(fact[p], f);
                p++;
            }
        }
        long prod = 1;
        for (int i = 2; i <= nMax; i++) {
            for (int j = 0; j < fact[i]; j++) {
                prod *= i;
            }
        }
        return prod;
    }

    public static void main(String[] args) {
        System.out.println(P005.solve(20));
    }
}
