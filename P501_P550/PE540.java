package P501_P550; 

import util.Util;

public class PE540{

    public static void main(String[] args){
        new PE540().solve(1000000);
        new PE540().solve(3141592653589793L);
    }
    
    void solve(long n){
        long p=0;
        int d,lim=(int)Math.sqrt(n);
        int[] mu=Util.sieveMoebius(lim);
        for(d=1;d<=lim;d++)
            p+=mu[d]*c(n/(1L*d*d));        
        for(d=1;d<=lim;d+=2)
            p-=mu[d]*c_odd(n/(1L*d*d));        
        System.out.format("P(%d)=%d%n",n,p);
    }

    long c(long n){
        long c=0,q,sq;
        for(q=1;(sq=q*q)<=n/2;q++)
            c+=(long)Math.sqrt(n-sq)-q;
        return c;
    }

    long c_odd(long n){
        long c=0,q,sq;
        for(q=1;(sq=q*q)<n/2;q+=2)
            c+=((long)Math.sqrt(n-sq)+1)/2-(q+1)/2;
        return c;
    }
    
}