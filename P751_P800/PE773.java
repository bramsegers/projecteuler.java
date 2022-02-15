package P751_P800;

import util.Util;

public class PE773 {

  public static void main(String[] args) {
    new PE773().solve(97,1000000007);
  }

  void solve(int N,int M) {
    int[] K={1,3,7,9};
    long[][] A=new long[10][2],B;
    for(int k:K) A[k]=new long[]{k,1};
    for(long p=7,q=10;N>0;p+=10){
      if(Util.isPrime(p)){
        B=new long[10][2];
        for(int k:K){
          long m=A[k][0];
          long n=A[k][1];
          B[k][0]=(((p-1)*p/2)%M)*((n*q)%M);
          B[k][0]=((B[k][0]%M)+(m*p)%M)%M;
          B[k][1]=(n*p)%M;
        }
        for(int k:K){
          int n=(7*k)%10;
          B[n][0]=(B[n][0]-p*A[k][0])%M;
          B[n][1]=(B[n][1]-A[k][1])%M;
        }
        A=B;
        q=(q*p)%M;
        N--;
      }
    }
    System.out.println(A[7][0]);
  }
}