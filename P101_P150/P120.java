package P101_P150;

/*
 * Let r be the remainder when (a-1)^n + (a+1)^n is divided by a^2.
 * For example, if a = 7 and n = 3, then r = 42: 6^3 + 8^3 = 728 ≡ 42 mod 49. 
 * And as n varies, so too will r, but for a = 7 it turns out that rMax = 42.
 * 
 * For 3 <= a <= 1000, find Σ rMax.
 */
public class P120 {

    /*
     n=even:
     -> r = 2
     n=odd:  
     -> r = 2an mod a^2 
     -> choose largest n such that 2an < a^2
     -> nmax = (a-1)/2
     */
    public static long solve(int aMin, int aMax) {
        long sum = 0;
        for (int a = aMin; a <= aMax; a++) {
            sum += ((a - 1) / 2) * 2 * a;
            System.out.println(a+" "+((a - 1) / 2) * 2 * a);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P120.solve(3, 1000));
    }
}
