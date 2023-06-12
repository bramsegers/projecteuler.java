package P351_P400;

import static util.Util.matrixPow;

public class PE377 {

  public static void main(String[] args) {
    new PE377().solve();
  }

  void solve() {

    long a,b;
    int i,j,k = (int)1e9;
    long[][] p = new long[2][10];
    long[][] q = new long[18][18], r;

    for (i=0, p[1][0]=1; ++i<10;)
      for (j=0; ++j<=i; p[1][i] += p[1][i-j])
        p[0][i] += 10*p[0][i-j] + j*p[1][i-j];

    for (i=0; i<9;) q[0][i++]=10;
    for (i=1; i<9;) q[i][i-1]=q[i+9][++i+7]=1;
    for (i=0; i<9;) q[0][i+9]=i+(q[9][++i+8]=1);

    for(a=i=0,b=1; (b*=13)>0 && ++i<=17;)
      for(j=0, r=matrixPow(q,b-9,k); j<9; j++)
        a=(a+(r[0][j])*p[0][9-j] + (r[0][9+j])*p[1][9-j])%k;

    System.out.println(a);
  }

}