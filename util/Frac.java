package util;

public class Frac implements Comparable<Frac> {

    public final long P, Q;

    public Frac(long p, long q) {
        if (q == 0) {
            throw new ArithmeticException("Division by zero");
        }
        if (p < 0 && q < 0) {
            q = -q;
            p = -p;
        }
        if (q < 0) {
            q = -q;
            p = -p;
        }
        long gcd = Util.gcd(Math.abs(p), q);
        P = p / gcd;
        Q = q / gcd;
    }

    public Frac add(Frac f) {
        long p = P * f.Q + Q * f.P;
        long q = Q * f.Q;
        return new Frac(p, q);
    }

    public Frac sub(Frac f) {
        long p = P * f.Q - Q * f.P;
        long q = Q * f.Q;
        return new Frac(p, q);
    }

    public Frac mul(Frac f) {
        long p = P * f.P;
        long q = Q * f.Q;
        return new Frac(p, q);
    }

    public Frac div(Frac f) {
        long p = P * f.Q;
        long q = Q * f.P;
        return new Frac(p, q);
    }

    public Frac pow(int i) {
        Frac f = new Frac(1, 1);
        for (int j = 0; j < Math.abs(i); j++) {
            f = f.mul(this);
        }
        return i < 0 ? new Frac(f.Q, f.P) : f;
    }

    public boolean gt(Frac o) {
        return P * o.Q > Q * o.P;
    }

    public boolean lt(Frac o) {
        return P * o.Q < Q * o.P;
    }

    @Override
    public String toString() {
        return Q == 1 ? P + "" : P + "/" + Q;
    }

    @Override
    public int compareTo(Frac o) {
        return equals(o) ? 0 : gt(o) ? 1 : -1;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.P ^ (this.P >>> 32));
        hash = 89 * hash + (int) (this.Q ^ (this.Q >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Frac
            && P == ((Frac) o).P
            && Q == ((Frac) o).Q;
    }

}
