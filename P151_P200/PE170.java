package P151_P200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PE170 {

    public static void main(String[] args) {
        new PE170().solve();
    }

    List<List<Integer>> divs = new ArrayList<>();
    List<Long> pandigits = new ArrayList<>();

    void solve() {

        // generate pandigits
        for (int i = 0; i < 9; i++) {
            List<Integer> todo = new ArrayList<>(Arrays.asList(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
            List<Integer> done = new ArrayList<>();
            done.add(todo.get(i));
            todo.remove(i);
            pandigits(done, todo);
        }

        // generate permutations
        for (int i = 1; i < 10; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            divide(10 - i, i, list);
        }

        // search      
        System.out.println(search());
    }

    long search() {
        for (long t : pandigits) {
            for (List<Integer> div : divs) {
                List<Long> parts = parts(t, div);
                if (parts != null) {
                    for (long d = 2; d <= min; d++) {
                        if (isDivisor(d, parts)) {
                            List<Long> prods = new ArrayList<>();
                            prods.add(d);
                            for (long num : parts) {
                                prods.add(num / d);
                            }
                            if (isPandigit(prods)) {
                                System.out.println(prods + " " + parts);
                                return t;
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    boolean isPandigit(List<Long> list) {
        int[] arr = new int[10];
        for (long n : list) {
            long m = n;
            while (m > 0) {
                int i = (int) m % 10;
                if (++arr[i] > 1) {
                    return false;
                }
                m /= 10;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (arr[i] == 0) {
                return false;
            }
        }
        return true;
    }

    long min;

    List<Long> parts(long t, List<Integer> list) {
        List<Long> parts = new ArrayList<>();
        min = Long.MAX_VALUE;
        for (int i = list.size() - 1; i >= 0; i--) {
            long mod = (long) Math.pow(10, list.get(i));
            long tmod = t % mod;
            parts.add(0, tmod);
            t /= mod;
            min = Math.min(min, tmod);
            if (tmod * 10 / mod == 0) {
                return null;
            }
        }
        return parts;
    }

    void pandigits(List<Integer> done, List<Integer> todo) {
        if (todo.isEmpty()) {
            long pandigit = 0;
            for (int i : done) {
                pandigit *= 10;
                pandigit += i;
            }
            pandigits.add(pandigit);
        } else {
            for (int i = 0; i < todo.size(); i++) {
                List<Integer> done2 = new ArrayList<>(done);
                List<Integer> todo2 = new ArrayList<>(todo);
                done2.add(todo.get(i));
                todo2.remove(i);
                pandigits(done2, todo2);
            }
        }
    }

    boolean isDivisor(long d, List<Long> list) {
        for (long i : list) {
            if (i % d != 0) {
                return false;
            }
        }
        return true;
    }

    void divide(int n, int a, List<Integer> list) {
        if (n == 0) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i : list) {
                Integer f = map.get(i);
                map.put(i, f == null ? 1 : f + 1);
            }
            List<Integer> list2 = new ArrayList<>();
            permutation(list2, map);
        } else {
            for (int i = 1; i <= a; i++) {
                List<Integer> list2 = new ArrayList<>(list);
                list2.add(i);
                if (n - i >= 0 && !(a == 1 && i == 1)) {
                    divide(n - i, i, list2);

                }
            }
        }
    }

    void permutation(List<Integer> list, Map<Integer, Integer> map) {
        if (map.isEmpty()) {
            divs.add(list);
        } else {
            for (int i : map.keySet()) {
                Map<Integer, Integer> map2 = new HashMap<>(map);
                int f = map.get(i);
                if (f == 1) {
                    map2.remove(i);
                } else {
                    map2.put(i, f - 1);
                }
                List<Integer> list2 = new ArrayList<>(list);
                list2.add(i);
                permutation(list2, map2);
            }
        }
    }

}
