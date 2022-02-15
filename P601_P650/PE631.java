package P601_P650;

public class PE631 {

    //f(1000000000000000000,40) mod 1000000007 = 869588692
    //BUILD SUCCESSFUL (total time: 186 minutes 45 seconds)
    public static void main(String[] args) {
        new PE631().solve(10,25,1000000);
        new PE631().solve((long)1e18,40,1000000007);
    }

    int n,N,M;
    long[][] count;

    void solve(long _n, int _m, int mod) {        
        N=(int)Math.min(_m+2,_n);
        M=_m;
        count=new long[N+1][M+1];        
        for(n=0;n<=N;n++){
            System.out.println(n+"/"+N);
            solve(0,(1L<<n)-1,0,new int[n],0);
        }
        long sum=0,tsum=0;
        for(int i=0;i<=N;i++){
            sum=0;
            for(int j=0;j<=M;j++) 
                sum+=count[i][j];
            sum%=mod;
            tsum+=sum;
            tsum%=mod;
        }
        tsum+=((_n-n+1)%mod)*sum;
        tsum%=mod;
        String out="f(%d,%d) mod %d = %d%n";
        System.out.format(out,_n,_m,mod,tsum);
    }

    void solve(int i, long p, long q, int[] qa, int t) {
        if(!valid1243(qa,i,p)) return;
        if(!valid21(q,i,M-t)) return;
        if(p==0) {count[n][t]++;return;}
        long jb,p2=p;
        for(int c=i,j=0;p2>0;j++){
            if(((q>>j)&1)==1) c--;
            if((p2&1)==0) {p2>>=1;continue;}
            p2>>=1;
            jb=1L<<j;
            qa[i]=j;            
            solve(i+1,p&(~jb),q|jb,qa,t+c);
        }
    }

    boolean valid1243(int[] qa, int n, long p) {
        if(n<3) return true;
        int c=qa[n-1];
        for(int i=n-2;i>=0;i--){
            int b=qa[i];
            if(c-b<2) continue;
            for(int j=i-1;j>=0;j--){
                int a=qa[j];
                if(a>b) continue;
                long q=((1L<<(c-b-1))-1)<<(b+1);
                if((p&q)>0) return false;
            }
        }
        return true;
    }
    
    boolean valid21(long q, int n, int m){
        int c=0;
        while(q>0){
            if((q&1)==1) n--;
            else if((c+=n)>m) return false;
            q>>=1;
        }
        return true;
    }    
   
}
