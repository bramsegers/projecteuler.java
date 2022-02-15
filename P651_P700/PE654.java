package P651_P700;

import java.util.stream.IntStream;

public class PE654{

    public static void main(String[] args){
        new PE654().brute();
        new PE654().solve(10,100,1000000007);
        new PE654().solve(100,10,1000000007);
        new PE654().solve(5000,1000000000000L,1000000007); 
        //T(5000,1000000000000)=815868280 (628 minutes 55 seconds)
    }
    
    int N;
    
    void solve(int n,long m,long mod){
        N=n-1;
        int[] M=new int[N*N];
        for(int i=0;i<n-1;i++)
            for(int j=0;j<n-1-i;j++)
                M[i*N+j]=1;
        M=matrixPow(M,m,mod);
        long t=0;
        for(int i=0;i<N;i++) 
            t=(t+M[i])%mod;
        String out="T(%d,%d)=%d%n";
        System.out.format(out,n,m,t);
    }
    
    int[] matrixPow(int m[],long pow,long mod){
        int[] p=new int[N*N];
        for(int i=0;i<N;i++) p[i*N+i]=1;
        while(pow>0){
            if((pow&1)!=0) p=matrixMult(p,m,mod);
            m=matrixMult(m,m,mod);
            pow>>=1;
        }
        return p;
    }    
    
    int[] matrixMult(int[] m1,int[] m2,long mod){
        int[] m=new int[N*N];
        for(int i=0;i<N;i++){
            int i2=i;
            for(int j=i;j<N;j++){
                int j2=j;
                long t=IntStream
                    .range(0,N)
                    .parallel()
                    .mapToLong(k->
                        (1L*(k<i2?m1[k*N+i2]:m1[i2*N+k])
                        *(j2<k?m2[j2*N+k]:m2[k*N+j2])
                        %mod))
                    .sum();
                m[i*N+j]=(int)(t%mod);
            }
        }
        return m;
    }
    
    
    // brute force
    // n=5: https://oeis.org/A006357
    // n=6: https://oeis.org/A006358
    // n=7: https://oeis.org/A006359
    // etc
    
    int n,m;
    long[][] mem;
    
    void brute(){
        for(n=2;n<10;n++)
            for(m=1;m<10;m++){
                mem=new long[n][m];
                String out="T(%d,%d)=%d%n";
                System.out.format(out,n,m,dp(1,0));
            }
    }
    
    long dp(int i,int p){
        if(p==m) return 1;
        if(mem[i][p]>0) return mem[i][p];
        long r=0;
        for(int j=1;i+j<=n;j++) r+=dp(j,p+1);
        mem[i][p]=r;
        return r;
    }

}