package P151_P200;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class PE169 {

    /*
     * test() => http://oeis.org/A114214 
     *
     *        => x is even: f(x/2) + f((x-2)/2)
     *           x is odd:  f((x-1)/2)
     */
    public static void main(String[] args) {
        new PE169().solve("10000000000000000000000000");
        new PE169().solve2("10000000000000000000000000");
    }

    void solve(String s) {
        BigInteger bi = new BigInteger(s);
        map.put(BigInteger.ZERO, 1L);
        map.put(BigInteger.ONE, 1L);
        System.out.println(count(bi));
    }

    Map<BigInteger, Long> map = new HashMap<>();

    long count(BigInteger i) {
        if (map.get(i) == null) {
            if (i.testBit(0)) {
                map.put(i, count(i.subtract(BigInteger.ONE).shiftRight(1)));
            } else {
                map.put(i, count(i.shiftRight(1)) + count(i.shiftRight(1).subtract(BigInteger.ONE)));
            }
        }
        return map.get(i);
    }

    void solve2(String s) {
        BigInteger bi = new BigInteger(s);
        long a = 1;
        long b = 0;
        for (int i = bi.bitLength(); i > 0; i--) {
            if (bi.testBit(i)) {
                b += a;
            } else {
                a += b;
            }
        }
        System.out.println(a + b);
    }

}
