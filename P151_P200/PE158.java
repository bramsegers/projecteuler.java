package P151_P200;

public class PE158 {

    /*  
     brute force:         
     P(1)=0         
     P(2)=325        => 26choose2=325    = P(2)*1   =>   1=2^2-2-1
     P(3)=10400      => 26choose3=2600   = P(3)*4   =>   4=2^3-3-1
     P(4)=164450     => 26choose4=14950  = P(4)*11  =>  11=2^4-4-1
     P(5)=1710280    => 26choose5=65780  = P(5)*26  =>  26=2^5-5-1
     P(6)=13123110   => 26choose6=230230 = P(6)*57  =>  57=2^6-6-1
     P(7)=78936000   => 26choose7=657800 = P(7)*120 => 120=2^7-7-1
    
     => P(n)=(26 choose n)*(2^n-n-1)     
     */
    public static void main(String[] args) {
        //new PE158().bruteForce(5);
        new PE158().solve(26);
    }

    void solve(int limit) {
        long start = System.currentTimeMillis();
        long maxCount = 0, maxN = 0;
        for (int n = 2; n <= limit; n++) {
            long count = nChooseK(limit, n) * ((1L << n) - n - 1);
            System.out.format("P(%d)=%d%n", n, count);
            if (count > maxCount) {
                maxCount = count;
                maxN = n;
            }
        }
        long end = System.currentTimeMillis();
        System.out.format("Max found at P(%d)=%d%nElapsed:%dms%n", maxN, maxCount, end - start);
    }

    long nChooseK(int n, int k) {
        if (k < n - k) {
            k = n - k;
        }
        long rv = 1;
        for (int i = k + 1; i <= n; i++) {
            rv *= i;
        }
        for (int i = 2; i <= n - k; i++) {
            rv /= i;
        }
        return rv;
    }

    void bruteForce(int limit) {
        String abc = "abcdefghijklmnopqrstuvwxyz";
        for (int n = 2; n <= limit; n++) {
            long count = bruteForce("", abc, 0, n);
            System.out.println(count);
        }
    }

    long bruteForce(String done, String abc, int asc, int todo) {
        if (asc > 1 || (asc != 1 && todo == 0)) {
            return 0;
        } else if (todo == 0) {
            return 1;
        } else {
            long rv = 0;
            for (char ch : abc.toCharArray()) {
                rv += bruteForce(
                        done + ch,
                        abc.replace("" + ch, ""),
                        asc + (done.length() > 0 && ch > done.charAt(done.length() - 1) ? 1 : 0),
                        todo - 1
                );
            }
            return rv;
        }
    }

}
