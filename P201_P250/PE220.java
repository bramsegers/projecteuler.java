package P201_P250;

public class PE220 {

    /*
     * http://en.wikipedia.org/wiki/Dragon_curve
     * Method for determining the direction of the nth turn in 
     * the turn sequence of a Heighway dragon iteration:
     * - Express n in the form k*2^m where k is an odd number
     * - The direction of the nth turn is determined by d =  k mod 4 
     * - If k mod 4 = 1 then the nth turn is R
     * - If k mod 4 = 3 then the nth turn is L
     */
    public static void main(String[] args) {
        new PE220().solve(1000000000000L);// (139776,963904) -> BUILD SUCCESSFUL (total time: 6 minutes 9 seconds) 
    }

    void solve(long step) {

        // determine increase or reverse
        int e = 0;
        boolean rev = true;
        while ((1L << ++e) < step) {
        }
        if ((1L << e) - step > step - (1L << (e - 1))) {
            rev = false;
            e--;
        }

        // init 
        long x = (((e & 3) == 1) ? 1 << ((e - 1) >> 1)
                : ((e & 3) > 1) ? 1 << ((e >> 1)) : 0)
                * ((((e >> 2) & 1) == 1) ? -1 : 1);
        long y = (((e & 3) < 2) ? 1 << (2 * (e >> 2))
                : ((e & 3) == 3) ? 1 << (2 * (e >> 2) + 1) : 0)
                * (((((e + 2) >> 2) & 1) == 1) ? -1 : 1);
        int dir = rev ? 3 : 2;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        long start = (1L << e) + (rev ? -1 : 1);

        // step
        for (long n = start; rev ? n >= step : n <= step; n += rev ? -1 : 1) {
            x += dx[dir];
            y += dy[dir];
            long i = n;
            while ((i & 1) == 0) {
                i >>= 1;
            }
            dir += (i & 3) + (rev ? 2 : 0);
            dir &= 3;
        }
        System.out.format("P(%d)=(%d,%d)%n", step, x, y);

    }

}
