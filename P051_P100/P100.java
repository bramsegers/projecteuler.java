package P051_P100;

/*
 * If a box contains twenty-one coloured discs, composed of fifteen blue discs 
 * and six red discs, and two discs were taken at random, it can be seen that 
 * the probability of taking two blue discs, P(BB) = (15/21)x(14/20) = 1/2.
 * 
 * The next such arrangement, for which there is exactly 50% chance of taking 
 * two blue discs at random, is a box containing 85 blue discs and 35 red discs.
 * 
 * By finding the first arrangement to contain over 10^12 discs in total, 
 * determine the number of blue discs that the box would contain.
 */
public class P100 {

    /*
     * n     total = t(n)	  blue	   t(n)/t(n-1)	  2*SQRT(2)+3
     * --------------------------------------------------------------
     * 0                4	     3	
     * 1               21	    15	   5,25	          5,828427125
     * 2              120	    85	   5,714285714	  5,828427125
     * 3              697	   493	   5,808333333	  5,828427125
     * 4             4060	  2871	   5,824964132	  5,828427125
     * 5            23661	 16731	   5,827832512	  5,828427125
     * 6           137904	 97513	   5,828325092	  5,828427125
     * 7           803761	568345	   5,828409618	  5,828427125
     *
     * Info: http://www.wolframalpha.com/input/?i=x%28x-1%29%3D2y%28y-1%29    
     */
    public static long solve(long tMax) {
        int i = 2;
        while (b(++i) < tMax) {
            System.out.format("(t,b)=(%d,%d)%n", t(i), b(i));
        }
        return b(--i);
    }

    private static long t(int n) {
        double sq2 = Math.sqrt(2);
        double d = -Math.pow(3 - 2 * sq2, n);
        d -= sq2 * Math.pow(3 - 2 * sq2, n);
        d -= Math.pow(3 + 2 * sq2, n);
        d += sq2 * Math.pow(3 + 2 * sq2, n);
        return Math.round((d + 2) / 4);
    }

    private static long b(int n) {
        double sq2 = Math.sqrt(2);
        double d = 2 * Math.pow(3 - 2 * sq2, n);
        d += sq2 * Math.pow(3 - 2 * sq2, n);
        d += 2 * Math.pow(3 + 2 * sq2, n);
        d += -sq2 * Math.pow(3 + 2 * sq2, n);
        return Math.round((d + 4) / 8);
    }

    public static void main(String[] args) {
        System.out.println(P100.solve(1000000000000L));
    }
}
