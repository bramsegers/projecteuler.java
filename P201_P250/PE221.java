package P201_P250;

import java.math.BigInteger;
import java.util.TreeSet;

public class PE221 {

    /*
     Info: https://oeis.org/A147811    
     The numbers are of the form p(p+d)(p+(p^2+1)/d), where d runs over 
     divisors of p^2+1 and p runs over all positive integers
     */
    public static void main(String[] args) {
        new PE221().solve(150000, 80000);
    }

    void solve(int n, int range) {
        TreeSet<BigInteger> set = new TreeSet<>();
        long p = 0;
        while (p < range) {
            p++;
            long P = (p * p + 1);
            for (long d = 1; d <= p; d++) {
                if (P % d == 0) {
                    //long a = p * (p + d) * (p + P / d);
                    //System.out.format("(p,d,a)=(%d,%d,%d)%n", p, d, a);  
                    set.add(
                            new BigInteger(String.valueOf(p * (p + d)))
                            .multiply(new BigInteger(String.valueOf(p + P / d)))
                    );
                    if (set.size() > n) {
                        set.pollLast();
                    }
                }
            }
        }
        System.out.println(set.last());
    }

}

