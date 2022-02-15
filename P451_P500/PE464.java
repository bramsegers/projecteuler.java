package P451_P500;

import java.util.stream.LongStream;
import util.Util;

public class PE464{

    public static void main(String[] args){
        new PE464().solve(20000000);
        // 198775297232878 BUILD SUCCESSFUL (total time: 11 minutes 52 seconds @Intel i7 8-core)
    }

    int[] N,P;

    void solve(int m){
        
        int[] M=Util.sieveMoebius(m);
        N=new int[m+1];
        P=new int[m+1];
        for(int i=1;i<=m;i++){
            N[i]=N[i-1]+((M[i]==-1)?1:0);
            P[i]=P[i-1]+((M[i]== 1)?1:0);
        }

        long ans=LongStream
            .range(1,m+1)
            .parallel()
            .map(a->count((int)a,m))
            .sum();
        System.out.println(ans);
    }

    long count(int a,int m){
        long count=0;
        long n,p,nmax,pmax,nmin,pmin,d;
        for(int b=a;b<=m;b++){
            n=N[b]-N[a-1];
            p=P[b]-P[a-1];
            if((n<p)?(99*p<=100*n):(99*n<=100*p)){
                nmax=100*p/99;
                pmax=100*n/99;
                d=Math.min(pmax-p,m-b);
                d=Math.min(nmax-n,d);
                count+=d+1;
                b+=d;
            }else{
                nmin=99*(p+1)/100;
                pmin=99*(n+1)/100;
                d=Math.max(nmin-n,pmin-p)-1;
                b+=d;
            }
        }
        return count;
    }

}