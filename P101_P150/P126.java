package P101_P150;

/*
 * The minimum number of cubes to cover every visible face on a cuboid measuring 
 * 3 x 2 x 1 is 22. If we then add a second layer to this solid it would require 
 * 46 cubes to cover every visible face, the third layer would require 78 cubes, 
 * and the fourth layer would require 118 cubes to cover every visible face.
 * 
 * However, the first layer on a cuboid measuring 5 x 1 x 1 also requires 22 cubes.
 * Similarly the first layer on cuboids measuring 5 x 3 x 1, 7 x 2 x 1, and 
 * 11 x 1 x 1 all contain 46 cubes.
 * 
 * We shall define C(n) to represent the number of cuboids that contain n cubes 
 * in one of its layers. So C(22) = 2, C(46) = 4, C(78) = 5, and C(118) = 8.
 * It turns out that 154 is the least value of n for which C(n) = 10.
 * 
 * Find the least value of n for which C(n) = 1000.
 */
public class P126 {

    public static int solve(int num) {
        int limit = 20000;
        int[] count = new int[limit + 1];
        for (int z = 1; cubes(z, z, z, 1) <= limit; ++z) {
            for (int y = z; cubes(z, y, z, 1) <= limit; ++y) {
                for (int x = y; cubes(z, y, x, 1) <= limit; ++x) {
                    for (int n = 1; cubes(z, y, x, n) <= limit; ++n) {
                        count[cubes(z, y, x, n)]++;
                    }
                }
            }
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] == num) {
                return i;
            }
        }
        return -1;
    }

    private static int cubes(int x, int y, int z, int n) {
        return 2 * (x * y + y * z + x * z) + 4 * (x + y + z + n - 2) * (n - 1);
    }

    public static void main(String[] args) {
        System.out.println(P126.solve(1000));
    }
}
