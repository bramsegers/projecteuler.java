package P001_P050;

/*
 Triangle, pentagonal, and hexagonal numbers are generated 
 by the following formulae:
 Triangle       T(n)=n(n+1)/2       1, 3,  6, 10, 15, ...
 Pentagonal     P(n)=n(3n-1)/2      1, 5, 12, 22, 35, ...
 Hexagonal      H(n)=n(2n-1)        1, 6, 15, 28, 45, ...
 It can be verified that T(285) = P(165) = H(143) = 40755.

 Find the next triangle number that is also pentagonal and hexagonal.
 */
public class P045 {

    static long nTri, nPen, nHex;

    public static long solve(int triPrev) {
        long triFound = 0;
        long i = 0;
        while (triFound <= triPrev) {
            i++;
            long n = 2 * i * i - i;
            if (isTriangle(n) && isPentagonal(n) && isHexagonal(n)) {
                System.out.println("T(" + nTri + ") = P(" + nPen + ") = H(" + nHex + ") = " + n);
                triFound = n;
            }
        }
        return triFound;
    }

    public static boolean isTriangle(long i) {
        long D = 8 * i + 1;
        long sqrtD = (long) Math.sqrt(D);
        nTri = (-1 + sqrtD) / 2;
        return nTri * (nTri + 1) / 2 == i;
    }

    public static boolean isPentagonal(long i) {
        long D = 24 * i + 1;
        long sqrtD = (long) Math.sqrt(D);
        nPen = (1 + sqrtD) / 6;
        return nPen * (3 * nPen - 1) / 2 == i;
    }

    public static boolean isHexagonal(long i) {
        long D = 8 * i + 1;
        long sqrtD = (long) Math.sqrt(D);
        nHex = (1 + sqrtD) / 4;
        return nHex * (2 * nHex - 1) == i;
    }

    public static void main(String[] args) {
        System.out.println(P045.solve(40755));
    }
}
