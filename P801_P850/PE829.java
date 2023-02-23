package P801_P850;

public class PE829 {

  public static void main(String[] args) {
    new PE829().solve();
  }

  long A,B,S,M;
  int[] P = {2,3,5,7,11,13,17,19,23,29,31};

  void solve() {
    long ans = 0;
    String f = "%3s%21s%20s", s="%n";
    System.out.format(f+s+s, "n", "n!!", "M(n)");
    for (int e,n=2; n<32; n++){
      long d = dfact(n);
      S=new Tree(d).id(1); M=d; e=0;
      for (int[] t:factors(d)) e+=t[1];
      M(0,1,e); ans += M;
      System.out.format(f+s, n, d, M);
    }
    System.out.format(s+f+s+s, "ans", "=", ans);
  }

  long dfact(long n) {
    return n<1? 1 : n * dfact(n-2);
  }

  void M(int i, long n, int e) {
    if (e<1 && S==new Tree(n).id(1)) M = n;
    if (i>8 || e<1) return;
    for (;n<=M && e>=0; n *= P[i])
      M(i+1, n, e--);
  }

  void ab(int i, long a, long b, int[][] f) {
    if (b-a < B-A) {A=a; B=b;}
    int p = f[i][0];
    int e = f[i][1];
    if (p<1) return;
    for (int k=0; k<=e && a<=b; k++, a*=p, b/=p)
      ab(i+1,a,b,f);
  }

  int[][] factors(long p) {
    int i,j,k;
    int[][] f = new int[11][2];
    for (i=0,j=0;j<11 && p>1; j++) {
      for (k=0; p%P[j]<1; k++) p/=P[j];
      if (k>0) {f[i][0]=P[j]; f[i++][1]=k;}
    }
    return f;
  }

  class Tree {
    long N;
    Tree L,R;
    Tree(long n) {
      ab(0, A=1, B=n, factors(N=n));
      if ((n=A)<2) return;
      L = new Tree(n);
      R = new Tree(N/n);
    }
    long id(long d) {
      long id = 1L<<d;
      if (L!=null) id+=L.id(2*d);
      if (R!=null) id+=R.id(2*d+1);
      return id;
    }
  }

}