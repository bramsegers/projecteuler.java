package P151_P200;

public class PE197 {

    public static void main(String[] args) {
        new PE197().solve(1000000000000L);
    }

    void solve(long nMax) {        
        long start = System.currentTimeMillis();        
        double f = -1, f1 = 0, f2 = 0;
        long n;
        boolean repeat = false;
        for (n = 0; n < nMax + 2 && !repeat; n++) {
            System.out.println("u(" + n + ")=" + f);
            repeat = f == f2;
            f2 = f1;
            f1 = f;
            f = (int) Math.pow(2, 30.403243784D - (f * f)) / 1000000000D;
        }        
        long end = System.currentTimeMillis();        
        System.out.format("u(%d)+u(%d)%s=%.9f%nElapsed:%dms%n",
                nMax,
                nMax + 1,
                repeat ? String.format("=u(%d)+u(%d)", n - 2, n - 1) : "",
                f1 + f2,
                end - start);
    }

}
