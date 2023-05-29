package P801_P850;

public class PE845 {

  public static void main(String[] args) {
    new PE845().D((long)61);
    new PE845().D((long)1e8);
    new PE845().D((long)1e16);
  }

  int[] P = new int[200];
  long[] M = new long[10000];

  void D(long n) {
    java.util.Arrays.fill(P,2,200,1);
    for (int i=1; ++i<200;) if(P[i]>0)
    for (int j=i; i+j<200;) P[j+=i]=0;
    long a=0, b=(long)1e18;
    while (a+1<b) {
      long c = (a+b)/2;
      java.util.Arrays.fill(M, -1);
      if (f(""+c,0,0,1) < n) a = c;
      else b = c;
    }
    System.out.format("D(%d)=%d%n",n,b);
  }

  long f(String n, int i, int t, int u) {
    if (i==n.length()) return P[t];
    int k = (t<<6)+(i<<1)+u;
    if (M[k]>=0) return M[k];
    int d = n.charAt(i)-'0', v;
    for (v=(u<1?9:d); v>=0; v--, u=0)
      M[k] += f(n, i+1, t+v, u);
    return ++M[k];
  }

}
