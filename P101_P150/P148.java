package P101_P150;

/*
 * We can easily verify that none of the entries in the first seven rows of 
 * Pascal's triangle are divisible by 7:
 *                   1
 *                1     1
 *             1     2     1
 *          1     3     3     1
 *       1     4     6     4     1
 *    1     5    10    10     5     1     
 * 1     6    15    20    15     6     1
 * However, if we check the first one hundred rows, we will find that only 2361 
 * of the 5050 entries are not divisible by 7.
 * 
 * Find the number of entries which are not divisible by 7 in the first 
 * one billion (10^9) rows of Pascal's triangle.
 * 
 * Info: http://www.discreteteaching.com/Pascaler/pdf/PascalTalk.pdf
 */
public class P148 {

    public static long solve(int rows, int div) {
        int printcut = rows / 100;
        long sum = 0;
        for (int i = 0; i < rows; i++) {
            int p = 1;
            for (char ch : Integer.toString(i, div).toCharArray()) {
                p *= ch - '0' + 1;
            }
            sum += p;
            if (i % printcut == 0) {
                System.out.println("row " + i + ": " + p);
            }
        }
        return sum;
    }

    public static long solve2(int rows, int div) {
        int p=1,I=1;
        while((p*=div)<rows){
            I++;
        }
        long[][] units = new long[I][div + 1];
        for (int i = 0; i < I; i++) {
            for (int j = 0; j <= div; j++) {
                if (i == 0) {
                    units[i][j] = (0 + j) * (j + 1) / 2;
                } else {
                    units[i][j] = (0 + j) * (j + 1) / 2 * units[i - 1][div];
                }
            }
        }
        long count = 0;
        int index = 0;
        while (rows > 0) {
            int mod = rows % div;
            count = count * (mod + 1) + units[index][mod];
            rows /= div;
            index++;
        }
        return count;
    }

    public static void main(String[] args) {
        //System.out.println(solve(1000000000, 7));
        System.out.println(solve2(1000000000, 7));
    }
}
