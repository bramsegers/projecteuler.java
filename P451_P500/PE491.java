package P451_P500;

public class PE491 {

    public static void main(String[] args) {
        new PE491().solve();
    }

    int[] nums = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9};
    int mid = 60;
    long[][] m = new long[1 << nums.length][mid << 1];

    void solve() {
        long s = count((1 << nums.length) - 1, 0, 1);
        System.out.println(s);
    }

    long count(int state, int sum, int mult) {
        if (state == 0) {
            return sum % 11 == 0 ? 1 : 0;
        }
        if (m[state][sum + mid] == 0) {
            for (int i = 0; i < nums.length; i += 2) {
                int bit = (state >> i) & 1;
                if (bit == 1) {
                    int newstate = state - (1 << i);
                    int newsum = sum + (mult * nums[i]);
                    m[state][sum + mid] += count(newstate, newsum, -mult);
                } else {
                    bit = (state >> (i + 1)) & 1;
                    if (bit == 1) {
                        if (!(Integer.bitCount(state) == 1 && i == 0)) {
                            int newstate = state - (1 << (i + 1));
                            int newsum = sum + (mult * nums[i]);
                            m[state][sum + mid] += count(newstate, newsum, -mult);
                        }
                    }
                }
            }
        }
        return m[state][sum + mid];
    }

}
