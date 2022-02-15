package P001_P050;

/*
 In England the currency is made up of pound, £, and pence, p, 
 and there are eight coins in general circulation:
 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
 
 It is possible to make £2 in the following way:
 1x£1 + 1x50p + 2x20p + 1x5p + 1x2p + 3x1p
 How many different ways can £2 be made using any number of coins?
 */
public class P031 {

    static int[] coins = new int[]{200, 100, 50, 20, 10, 5, 2, 1};
    static int count = 0;

    public static int solve() {
        for (int c : coins) {
            solve("", c, 200);
        }
        return count;
    }

    private static void solve(String str, int take, int left) {
        str += take + "-";
        left -= take;
        if (left == 0) {
            //System.out.println(str + "\b");
            count++;
            return;
        }
        for (int c : coins) {
            if (c <= take && c <= left) {
                solve(str, c, left);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(P031.solve());
    }
}
