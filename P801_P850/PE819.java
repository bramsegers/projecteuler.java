package P801_P850;

class PE819 {

  public static void main(String[] args){
    new PE819().solve(5);
    new PE819().solve(1000);
  }
  
  double[][] square(int n,double[][] m){
    int i,j,k;
    double s,a[],b[],t[][];
    b=new double[n];
    t=new double[n][n];
    for(j=0;j<n;j++){
      for(i=0;i<n;i++)
        b[i]=m[i][j];
      for(i=0;i<n;t[i++][j]=s) 
        for(a=m[i],s=k=0;k<n;k++)
          s+=a[k]*b[k];
    }
    return t;
  }

  void solve(int n){
    int i,j;
    double v,b,t[][];
    t=new double[n+1][n+1];
    for(i=1;i<=n;i++)
      for(b=j=1;j<=n;j++){
        b=b*(n-j+1)/j;
        v=Math.pow((1d*n-i)/n,n-j)*b;
        v*=Math.pow(1d*i/n,j);
        t[j-1][i-1]=v;
      }
    t[n][n]=t[n][n-1]=1;
    for(i=0;i<15;i++)
      t=square(n+1,t);
    v=t[n][n-1]-t[n][0]*n;
    System.out.format("E(%d)=%.6f%n",n,v);
  }
  
}