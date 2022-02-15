package P101_P150;

/*
 * A number consisting entirely of ones is called a repunit. We shall define 
 * R(k) to be a repunit of length k; for example, R(6) = 111111.
 * 
 * Given that n is a positive integer and GCD(n, 10) = 1, it can be shown that 
 * there always exists a value, k, for which R(k) is divisible by n, and let 
 * A(n) be the least such value of k. For example, A(7) = 6 and A(41) = 5.
 * 
 * The least value of n for which A(n) first exceeds ten is 17.
 * Find the least value of n for which A(n) first exceeds one-million.
 */
public class P129 {

    public static void main(String[] args) {
        System.out.println(P129.solve(1000000));
    }

    public static int solve(int limit) {
        int mod = limit + limit % 2 - 1;
        int count = 0;
        while (count <= limit) {
            if ((mod = mod + 2) % 5 != 0) {
                int n = 1;
                count = 1;
                while (n != 0) {
                    count++;
                    n = (n * 10 + 1) % mod;
                }
                System.out.println(mod + "," + count);
            }
        }
        return mod;
    }
}
