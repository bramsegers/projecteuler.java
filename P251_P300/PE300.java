package P251_P300;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class PE300 {

    public static void main(String[] args) {
        new PE300().solve(15);
    }

    int SIZE, SIZE2;
    char EMPTY = '.';
    byte[][] COUNT;
    Set<Set<Integer>> CONNECTIONS = new HashSet<>();

    void solve(int s) {

        // init
        SIZE = s;
        SIZE2 = s << 1;
        String f = "";
        for (int i = 0; i < SIZE2 * SIZE2; i++) {
            f += EMPTY;
        }
        f = addPosition(f, SIZE, SIZE, 1);
        System.out.println("#Proteins: " + (1 << SIZE));

        // get connections
        setFoldings(f, SIZE, SIZE, 1, false);
        COUNT = new byte[CONNECTIONS.size()][1 << SIZE];
        System.out.println("#Unique connections: " + CONNECTIONS.size());

        // count connections
        System.out.println("Counting connections per protein...");
        int cc = 0;
        for (Set<Integer> conn : CONNECTIONS) {
            for (int c : conn) {
                int b1 = c & ((1 << 8) - 1);
                int b2 = c >> 8;
                count(cc, 0, b1, b2, 0);
            }
            cc++;
        }

        // collect max result        
        System.out.println("Collecting max result...");
        int sum = 0;
        for (int n = 0; n < (1 << SIZE); n++) {
            int max = 0;
            for (int c = 0; c < CONNECTIONS.size(); c++) {
                max = Math.max(max, COUNT[c][n]);
            }
            sum += max;
        }
        System.out.println("Sum: " + sum);
        System.out.println("Avg: " + (double) sum / (1 << SIZE));
    }

    void count(int conn, int n, int b1, int b2, int pos) {
        if (pos == SIZE) {
            COUNT[conn][n]++;
        } else {
            count(conn, (n << 1) + 1, b1, b2, pos + 1);
            if (pos != b1 && pos != b2) {
                count(conn, n << 1, b1, b2, pos + 1);
            }
        }
    }

    void setFoldings(String s, int i, int j, int done, boolean tr) {
        if (s == null) {
            return;
        }
        if (done == SIZE) {
            setConnections(s);
            return;
        }
        int[] di = {i, i + 1, i, i - 1};
        int[] dj = {j + 1, j, j - 1, j};
        for (int d = 0; d < (done == 1 ? 1 : (!tr ? 2 : 4)); d++) {
            String t = addPosition(s, di[d], dj[d], done + 1);
            setFoldings(t, di[d], dj[d], done + 1, d == 1 ? true : tr);
        }
    }

    String addPosition(String s, int i, int j, int done) {
        if (i < 0 || j < 0 || i >= SIZE2 || j >= SIZE2) {
            return null;
        }
        int index = j * SIZE2 + i;
        if (s.charAt(index) != EMPTY) {
            return null;
        }
        return s.substring(0, index) + (char) ('A' + done - 1) + s.substring(index + 1);
    }

    void setConnections(String s) {
        Set<Integer> conn = new TreeSet<>();
        for (int n = 0; n < SIZE2 * SIZE2; n++) {
            char ch = s.charAt(n);
            int i = n % SIZE2;
            int j = n / SIZE2;
            if (ch != EMPTY) {
                char right = (i < SIZE2 - 1) ? s.charAt(i + 1 + (SIZE2 * j)) : EMPTY;
                char lower = (j < SIZE2 - 1) ? s.charAt(i + SIZE2 * (j + 1)) : EMPTY;
                for (char neighb : new char[]{right, lower}) {
                    if (neighb != EMPTY) {
                        int c = ch < neighb
                                ? ((ch - 'A') << 8) + (neighb - 'A')
                                : ((neighb - 'A') << 8) + (ch - 'A');
                        conn.add(c);
                    }
                }
            }
        }
        CONNECTIONS.add(conn);
    }

}
