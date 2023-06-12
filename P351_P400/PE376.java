package P351_P400;

public class PE376 {

  public static void main(String[] args) {
    new PE376().solve(7);
    new PE376().solve(30);
  }

  void solve(int n) {
    long s = f(n,0,6,6,6,0,0,0) / 3;
    System.out.format("S(%d) = %d%n", n,s);
  }

  long f(int n, int k, int a, int b, int c, int A, int B, int C){
    if (A>=18 || B>=18 || C>=18 || k>n) return 0;
    if (a+b+c<1) return nCr(n,k);
    long v=0;
    for (int p=0; p<=a; p++)
      for (int q=0; q<=b; q++)
        for (int r=p+q<1?1:0; r<=c; r++)
            v += f(n,k+1,a-p,b-q,c-r,A+p*c,B+q*a,C+r*b);
    return v;
  }
  
  long nCr(int n, int k){
    long i=0,c=1;
    while (i<k) c=c*(n-i)/++i;
    return c;
  }
 
}