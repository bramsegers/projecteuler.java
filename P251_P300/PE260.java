package P251_P300;

public class PE260 {

    public static void main(String[] args) {
        new PE260().solve(1000);
    }

    void solve(int max) {
        boolean[][] one = new boolean[max + 1][max + 1]; // one[n][m] = found loser n,m,x
        boolean[][] two = new boolean[max + 1][max + 1]; // two[n][m] = found loser n,x,x+m
        boolean[][] three = new boolean[max + 1][max + 1]; // three[n][m] = found loser x,x+n,x+m

        one[0][0] = true;
        two[0][0] = true;
        three[0][0] = true;

        long total = 0;

        for (int c = 1; c <= max; c++) {
            for (int b = 0; b <= c; b++) {
                for (int a = 0; a <= b; a++) {
                    if (!(one[a][b]
                            || one[a][c]
                            || two[a][c - b]
                            || two[b][c - a]
                            || two[c][b - a]
                            || three[b - a][c - a])) {
                        one[a][b] = true;
                        one[a][c] = true;
                        one[b][c] = true;
                        two[a][c - b] = true;
                        two[b][c - a] = true;
                        two[c][b - a] = true;
                        three[b - a][c - a] = true;
                        total += a + b + c;
                        break;
                    }
                }
            }
        }
        System.out.println(total);
    }

}
