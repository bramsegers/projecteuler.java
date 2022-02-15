package P251_P300;

import static util.Util.matrixPow;

public class PE294{

    public static void main(String[] args){
        new PE294().brute(9);
        new PE294().brute(42);
        new PE294().solve(3138428376721L,1000000000);
    }
    
    void solve(long n,long mod){
        long[][] a=new long[23*24][23*24];
        for(int i=0;i<23;i++)
            for(int s=0;s<=23;s++)
                for(int d=0;d<10&&s+d<=23;d++)
                    a[i*24+s][((i*10+d)%23)*24+s+d]++;
        a=matrixPow(a,n,mod);
        long s=a[0][23];
        System.out.format("S(%d)=%d%n",n,s);      
    }
    
    
    void brute(int n){
        N=n;
        M=new long[N][24][23];
        long s=dp(0,0,0);
        System.out.format("S(%d)=%d%n",n,s);
    }
    
    int N;
    long[][][] M;
    
    long dp(int i,int s,int m){
        if(i==N) return (m==0&&s==23)?1:0;
        if(M[i][s][m]==0)
            for(int d=0;d<10&&s+d<=23;d++)
                M[i][s][m]+=dp(i+1,s+d,(10*m+d)%23);
        return M[i][s][m];
    }

}
