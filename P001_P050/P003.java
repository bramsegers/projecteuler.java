package P001_P050;

/*
 The prime factors of 13195 are 5, 7, 13 and 29.
 What is the largest prime factor of the number 600851475143 ?
 */
public class P003 {

    public static void solve(long n) {
        String s = n + "=";
        long rv = 0, p = 2;
        while (n > 1) {
            while (n % p == 0) {
                rv = p;
                s += p + "*";
                n /= p;
            }
            p++;
        }
        System.out.println(s + "\b");
        System.out.println(rv);
    }

    public static void main(String[] args) {
        P003.solve(600851475143L);
    }
}
