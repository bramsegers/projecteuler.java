package P701_P750;

class PE703 {

  public static void main(String[] args) {
    new PE703().solve(3);
    new PE703().solve(4);
    new PE703().solve(20);
  }

  int[][] T;
  long M = 1001001011;
  long[] A,B,C,D,Q,R,S;
  
  void solve(int N) {    
  
    long s=1;
    int i,j,k=1<<N;
    
    A = new long[k];
    B = new long[k];
    C = new long[k];
    D = new long[k];
    Q = new long[k];
    R = new long[k];
    T = new int[k][4];
    
    for (i=0; ++i<k;) {
      j = (i&(1<<(N-3)))>0?1:0;
      j^= (i&(1<<(N-2)))>0?1:0;
      j&= (i&(1<<(N-1)))>0?1:0;
      j+= 2*(i&((1<<(N-1))-1));
      T[i][++T[i][0]] = j;
      T[j][++T[j][0]] = i;
    }  
    
    for (j=0; j<2; j++)
      for (S=new long[k], i=0; ++i<k;) if (S[i]<1) 
        if (j<1) {f(i,i,i); R[i]=-1; Q[i]=S[i]=1;} else
        if (j>0) {g(i,i,i); s=(s*(A[i]+B[i]+(R[i]<0?0:C[i]+D[i])))% M;}   
    
    System.out.format("S(%d) = %d%n", N, s);
  }

  void f(int p, int q, int r) {
    for (int s, i=0; i<T[p][0]; i++)
      if ((s=T[p][i+1])==q) {} else
      if (S[s]>0 && Q[s]<Q[p]) R[r]=p; else
      if (S[s]<1) {
        Q[s]=Q[p]+1;
        S[s]=1;
        f(s,p,r);
      }
  }

  void g(int p, int q, int r) {    
    if (T[p][0]>0 && p==R[r]) {A[p]=D[p]=1; return;}
    if (p<(S[p]=1)) {A[p]=C[p]=1; B[p]=D[p]=0; return;}
    if (T[p][0]==(D[p]=C[p]=B[p]=A[p]=1) && p<r) {return;}
    for (int x, i=0; i<T[p][0];)
      if ((x=T[p][++i])==R[r]) D[p]=0; else
      if (x!=q && Q[x]<Q[p]) R[r]=(C[p]=B[p]=0)+p; else
      if (S[x]<1){ g(x,p,r); 
        D[p]=(D[p]*C[x])%M;
        C[p]=(C[p]*(D[x]+C[x]))%M;
        B[p]=(B[p]*A[x])%M;
        A[p]=(A[p]*(A[x]+B[x]))%M;
      }
  }

}
