package P001_P050;

/*
 The number, 197, is called a circular prime because all rotations 
 of the digits: 197, 971, and 719, are themselves prime.
 There are thirteen such primes below 100: 
 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 How many circular primes are there below one million?
 */
public class P035 {

    public static int solve2(int nMax) {
        boolean[] isComposite = new boolean[nMax];
        for (int n = 2; n * n < nMax; n++) {
            if (!isComposite[n]) {
                for (int j = n; n * j < nMax; j++) {
                    isComposite[n * j] = true;
                }
            }
        }
        int count = 0, r = 0, d = 10;
        for (int n = 2; n < nMax; n++) {
            if (n % d == 0) {
                d *= 10;
                r++;
            }
            if (!isComposite[n] && isCircPrime(n, d / 10, r, isComposite)) {
                count++;
                System.out.println(n);
            }
        }
        return count;
    }

    private static boolean isCircPrime(int p, int d, int r, boolean[] comp) {
        for (int i = 0; i < r; i++) {
            p = (p / 10) + (d * (p % 10));
            if (comp[p]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(P035.solve2(1000000));
    }
}
