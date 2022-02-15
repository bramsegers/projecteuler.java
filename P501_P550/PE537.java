package P501_P550;

import util.Primes;

public class PE537{
    
    public static void main(String[] args){
        new PE537().solve();
    }

    int N=20000;
    int M=1004535809;
        
    void solve(){
        long[] pi=new long[N+1];
        Primes pr=new Primes(1000000);
        for(int p=0,n=1;;n++){
            if(pr.contains(n)) p++;
            if(p>N) break;
            pi[p]++;
        }
        long ans=pow(pi,N)[N];
        System.out.println(ans);
    }
    
    long[] pow(long[] m,long n){
        long[] p=new long[N+1];
        p[0]=1;
        while(n>0){
            if((n&1)==1) p=mult(p,m);
            m=mult(m,m);
            n>>=1;
        }
        return p;
    }

    long[] mult(long[] m1,long[] m2){
        long[] m=new long[N+1];
        for(int j=0;j<=N;j++)
            for(int i=0;j+i<=N;i++){
                m[i+j]+=m1[j]*m2[i];
                m[i+j]%=M;
            }
        return m;
    }
    
}
