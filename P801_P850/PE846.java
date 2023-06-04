package P801_P850;

public class PE846 {

  public static void main(String[] args) {
    new PE846().solve(20);
    new PE846().solve(100);
    new PE846().solve(1000000);
  }

  int[] NUMS;
  int[][] PAIR;

  void solve(int N) {

    NUMS = new int[200000];
    PAIR = new int[200000][300];

    int[] PRIMES = new int[N];
    int v = NUMS[1] = ++NUMS[0] + 1;
    for (int p=1; (p+=2)<N;){
      if (PRIMES[p]>0) continue;
      for (int q=p; (q+=p)<N;) PRIMES[q] = 1;
      for (int q=1; q<=N/p;) NUMS[v++] = q*=p;
      for (int q=2; q<=N/p;) NUMS[v++] = q*=p;
    }

    for (int i=0; i<v; i++){
      for (int j=i+1; j<v; j++){
        long r = 1L*NUMS[i]*NUMS[j]-1;
        long s = (long) Math.sqrt(r);
        if (r>s*s) continue;
        PAIR[i][++PAIR[i][0]] = j;
        PAIR[j][++PAIR[j][0]] = i;
      }
    }

    final int i = v;
    long ans = java.util.stream
      .IntStream
      .range(0,i)
      .parallel()
      .mapToLong(k -> dfs(NUMS[k],k,k,1,new int[i]))
      .sum()/2;

    System.out.format("F(%d)=%d%n", N, ans);
  }

  long dfs(long pot, int firsti, int lasti, int len, int[] seen) {
    long sum = 0;
    seen[lasti] = 1;
    int first = NUMS[firsti];
    int[] pair = PAIR[lasti];
    for (int i=pair[0]; i>0;){
      int nexti = pair[i--];
      int next = NUMS[nexti];
      if (first==next && len>2) sum += pot;
      if (first< next && seen[nexti]<1) sum += dfs(pot + next, firsti, nexti, len + 1, seen);
    }
    seen[lasti] = 0;
    return sum;
  }

}
