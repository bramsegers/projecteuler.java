package P001_P050;

/*
 An irrational decimal fraction is created by concatenating the positive integers:
 0.123456789101112131415161718192021...
 It can be seen that the 12th digit of the fractional part is 1.

 If d(n) represents the nth digit of the fractional part, find the value 
 of the following expression:
 d(1) x d(10) x d(100) x d(1000) x d(10000) x d(100000) x d(1000000)
 */
public class P040 {

    public static int solve(int... nums) {
        int trunc = 1000;
        int prod = 1;
        int done = 0;
        int i = 0;
        String digits = "";
        while (done < nums.length) {
            digits += ++i;
            if (digits.length() > trunc) {
                for (int j = done; j < nums.length; j++) {
                    if (nums[j] <= trunc) {
                        prod *= digits.charAt(nums[j] - 1) - '0';
                        done++;
                    }
                    nums[j] -= trunc;
                }
                digits = digits.substring(trunc);
            }
        }
        return prod;
    }

    public static int solve2(int... nums) {
        int prod = 1;
        int done = 0;
        int i = 0, t = 10, d = 1, c = 0;
        while (done < nums.length) {
            if (++i % t == 0) {
                t *= 10;
                d++;
            }
            c += d;
            if (c >= nums[done]) {
                int j = i, p = 0;
                for (int k = 0; k < c - nums[done] + 1; k++) {
                    p = j % 10;
                    j /= 10;
                }
                prod *= p;
                done++;
            }
        }
        return prod;
    }

    public static void main(String[] args) {
        System.out.println(P040.solve(1, 10, 100, 1000, 10000, 100000, 1000000));
        System.out.println(P040.solve2(1, 10, 100, 1000, 10000, 100000, 1000000));
    }
}
