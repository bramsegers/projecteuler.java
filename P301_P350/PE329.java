package P301_P350;

import java.util.ArrayList;
import java.util.List;
import util.Util;

public class PE329 {

    public static void main(String[] args) {
        new PE329().solve(500, "PPPPNNPPPNPPNPN");
    }

    int range;
    int length;
    long P, Q;
    boolean[] croakPrime;

    void solve(int rnge, String croak) {

        // init
        range = rnge;
        Util.initPrimes(range);
        length = croak.length();
        croakPrime = new boolean[length];
        Q = range * (long) Math.pow(6, length);
        for (int i = 0; i < length; i++) {
            croakPrime[i] = croak.charAt(i) == 'P';
        }

        // get paths and their probabilities
        for (int i = 1; i <= range; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            paths(i, range, list);
        }

        // get result
        long gcd = Util.gcd(P, Q);
        System.out.println(P / gcd + "/" + Q / gcd);
    }

    void paths(int i, int p, List<Integer> path) {
        if (path.size() == length) {
            addP(p, path);
        } else {
            List<Integer> list2 = new ArrayList<>(path);
            if (i == 1) {
                list2.add(2);
                paths(2, p, list2);
            } else if (i == range) {
                list2.add(range - 1);
                paths(range - 1, p, list2);
            } else {
                list2.add(i - 1);
                paths(i - 1, p * 2, list2);
                List<Integer> list3 = new ArrayList<>(path);
                list3.add(i + 1);
                paths(i + 1, p * 2, list3);
            }
        }
    }

    void addP(long q, List<Integer> list) {
        long p = 1;
        for (int i = 0; i < length; i++) {
            if (Util.isPrime[list.get(i)] == croakPrime[i]) {
                p *= 2;
            }
            q *= 3;
        }
        P += (Q / q) * p;
    }

}
