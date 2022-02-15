package P101_P150;

/*
 * Let S(A) represent the sum of elements in set A of size n. We shall call it a special sum set 
 * if for any two non-empty disjoint subsets, B and C, the following properties are true:
 * I) S(B) != S(C); that is, sums of subsets cannot be equal.
 * II) If B contains more elements than C then S(B) > S(C).
 * 
 * For this problem we shall assume that a given set contains n strictly 
 * increasing elements and it already satisfies the second rule.
 * 
 * Surprisingly, out of the 25 possible subset pairs that can be obtained from a set 
 * for which n = 4, only 1 of these pairs need to be tested for equality (first rule). 
 * Similarly, when n = 7, only 70 out of the 966 subset pairs need to be tested.
 * 
 * For n = 12, how many of the 261625 subset pairs that can be obtained need to be tested for equality?
 * 
 * Info: http://www.mathblog.dk/project-euler-106-minimum-comparisons-special-sum-sets/
 */
public class P106 {

    public static long solve(int n) {
        long sum = 0;
        for (int i = 1; i <= n / 2; i++) {
            for (int j = i; j <= n - i; j++) {
                long poss = nChooseK(n, i) * nChooseK(n - i, j) / (i == j ? 2 : 1);
                long test = (i == j) ? test(i, n) : 0;
                System.out.println("(" + i + "," + j + ") -> " + poss + " combinations, " + test + " tests");
                sum += test;
            }
        }
        return sum;
    }

    private static long nChooseK(long n, long k) {
        return (n < k) ? 0 : (k == 0 || k == n) ? 1 : nChooseK(n - 1, k - 1) + nChooseK(n - 1, k);
    }

    private static long test(int s, int n) {
        return (nChooseK(n, s) * nChooseK(n - s, s) / 2) - (nChooseK(n, 2 * s) * nChooseK(2 * s, s) / (s + 1));
    }

    public static void main(String[] args) {
        System.out.println(P106.solve(12));
    }
}
