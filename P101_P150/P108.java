package P101_P150;

/*
 * In the following equation x, y, and n are positive integers.
 * 
 *  1     1     1
 *  -  +  -  =  -
 *  x     y     n
 * 
 * For n = 4 there are exactly three distinct solutions:
 * (x,y,n)=(5,20,4)
 * (x,y,n)=(6,12,4)
 * (x,y,n)=(8,8,4)
 * 
 * What is the least value of n for which the number of distinct solutions exceeds one-thousand?
 * Info: http://answers.yahoo.com/question/index?qid=20101205221817AA9vNp7
 */
public class P108 {

    public static long solve(long nMax) {
        long d = 0, n = 0, f = 2 * 3 * 5 * 7;
        while (d < 1000) {
            n += f;
            d = 2;
            for (int i = 2; i < n; i++) {
                d += n * n % i == 0 ? 1 : 0;
            }
            System.out.println(n + " " + d);
        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(P108.solve(1000));
    }
}
