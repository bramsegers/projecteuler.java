package P201_P250;

import java.util.TreeSet;

public class PE247 {

    public static void main(String[] args) {
        new PE247().solve(3);
    }

    void solve(int i) {

        TreeSet<SQ> set = new TreeSet<>();
        set.add(new SQ(0, 0, 1, 0));
        int todo = 1;

        for (int n = 1; todo > 0; n++) {
            SQ q = set.pollFirst();
            SQ right = new SQ(q.ix + 1, q.iy, q.x + q.size, q.y);
            SQ upper = new SQ(q.ix, q.iy + 1, q.x, q.y + q.size);
            set.add(upper);
            set.add(right);
            todo += right.todo(i) + upper.todo(i) - q.todo(i);
            if (q.ix == i && q.iy == i) {
                System.out.println(q + ", index:" + n + ", todo:" + todo);
            }
        }
    }

    class SQ implements Comparable<SQ> {

        int ix, iy;
        double x, y, size;

        public SQ(int ix, int iy, double x, double y) {
            this.ix = ix;
            this.iy = iy;
            this.x = x;
            this.y = y;
            double b = y - x;
            size = -x + (Math.sqrt(b * b + 4) - b) / 2;
        }

        int todo(int n) {
            return (ix > n || iy > n) ? 0 : 1;
        }

        @Override
        public String toString() {
            return "(" + ix + "," + iy + ")";
        }

        @Override
        public int compareTo(SQ o) {
            return size < o.size ? 1 : -1;
        }
    }

}
