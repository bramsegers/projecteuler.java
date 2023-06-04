package P801_P850;

public class PE846 {

  public static void main(String[] args) {
    new PE846().solve(20);
    new PE846().solve(100);
    new PE846().solve(1000000);
  }

  int[] V;
  int[][] D;

  void solve(int N) {

    V = new int[200000];
    D = new int[200000][300];

    int v = V[1] = ++V[0] + 1;
    for (int p : util.Util.getPrimes(N)){
      for (int q=1; p>2 && q<=N/p;) V[v++] = q*=p;
      for (int q=2; p>2 && q<=N/p;) V[v++] = q*=p;
    }

    for (int i=0; i<v; i++){
      for (int j=i+1; j<v; j++){
        long r = 1L*V[i]*V[j]-1;
        long s = (long) Math.sqrt(r);
        if (r>s*s) continue;
        D[i][++D[i][0]] = j;
        D[j][++D[j][0]] = i;
      }
    }

    final int i = v;
    long ans = java.util.stream
      .IntStream
      .range(0,i)
      .parallel()
      .mapToLong(k -> D[k][0]<1 ? 0 : dfs(V[k],k,k,1,new int[i]))
      .sum()/2;

    System.out.format("F(%d)=%d%n", N, ans);
  }

  long dfs(long pot, int firsti, int lasti, int len, int[] seen) {
    long sum = 0;
    seen[lasti] = 1;
    int first = V[firsti];
    int[] a = D[lasti];
    for (int i=a[0]; i>0;){
      int nexti = a[i--];
      int next = V[nexti];
      if (first==next && len>2) sum += pot; else
      if (first< next && seen[nexti]<1) sum += dfs(pot+next, firsti, nexti, len+1, seen);
    }
    seen[lasti] = 0;
    return sum;
  }

}