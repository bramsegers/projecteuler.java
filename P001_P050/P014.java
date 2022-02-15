package P001_P050;

/*
 The following iterative sequence is defined for the set of positive integers:
 n ->  n/2 (n is even)
 n ->  3n + 1 (n is odd)

 Using the rule above and starting with 13, we generate the following sequence:
 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
 It can be seen that this sequence (starting at 13 and finishing at 1) contains 
 10 terms. Although it has not been proved yet (Collatz Problem), it is thought 
 that all starting numbers finish at 1.

 Which starting number, under one million, produces the longest chain?
 NOTE: Once the chain starts the terms are allowed to go above one million.
 */
public class P014 {

    public static String solve(int num) {
        long longestChain = 0;
        long number = 0;
        for (int i = 1; i <= num; i++) {
            long chainLength = 1;
            long curNum = i;
            while (curNum > 1) {
                curNum = (curNum % 2 == 0) ? curNum / 2 : 3 * curNum + 1;
                chainLength++;
            }
            if (chainLength >= longestChain) {
                longestChain = chainLength;
                number = i;
            }
        }
        return "Number:" + number + ", Chain length:" + longestChain;
    }

    public static void main(String[] args) {
        System.out.println(P014.solve(1000000));
    }
}
