package P151_P200;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PE196 {

    /*
     S(5678027)=79697256800321526
     S(7208785)=242605983970758409
     T=322303240771079935
     Elapsed:53478ms
     */
    public static void main(String[] args) {
        //new PE196().solve(10000);
        new PE196().solve(5678027, 7208785);
    }

    void solve(long... ns) {
        long start = System.currentTimeMillis();
        long totSum = 0;
        for (long n : ns) {
            long nEnd = n * (n + 1) / 2;
            long nStart = nEnd - n + 1;
            long sum = 0;
            for (long i = nStart + (nStart % 2 == 0 ? 1 : 0); i <= nEnd; i += 2) {
                if (isPrime(i)) {
                    List<Long> list = neighbours(i, 0);
                    if (list.size() < 2) {
                        List<Long> list2 = new ArrayList<>();
                        for (long el : list) {
                            list2.addAll(neighbours(el, i));
                        }
                        list.addAll(list2);
                    }
                    sum += list.size() > 1 ? i : 0;
                }
            }
            System.out.format("S(%d)=%d%n", n, sum);
            totSum += sum;
        }
        long end = System.currentTimeMillis();
        System.out.format("T=%d%nElapsed:%dms%n", totSum, end - start);
    }

    List<Long> neighbours(long i, long ignore) {
        List<Long> list = new ArrayList<>();
        long nb1, nb2, nb3;
        long n = (long) Math.ceil((-1 + Math.sqrt(8 * i + 1)) / 2);
        long nEnd = n * (n + 1) / 2;
        long nStart = nEnd - n + 1;
        if (n % 2 == 0) {
            nb1 = (i == nStart) ? 0 : i - n;
            nb2 = (i == nEnd) ? 0 : i - n + 2;
            nb3 = i + n;
        } else {
            nb1 = i - n + 1;
            nb2 = (i == nStart) ? 0 : i + n - 1;
            nb3 = i + n + 1;
        }
        if (nb1 > 0 && nb1 != ignore && isPrime(nb1)) {
            list.add(nb1);
        }
        if (nb2 > 0 && nb2 != ignore && isPrime(nb2)) {
            list.add(nb2);
        }
        if (nb3 > 0 && nb3 != ignore && isPrime(nb3)) {
            list.add(nb3);
        }
        return list;
    }

    boolean isPrime(long i) {
        return new BigInteger(String.valueOf(i)).isProbablePrime(4);
    }

}
