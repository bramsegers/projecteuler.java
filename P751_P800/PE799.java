package P751_P800;
import util.Primes;
public class PE799{

  int     M     = 20000000;
  int     N     = 50000000;
  int[]   Q     = new int[N];
  Primes  P     = new Primes(3*M);
  long p(int n) {return 1L*n*(3*n-1)/2;}

  void solve(){
    for(int f[][],c=1;c<M;c++)dfs(f=P.fact2(c,3*c-1),f.length-1,1,2*p(c),c);
    for(int m=0,a=0;a<N;a++)if(Q[a]>m)System.out.format("%d: %d%n",m=Q[a],p(a));
  }

  void dfs(int f[][],int i,long p,long n,long c){
    if(i<0&&(n=3*p+n/p+1)%6<1&&(n/=6)<N&&n-p>=c)Q[(int)n]++;else
    if(i>=0)for(int j=0;j<=f[i][1]&&p*p<=n;j++){dfs(f,i-1,p,n,c);p*=f[i][0];}
  }

  public static void main(String[] args){new PE799().solve();}

}