package P201_P250;

public class PE225 {

    public static void main(String[] args) {
        new PE225().solve(124);
    }

    void solve(int nMax) {
        long count = 0, d = 1;
        while (count < nMax) {
            if (!isDivisor(d)) {
                System.out.println(++count + " " + d);
            }
            d += 2;
        }
    }

    boolean isDivisor(long d) {
        long f1 = 1, f2 = 1, f3 = 1;
        boolean more = true;
        while (more) {
            long f = (f1 + f2 + f3) % d;
            f1 = f2;
            f2 = f3;
            f3 = f;
            more = !(f1 == 1 && f2 == 1 && f3 == 1);
            if (f % d == 0) {
                return true;
            }
        }
        return false;
    }

}
