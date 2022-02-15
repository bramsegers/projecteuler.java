package P051_P100;

/*
 It is possible to write five as a sum in exactly six different ways:
 4 + 1
 3 + 2
 3 + 1 + 1
 2 + 2 + 1
 2 + 1 + 1 + 1
 1 + 1 + 1 + 1 + 1

 How many different ways can one hundred be written 
 as a sum of at least two positive integers?
 */
public class P076 {

    public static long solve(int num) {
        long sum = 0;
        count = new long[num][num];
        for (int i = 1; i < num; i++) {
            sum += count(i, num - i);
        }
        return sum;
    }

    static long[][] count;

    private static long count(int take, int left) {
        if (left < 0) {
            return 0;
        } else if (left == 0) {
            count[take][left] = 1;
        } else if (count[take][left] == 0) {
            long rv = 0;
            for (int i = 1; i <= take; i++) {
                rv += count(i, left - i);
            }
            count[take][left] = rv;
        }
        return count[take][left];
    }

    public static void main(String[] args) {
        System.out.println(P076.solve(100));
    }
}
