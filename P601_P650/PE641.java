package P601_P650;

import util.Util;

public class PE641{

    public static void main(String[] args){
        new PE641().solve();
    }
    
    long M=(long)1e18;
    int[] P=Util.primes((int)5e8);
    
    void solve(){
        long c=solveA(new int[64],1,1,0,0);
        System.out.println(c);
    }
    
    long solveA(int[] ex,long p,int d,int i,int t){
        long q=P[i]*P[i],rv=0;
        if(d%6==1) rv+=solveB(ex,1,t,0,0);
        for(int e=2;p<=M/q;e++){
            ex[i]=e;
            rv+=solveA(ex,p*q,d*(2*e+1),i+1,t+e);
            q*=P[i];
        }
        return rv;
    }

    long solveB(int[] ex,long p,int t,int i,int e){
        if(t==0) return 1;
        long q,rv=0;
        for(int j=i;j<P.length;j++){
            if(p*Math.pow(P[j],t)>M) break;
            q=(long)Math.pow(P[j],ex[e]);
            rv+=solveB(ex,p*q,t-ex[e],j+1,e+1);
        }
        return rv;
    }

}
