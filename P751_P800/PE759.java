package P751_P800;

public class PE759 {
 
  public static void main(String[] args) {
    new PE759().solve((long)1e01,1000000007);
    new PE759().solve((long)1e02,1000000007);
    new PE759().solve((long)1e16,1000000007);
  }
 
  void solve(long n,long m){
    int i,j=(int)(Math.log((n+1))/Math.log(2));
    long A[][],B[][],C[][],p,q;
    A=new long[3][3];
    B=new long[3][3];
    C=new long[3][3];
    A[0][0]=B[0][0]=1;    
    while(j>0){
      p=((n+1)>>  j)%2>0?3:0;
      q=((n+1)>>--j)%2>0?9:0;
      for(i=0;i<6;i++) B[(i&1)+1][i/2]<<=(i&1)+1;
      for(i=0;i<p;i++) B[2][i]+=2*B[1][i]+B[0][i];
      for(i=0;i<p;i++) B[1][i]+=B[0][i];
      for(i=0;i<p;i++) B[i][2]+=2*B[i][1]+B[i][0];
      for(i=0;i<p;i++) B[i][1]+=B[i][0];
      for(i=0;i<9;i++) B[i/3][i%3]%=m;
      for(i=0;i<6;i++) A[1+(i/3)][i%3]<<=(1+(i/3));
      for(i=0;i<9;i++) C[i/3][i%3]=A[i/3][i%3];
      for(i=0;i<3;i++) A[2][i]+=2*A[1][i]+A[0][i];
      for(i=0;i<3;i++) A[1][i]+=A[0][i];
      for(i=0;i<3;i++) A[i][2]+=2*A[i][1]+A[i][0];
      for(i=0;i<3;i++) A[i][1]+=A[i][0];
      for(i=0;i<9;i++) C[i/3][i%3]+=A[i/3][i%3];
      for(i=0;i<q;i++) C[i/3][i%3]+=B[i/3][i%3]<<(i/3);
      for(i=0;i<9;i++) A[i/3][i%3]=C[i/3][i%3]%m;
    }
    System.out.println(A[2][2]);
  }

}