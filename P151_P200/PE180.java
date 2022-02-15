package P151_P200;

import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;
import util.Frac;
import util.Util;

public class PE180 {

    /*
     Met algebra vinden we:
     f1 n x y z = x^(n+1) + y^(n+1) - z^(n+1)
     f2 n x y z = (x*y + y*z + z*x) * ( x^(n-1) + y^(n-1) - z^(n-1) )
     f3 n x y z = x*y*z*( x^(n-2) + y^(n-2) - z^(n-2) )
     f n x y z = f1 n x y z + f2 n x y z - f3 n x y z
     f n x y z = (x+y+z) * (x^n+y^n-z^n)      
     Dankzij Fermat weten we: n = [-2, -1, 1, 2]
     Dan geldt: 
     f(-2) z = xy/sqrt(x^2 + y^2)
     f(-1) z = xy/(x+y)
     f(1)  z = x+y
     f(2)  z = sqrt(x^2 + y^2)
     */
    public static void main(String[] args) {
        new PE180().solve(35);
    }

    int order;
    long u, v;

    void solve(int k) {
        // init
        order = k;
        Set<Frac> fractions = new TreeSet<>();
        Set<Frac> solutions = new TreeSet<>();
        for (int a = 1; a <= order; a++) {
            for (int b = a + 1; b <= order; b++) {
                fractions.add(new Frac(a, b));
            }
        }
        // check solutions
        for (Frac x : fractions) {
            for (Frac y : fractions) {
                for (Frac z : solveZ(x, y)) {
                    solutions.add(x.add(y).add(z));
                }
            }
        }
        // get result
        sumWithoutOverflow(solutions);
        System.out.println("Solutions: " + solutions);
        System.out.println("Solutions found: " + solutions.size());
        System.out.println("Sum u/v = " + u + "/" + v);
        System.out.println("Sum u+v = " + (u + v));
    }

    Set<Frac> solveZ(Frac x, Frac y) {
        Set<Frac> set = new TreeSet<>();
        Frac z1 = x.add(y);                 // n = 1
        if (isValid(z1)) {
            set.add(z1);
        }
        Frac z2 = x.mul(y).div(x.add(y));  // n = -1
        if (isValid(z2)) {
            set.add(z2);
        }
        Frac f = x.mul(x).add(y.mul(y));
        long p = (long) Math.sqrt(f.P);
        long q = (long) Math.sqrt(f.Q);
        if (p * p == f.P && q * q == f.Q) {
            Frac z3 = new Frac(p, q);       // n = 2;
            if (isValid(z3)) {
                set.add(z3);
            }
            Frac z4 = x.mul(y).div(z3);    // n = -2 
            if (isValid(z4)) {
                set.add(z4);
            }
        }
        return set;
    }

    boolean isValid(Frac f) {
        return f.P < f.Q && f.Q <= order;
    }

    void sumWithoutOverflow(Set<Frac> set) {
        BigInteger P = BigInteger.ZERO;
        BigInteger Q = BigInteger.ONE;
        for (int q = 2; q <= order; q++) {
            Q = Q.multiply(BigInteger.valueOf(q));
        }
        for (Frac f : set) {
            BigInteger p = BigInteger.valueOf(f.P);
            BigInteger q = BigInteger.valueOf(f.Q);
            BigInteger m = Q.divide(q);
            P = P.add(p.multiply(m));
        }
        BigInteger gcd = Util.gcd(P, Q);
        u = P.divide(gcd).longValue();
        v = Q.divide(gcd).longValue();
    }

}
