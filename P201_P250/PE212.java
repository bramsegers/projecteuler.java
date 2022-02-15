package P201_P250;
 
import java.util.ArrayList;
import java.util.List;

public class PE212 {

    public static void main(String[] args) {
        new PE212().solve(50000);
    }

    void solve(int nMax) {
        long volume = 0;
        int[] S = new int[6 * nMax + 1];
        for (int k = 1; k <= 6 * nMax; k++) {
            S[k] = k <= 55
                    ? (int) ((100003L - 200003L * k + 300007L * k * k * k) % 1000000L)
                    : (S[k - 24] + S[k - 55]) % 1000000;
        }

        List<Cuboid> cubAdded = new ArrayList<>();
        List<Cuboid> cubSubstracted = new ArrayList<>();
        for (int n = 1; n <= nMax; n++) {
            System.out.println(n);
            int x = S[6 * n - 5] % 10000;
            int y = S[6 * n - 4] % 10000;
            int z = S[6 * n - 3] % 10000;
            int dx = 1 + (S[6 * n - 2] % 399);
            int dy = 1 + (S[6 * n - 1] % 399);
            int dz = 1 + (S[6 * n] % 399);
            Cuboid cuboid = new Cuboid(x, y, z, dx, dy, dz);
            List<Cuboid> newCubAdded = new ArrayList<>();
            List<Cuboid> newCubSubstracted = new ArrayList<>();
            newCubAdded.add(cuboid);
            for (Cuboid c : cubAdded) {
                Cuboid inter = Cuboid.intersect(c, cuboid);
                if (inter != null) {
                    newCubSubstracted.add(inter);
                }
            }
            for (Cuboid c : cubSubstracted) {
                Cuboid inter = Cuboid.intersect(c, cuboid);
                if (inter != null) {
                    newCubAdded.add(inter);
                }
            }
            cubAdded.addAll(newCubAdded);
            cubSubstracted.addAll(newCubSubstracted);
        }
        for (Cuboid c : cubAdded) {
            volume += c.volume();
        }
        for (Cuboid c : cubSubstracted) {
            volume -= c.volume();
        }
        System.out.format("V(%d)=%d%n", nMax, volume);

    }
}

class Cuboid {

    int x, y, z, dx, dy, dz;

    Cuboid(int x0, int y0, int z0, int dx0, int dy0, int dz0) {
        x = x0;
        y = y0;
        z = z0;
        dx = dx0;
        dy = dy0;
        dz = dz0;
    }

    static Cuboid intersect(Cuboid c1, Cuboid c2) {
        int interX0 = Math.max(c1.x, c2.x);
        int interY0 = Math.max(c1.y, c2.y);
        int interZ0 = Math.max(c1.z, c2.z);
        int interX1 = Math.min(c1.x + c1.dx, c2.x + c2.dx);
        int interY1 = Math.min(c1.y + c1.dy, c2.y + c2.dy);
        int interZ1 = Math.min(c1.z + c1.dz, c2.z + c2.dz);
        return (interX0 >= interX1 || interY0 >= interY1 || interZ0 >= interZ1)
                ? null
                : new Cuboid(interX0, interY0, interZ0, interX1 - interX0, interY1 - interY0, interZ1 - interZ0);
    }

    long volume() {
        return (long) dx * dy * dz;
    }
}
