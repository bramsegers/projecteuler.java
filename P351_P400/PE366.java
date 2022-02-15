package P351_P400;

public class PE366{

    public static void main(String[] args){
        new PE366().brute(100);
        //new PE366().solve((long)1e18,(long)1e8);
    }

    void solve(long n, long m){
        long[] f=new long[90];
        for(int i=0;i<90;i++)
            f[i]=i<2?1:f[i-2]+f[i-1];        
        long f1,f2,c=2,sum=0;        
        for(int j=2;c<n;j++){
            for(int i,k=0;k<j/2&&c<n;k++){
                i=j-2*k;
                f1=k==0?-1:(f[i-2]+f[i-1]-1)/2;
                f2=(f[i-1]+f[i]-1)/2;
                c+=f2-f1;
                f2-=c>n?c-n:0;
                sum+=tri(f2,m);
                sum-=tri(f1,m);
                sum%=m;
            }
        }
        System.out.println(sum);
    }
    
    long tri(long n,long m){
        return (n&1)==0
            ?(((n/2)%m)*((n+1)%m))%m
            :((n%m)*(((n+1)/2)%m))%m;
    }
    
    /*
        https://oeis.org/A201864
        0   1:  (0)
        1   1:  (0)
        2   2:  (0,1)
        3   3:  (0,2)
        4   5:  (0,3)   (1)
        5   8:  (0,6)   (2)
        6  13:  (0,10)  (3)     (1)
        7  21:  (0,16)  (4,6)   (2) 
        8  34:  (0,27)  (7,10)  (3)     (1)
        9  55:  (0,44)  (11,16) (4,6)   (2)
       10  89:  (0,71)  (17,27) (7,10)  (3)    (1)
       11 144:  (0,116) (28,44) (11,16) (4,6)  (2)
       12 233:  (0,188) (45,71) (17,27) (7,10) (3) (1)    
    */
    void brute(int n){
        long sum=0;
        for(int t,m,N=1;N<=n;N++){        
            int[][][] M=new int[N][N][2];
            for(m=0,t=N-1;t>0&&m==0;t--)
                if(winner(2*t,N-t,1,N,M)==1) m=t;
            System.out.println(N+" "+m);
            sum+=m;
        }
        System.out.println(sum);
    }
    
    int winner(int m,int n,int p,int N,int[][][] M){
        if(m>=n) return p+1;
        if(M[m][n][p]==0){
            for(int t=1;t<=m;t++){
                if(winner(2*t,n-t,1-p,N,M)==p+1){
                    M[m][n][p]=p+1;
                    return p+1;
                }
            }
            M[m][n][p]=2-p;
        }
        return M[m][n][p];
    }
}