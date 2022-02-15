package P451_P500;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PE480 {

    public static void main(String[] args) {
        new PE480().solve();
    }

    String word = "thereisasyetinsufficientdataforameaningfulanswer";
    List<Integer> numchars;
    Map<List<Integer>, Integer> divs = new HashMap<>();
    Map<Integer, Map<List<Integer>, Long>> Q = new HashMap<>();
    int N = 15;
    long[] fac = new long[N + 1];

    void solve() {
        for (int i = 0; i <= N; i++) {
            fac[i] = i < 2 ? 1 : i * fac[i - 1];
        }

        long L = P("legionary")//242132052536675919
                + P("calorimeters")//41242803282213119
                - P("annihilate")//23138326896450711
                + P("orchestrated")//329830042298774430
                - P("fluttering");//139042949535915543

        System.out.println(W(L));
    }

    long P(String s) {
        System.out.format("P(%s)=", s);
        Map<Character, Integer> map = new HashMap<>();
        for (char c : word.toCharArray()) {
            Integer i = map.get(c);
            map.put(c, (i == null) ? 1 : i + 1);
        }
        long sum = s.length();
        int n = N;
        for (char sc : s.toCharArray()) {
            for (char c = 'a'; c < sc; c++) {
                for (int i = 1; map.containsKey(c) && i <= n; i++) {
                    long count = count(c, i, map);
                    sum += count;
                }
            }
            Integer q = map.get(sc);
            if (q == 1) {
                map.remove(sc);
            } else {
                map.put(sc, q - 1);
            }
            n--;
        }
        System.out.println(sum);
        return sum;
    }

    String W(long L) {
        System.out.format("W(%d)=", L);
        return "\n" + W(L, N, word, "");
    }

    String W(long L, int n, String w, String pre) {
        Map<Character, Integer> map = new TreeMap<>();
        for (char c : w.toCharArray()) {
            Integer i = map.get(c);
            map.put(c, (i == null) ? 1 : i + 1);
        }
        long tsum = 0, last = 0;
        for (char c : map.keySet()) {
            long sum = 0;
            for (int i = 1; i <= n; i++) {
                long count = count(c, i, map);
                sum += count;
            }
            tsum += sum;
            if (tsum > L) {
                System.out.print(c);
                return (L - last == 1)
                        ? pre + c
                        : W(L - last - 1, n - 1, w.replaceFirst("" + c, ""), pre + c);
            }
            last = tsum;
        }
        return null;
    }

    long count(char c, int i, Map<Character, Integer> map) {
        if (i == 1) {
            return 1;
        }
        Map<Character, Integer> map2 = new HashMap<>(map);
        Integer n = map2.get(c);
        if (n == 1) {
            map2.remove(c);
        } else {
            map2.put(c, n - 1);
        }

        numchars = new ArrayList<>(map2.values());
        Collections.sort(numchars);
        Map<List<Integer>, Long> m = Q.get(i);
        if (m == null) {
            m = new HashMap<>();
            Q.put(i, m);
        }
        if (m.get(numchars) != null) {
            return m.get(numchars);
        }

        long rv = 0;
        int elems = numchars.stream().reduce(0, Integer::sum);
        getDivisions(0, i - 1, elems, new ArrayList<>());
        for (List<Integer> d : divs.keySet()) {
            long t = fac[i - 1];
            for (int q : d) {
                t /= fac[q];
            }
            rv += t * divs.get(d);
        }
        divs.clear();
        m.put(numchars, rv);
        return rv;
    }

    void getDivisions(int index, int todo, int maxtodo, List<Integer> list) {
        if (todo == 0) {
            Collections.sort(list);
            Integer n = divs.get(list);
            divs.put(list, (n == null) ? 1 : n + 1);
        } else if (todo <= maxtodo && todo > 0 && index < numchars.size()) {
            for (int i = 0; i <= numchars.get(index) && i <= todo; i++) {
                List<Integer> list2 = new ArrayList<>(list);
                if (i > 1) {
                    list2.add(i);
                }
                getDivisions(index + 1, todo - i, maxtodo - numchars.get(index), list2);
            }
        }
    }
}
