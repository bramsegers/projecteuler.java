package P351_P400;

public class PE368{

  public static void main(String[] args){
    new PE368().solve();
  }

  void solve(){

    int a,b,c,i,j,k,n;
    double m,p,q,r,s[][],t[][][];

    r = 0;
    n = 40;
    s = new double[3*n][3*n];
    t = new double[4][10][n+1];

    for (i=1;i<100;i++) r += 1.0/i;
    for (i=0;i<3*n;i++) for (j=0;j<=i;j++) 
    if (n>0) s[i][j]=j<1?1:s[i-1][j]+s[i-1][j-1];

    for (i=0; i<10; r+=t[1][i][1]+t[3][i++][1])
    for (j=0; n>j++;) for (t[1][i][j]=t[3][i][j]=0,k=10;k<=99;k++)
    if (i!=k%10 || k%10!=k/10) t[k%10!=i?1:3][i][j] += Math.pow(10*k+i,-j);

    for (i=3; i++<4000;) for (c=i&1, j=0; j<10; r+=t[c][j][1]+t[c+2][j++][1])
    for (k=0,m=10; k++<n;m*=10) for (t[c][j][k]=t[c+2][j][k]=a=0,p=1;a<=n;a++,p*=-.1*j)
    for (b=0, q=p*s[a+k-1][k-1]/m, t[c+2][j][k]+=q*(k+a<=n?t[c^1][j][k+a]:0); b<10; b++)
    if (j!=b) t[c][j][k]+=q*(k+a<=n?t[c^1][b][k+a]+t[3-c][b][k+a]:0);

    System.out.format("K=%.10f%n",r);
  }

}
