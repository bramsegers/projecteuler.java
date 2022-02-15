package P551_P600;

public class PE587 {

    public static void main(String[] args) {
        new PE587().solve(0.001);
    }

    void solve(double less) {
        boolean more = true;
        int n = 0;
        while (more) {
            n++;
            double x = (n * n - n + Math.sqrt(2 * n)) / (n * n + 1);
            double y = n * x - n + 1;
            double a1 = (1 - x) * y;
            double a2 = (1 - x) * (1 - y) / 2;
            double a3 = intg(1) - intg(x);
            double a = (a1 + a2 - a3) / (1 - Math.PI / 4);
            System.out.println(n + " " + a);
            more = (a >= less);
        }
    }

    double intg(double x) {
        return (Math.sqrt(1D - x * x) * x + Math.asin(x)) / 2;
    }

}
