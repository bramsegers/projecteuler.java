// 1. Sort points in lexicographical order 
// 2. Find the longest non-decreasing subsequence in the sequence of y-values
//
// Longest increasing subsequence in O(N*logN)
// https://sites.google.com/site/indy256/algo/lis_nlogn
//
// sum = 9936352
// BUILD SUCCESSFUL (total time: 3 minutes 53 seconds)
package P401_P450;

import java.util.TreeSet;

public class PE411 {

    public static void main(String[] args) {
        new PE411().solve();
    }

    void solve() {
        long sum = 0;
        for (int k = 1; k <= 30; k++) {
            int n = (int) Math.pow(k, 5);
            TreeSet<Point> set = new TreeSet<>();
            int x = 1, y = 1;
            for (int i = 0; i <= 2 * n; i++) {
                set.add(new Point(x, y));
                x = (x * 2) % n;
                y = (y * 3) % n;
            }
            int[] a = new int[set.size()];
            int i = 0;
            for (Point p : set) {
                a[i++] = p.y;
            }
            int lis = (n == 1) ? 1 : LNDS(a);
            sum += lis;
            System.out.println(a.length);
            System.out.format("S(%d^5)=%d%n", k, lis);
        }
        System.out.println("sum = " + sum);
    }

    int LNDS(int[] a) {
        int len = 0;
        int[] tail = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            int pos = lower(a, tail, len, a[i]);
            len += (pos == len) ? 1 : 0;
            tail[pos] = i;
        }
        return len;
    }

    int lower(int[] a, int[] tail, int len, int key) {
        int lo = -1;
        int hi = len;
        while (hi - lo > 1) {
            int mid = (lo + hi) >>> 1;
            if (a[tail[mid]] <= key) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    class Point implements Comparable<Point> {

        int x, y;

        Point(int i, int j) {
            x = i;
            y = j;
        }

        @Override
        public int compareTo(Point o) {
            int dx = x - o.x;
            return dx == 0 ? y - o.y : dx;
        }

    }

}
