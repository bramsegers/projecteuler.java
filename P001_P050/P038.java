package P001_P050;

/*
 Take the number 192 and multiply it by each of 1, 2, and 3:
 192 x 1 = 192
 192 x 2 = 384
 192 x 3 = 576
 By concatenating each product we get the 1 to 9 pandigital, 192384576. 
 We will call 192384576 the concatenated product of 192 and (1,2,3)

 The same can be achieved by starting with 9 and multiplying by 1,2 3 4, and 5, 
 giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).

 What is the largest 1 to 9 pandigital 9-digit number that can be formed 
 as the concatenated product of an integer with (1,2, ... , n) where n>1?
 */
public class P038 {

    public static int solve() {
        int panDigit=0;
        for (int i = 1; i < 10000; i++) {
            String prod = "";
            for (int n = 1; prod.length() < 10; n++) {
                prod += n * i;
                if (prod.length() == 9 && isPanDigital(prod)) {
                    System.out.println(i + "->" + n + ": " + prod);
                    panDigit=Math.max(panDigit, Integer.parseInt(prod));
                }
            }
        }
        return panDigit;
    }

    public static boolean isPanDigital(String prod) {
        for (int i = 1; i < 10; i++) {
            if (!prod.contains("" + i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(P038.solve());
    }
}
