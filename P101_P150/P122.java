package P101_P150;

/*
 * The most naive way of computing n^15 requires fourteen multiplications:
 * n * n * ... * n = n^15
 * 
 * But using a "binary" method you can compute it in six multiplications:
 * n * n = n^2
 * n^2 * n^2 = n^4
 * n^4 * n^4 = n^8
 * n^8 * n^4 = n^12
 * n^12 * n^2 = n^14
 * n^14 * n = n^15
 * 
 * However it is yet possible to compute it in only five multiplications:
 * n * n = n^2
 * n^2 * n = n^3
 * n^3 * n^3 = n^6
 * n^6 * n^6 = n^12
 * n^12 * n^3 = n^15
 * 
 * We shall define m(k) to be the minimum number of multiplications to compute 
 * n^k; for example m(15) = 5.
 * 
 * For 1 <= k <= 200, find Σ m(k).
 */
public class P122 {

    public static int solve(int p) {
        int sum = 0;
        for (int n = 1; n <= p; n++) {
            maxPower = n;
            minSteps = n;
            Node nd = new Node(1, null);
            solve(nd);
            System.out.println("m(" + n + ")=" + minSteps);
            sum += minSteps;
        }
        return sum;
    }

    private static int maxPower, minSteps;

    private static void solve(Node n) {
        if (n.power == maxPower) {
            minSteps = Math.min(minSteps, n.steps);
            return;
        }
        if (n.power * 2 <= maxPower) {
            solve(new Node(n.power * 2, n));
        }
        if (n.power < maxPower && n.steps < minSteps) {
            Node p = n.parent;
            while (p != null) {
                solve(new Node(n.power + p.power, n));
                p = p.parent;
            }
        }
    }

    private static class Node {

        private Node parent;
        private int power;
        private int steps;

        public Node(int p, Node n) {
            power = p;
            parent = n;
            steps = (n == null) ? 0 : n.steps + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(P122.solve(200));
    }
}
