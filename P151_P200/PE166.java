package P151_P200;

public class PE166 {

    public static void main(String[] args) {
        new PE166().solve();
    }

    void solve() {
        long start = System.currentTimeMillis();
        long count = 1;
        int[] num = new int[9];
        while (increment(num)) {
            /* 
             * 0 1 2 a  
             * 3 4 5 b  
             * 6 7 8 c 
             * d e f g      
             */
            int sum = num[0] * 2 + num[1] + num[2] + num[3] + num[4] * 2 + num[5] + num[6] + num[7] + num[8] * 2;
            if (sum % 3 == 0) {
                sum /= 3;
                int a = sum - num[0] - num[1] - num[2];
                if (isValid(a)) {
                    int b = sum - num[3] - num[4] - num[5];
                    if (isValid(b)) {
                        int c = sum - num[6] - num[7] - num[8];
                        if (isValid(c)) {
                            int d = sum - num[0] - num[3] - num[6];
                            if (isValid(d)) {
                                int e = sum - num[1] - num[4] - num[7];
                                if (isValid(e)) {
                                    int f = sum - num[2] - num[5] - num[8];
                                    if (isValid(f)) {
                                        int g = sum - d - e - f;
                                        if (isValid(g)) {
                                            if (a + num[5] + num[7] + d == sum
                                                    && num[0] + num[4] + num[8] + g == sum) {
                                                count++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.format("P=%d%nElapsed:%dms%n", count, end - start);
    }

    boolean isValid(int n) {
        return n >= 0 && n <= 9;
    }

    boolean increment(int[] num) {
        int i = 0;
        while (num[i] == 9) {
            num[i] = 0;
            i++;
            if (i == num.length) {
                return false;
            }
        }
        num[i]++;
        return true;
    }
}
