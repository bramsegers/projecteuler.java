package P851_P900;

public class PE868 {

  public static void main(String[] args) {
    new PE868().solve("BELFRY");
    new PE868().solve("NOWPICKBELFRYMATHS");
  }

  void solve(String s) {
    System.out.format("S(\"%s\") = %d%n", s, f(s));
  }

  long f(String s) {
    int n = s.length();
    if (n < 2) return 0;
    int i = s.indexOf(s.chars().max().getAsInt());
    long r = f(s.substring(0, i) + s.substring(i + 1));
    return r * n + (r % 2 > 0 ? i : n - 1 - i);
  }
}
