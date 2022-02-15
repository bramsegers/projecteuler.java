package P551_P600;

public class PE600 {

    public static void main(String[] args) {
        new PE600().solve(100);
        new PE600().solve(55106);
        //H(55106)=2668608479740672  32 minutes 17 seconds
    }

    void solve(int N) {
        long count = 0;
        for (int i = 1; 2 * i + 4 <= N; i++) {
            for (int j = i + 1; j - i <= i && 4 * j - 2 * i <= N; j++) {
                for (int k = i; j - k <= i && 4 * j - i - k <= N; k--) {
                    count += Math.min(i - j + k, (N - 4 * j + i + k) / 2) + 1;
                }
            }
        }
        System.out.format("H(%d)=%d%n", N, count);
    }

}
