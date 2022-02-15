package P151_P200;

import java.util.ArrayList;

public class PE186 {

    public static void main(String[] args) {
        new PE186().solve(524287);
    }

    long num = 0;
    ArrayList<Integer> lagFib = new ArrayList<>();
    ArrayList<Integer>[] conn = new ArrayList[1000000];

    void solve(int pm) {
        int calls = 0;
        int friends = 0;
        ArrayList<Integer> work;
        while (friends < 990000) {
            int from = lagFib();
            int to = lagFib();
            if (from == to) {
                continue;
            }
            calls++;
            if (conn[from] == null) {
                if (conn[to] == null) {
                    work = new ArrayList<>();
                    work.add(from);
                    work.add(to);
                    conn[from] = conn[to] = work;
                } else {
                    work = conn[from] = conn[to];
                    work.add(from);
                }
            } else {
                if (conn[to] == null) {
                    work = conn[to] = conn[from];
                    work.add(to);
                } else {
                    if (conn[from] == conn[to]) {
                        continue;
                    }
                    merge(to, from);
                }
            }
            friends = conn[pm] == null ? 0 : conn[pm].size();
        }

        System.out.println("Friends = " + friends);
        System.out.println("Calls  = " + calls);
    }

    int lagFib() {
        int lf;
        if (++num < 56) {
            lf = (int) ((100003 - 200003 * num + 300007 * num * num * num) % 1000000);
        } else {
            lf = (lagFib.get(0) + lagFib.get(31)) % 1000000;
            lagFib.remove(0);
        }
        lagFib.add(lf);
        return lf;
    }

    void merge(int to, int from) {
        ArrayList<Integer> sfrom = conn[from];
        ArrayList<Integer> sto = conn[to];
        if (sfrom.size() > sto.size()) {
            sfrom = conn[to];
            sto = conn[from];
        }
        for (int n : sfrom) {
            sto.add(n);
            conn[n] = sto;
        }
    }

}
