package P851_P900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class PE856 {

  public static void main(String[] args) {
    new PE856().solve(13, 4);
  }

  void solve(int R, int S) {

    int i,j,r;
    double e,p,q;
    var u = new ArrayList<Integer>();
    var a = new int[R]; Arrays.fill(a, S);
    var m = new HashMap<State, Double>();
    var k = new State(-1, a); m.put(k, 1d);

    for (e=0, r=1; r <= R*S; r++) {
      var n = new HashMap<State, Double>();
      for (var s : m.keySet()){
        for (p = m.get(s), i=0; i < R; i++) {
          if (s.d[i] < 1) continue;
          q = s.d[i] / (R*S - r + 1d);
          if (i==s.p || r==R*S) {e += p*q*r; continue;}
          for (j=0; j < R; j++) if (i!=j) u.add(s.d[j]);
          Collections.sort(u); u.add(i, s.d[i] - 1);
          k = new State(i, u.stream().mapToInt(z->z).toArray());
          u.clear(); n.put(k, n.getOrDefault(k, 0d) + p*q);
        }
      }
      m = n;
    }

    System.out.format("P(%d,%d) = %.8f%n", R, S, e);
  }

  record State(int p, int[] d) {
    public int hashCode() {return p + 31 * Arrays.hashCode(d);}
    public boolean equals(Object o) {return p == ((State) o).p && Arrays.equals(d, ((State) o).d);}
  }

}