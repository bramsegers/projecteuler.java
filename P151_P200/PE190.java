package P151_P200;

public class PE190 {

    /*
     Info: http://www.quora.com/How-can-I-solve-problem-number-190-at-Project-Euler
     */
    public static void main(String[] args) {
        new PE190().solve(15);
    }

    void solve(int nMax) {
        long sum = 0;
        for (int n = 2; n <= nMax; n++) {
            long max = max(n);
            System.out.println(n + " " + max);
            sum += max;
        }
        System.out.println(sum);
    }

    long max(double m) {
        //(Pm)max =((2/(m+1))^(m(m+1)/2))*2^2*3^3*4^4*...m^m
        double rv = Math.pow(2 / (m + 1), (m * (m + 1) / 2));
        for (int n = 2; n <= m; n++) {
            rv *= Math.pow(n, n);
        }
        return (long) rv;
    }

}
