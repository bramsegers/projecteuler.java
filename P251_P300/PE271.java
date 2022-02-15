package P251_P300;

import java.util.ArrayList;
import java.util.List;
import util.CRT;

public class PE271 {

    /*
     * a%(p1*p2*..*pn)=1 iff a%p1=1 and a%p2=1.. and a%pn=1
     * (a^3)%p=1 => period a in [0,p-1]
     */
    public static void main(String[] args) {
        new PE271().solve(91, 7, 13);
        new PE271().solve(13082761331670030L, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43);
    }

    List<List<Integer>> constraints = new ArrayList<>();
    List<List<Integer>> paths = new ArrayList<>();

    void solve(long n, long... p) {
        long sum = 0;
        for (long q : p) {
            List<Integer> c = new ArrayList<>();
            for (int i = 1; i < q; i++) {
                if ((i * i * i) % q == 1) {
                    c.add(i);
                }
            }
            constraints.add(c);
        }
        searchPath(0, new ArrayList<>());
        for (List<Integer> path : paths) {
            long[] constr = new long[path.size()];
            for (int i = 0; i < path.size(); i++) {
                constr[i] = constraints.get(i).get(path.get(i));
            }
            long s = CRT.solve(constr, p);
            sum += (s > 1) ? s : 0;
        }
        System.out.format("P(%d)=%d%n", n, sum);
    }

    void searchPath(int i, List<Integer> path) {
        if (i == constraints.size()) {
            paths.add(path);
        } else {
            for (int j = 0; j < constraints.get(i).size(); j++) {
                List<Integer> path2 = new ArrayList<>(path);
                path2.add(j);
                searchPath(i + 1, path2);
            }
        }
    }

}
