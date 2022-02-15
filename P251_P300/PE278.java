package P251_P300;
 
import java.util.List;
import util.Util;

public class PE278 {

    //f(p*q,p*r,q*r) = 2*p*q*r - p*q - q*r - r*p
    public static void main(String[] args) {
        new PE278().solve(5000);
    }

    void solve(int lim) {
        long sum = 0;
        List<Integer> pr = Util.getPrimes(lim);
        for (int i = 0; i < pr.size(); i++) {
            for (int j = i + 1; j < pr.size(); j++) {
                for (int k = j + 1; k < pr.size(); k++) {
                    long p = pr.get(i);
                    long q = pr.get(j);
                    long r = pr.get(k);
                    sum += 2 * p * q * r - p * q - q * r - r * p;
                }
            }
        }
        System.out.println(sum);
    }

}
