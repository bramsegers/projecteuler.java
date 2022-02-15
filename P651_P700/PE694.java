package P651_P700;

import util.Util;

public class PE694 {

    public static void main(String[] args){
        new PE694().solve(16);
        new PE694().solve(100);
        new PE694().solve(10000);
        new PE694().solve((long)1e18);
    }
    
    void solve(long N){
        int c=(int)Math.cbrt(N);
        int[] pr=Util.primes(c);
        long S=search(pr,N,1,0);
        System.out.format("S(%d)=%d%n",N,S);
    }

    long search(int[] pr,long N,long n,int i){
        long rv=N/n;
        for(int j=i;j<pr.length;j++){
            long p=pr[j];
            long n2=p*p*p;
            if(n2>N/n) break;
            while(true){
                rv+=search(pr,N,n*n2,j+1);
                if(n*n2>N/p) break;
                n2*=p;
            }
        }
        return rv;
    }

}
