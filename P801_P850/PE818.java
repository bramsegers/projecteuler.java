package P801_P850;

class PE818 {

  public static void main(String[] args) {
    new PE818().solve(3);
    new PE818().solve(6);
    new PE818().solve(12);
  }

  int C;
  long[] MUL = new long[16];
  int[][] SET = new int[1080][];

  void solve(int n) {
    int i,j,k;
    for (i=-1;++i<81;)
      for (j=i; ++j<81;)
        for (k=j; ++k<81;)
          set(i,j,k);

    for (add(0),i=0; ++i<1080;)
      for (add(0,i),j=i; ++j<1080;)
        for (add(0,i,j),k=j; ++k<1080;)
          add(0,i,j,k);

    long f=0;
    for (i=0;++i<=n;) f += nCr(81-i,n-i) * MUL[i];
    System.out.format("F(%d) = %d%n", n,f);
  }

  void set(int... h) {
    int c,i,j,k;
    for (i=-1;++i<3;) for (j=i; ++j<3;){
    for (c=0,k=1; k<81; k*=3) c+=((81+81-h[i]/k-h[j]/k)%3)*k;
    if (h[0]==c||h[1]==c||h[2]==c) {SET[C++]=h; return;}}
  }

  void add(int... s) {
    int t=0, n=s.length-1;
    int[] a=new int[81], m=new int[]{1,7,12,6};
    for (int i:s) for (int j:SET[i]) t += ++a[j]<2?1:0;
    MUL[t] += m[n]*1080;
  }
  
  long nCr(long n,long r) {
    long i=0,c=1;
    while (i<r) c=c*(n-i)/++i;
    return c;
  }

}