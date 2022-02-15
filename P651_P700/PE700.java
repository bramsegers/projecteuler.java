package P651_P700;

import java.math.BigInteger;

public class PE700 {
    
    //Seq: a*n mod m
    long a=1504170715041707L;
    long m=4503599627370517L;
        
    void solve(){
        
        long sum=0;
        long minc=m;
        BigInteger A=bi(a);
        BigInteger M=bi(m);
        BigInteger I=bi(modinv(a,m));
        
        String out="%20s%20s%20s%n";
        
        System.out.format(out,"n","coin","sum");
        
        for(int n=1;n<=50000000;n++){
            long c=bi(n).multiply(A).mod(M).longValue();
            if(c<minc){
                minc=c;
                sum+=c;
                System.out.format(out,n,c,sum);
            }
        }
        
        int C=(int)minc;
        long[] arr=new long[C];
        for(int c=0;c<C;c++){
            long n=bi(c).multiply(I).mod(M).longValue();
            arr[c]=n;
        }
        
        while(minc>1){
            long minn=m;
            for(int c=C-1;c>0;c--){
                long n=arr[c];
                if(n<minn){
                    minn=n;
                    minc=c;
                    C=c;
                }
            }
            sum+=minc;
            System.out.format(out,minn,minc,sum);
        }
    }
    
    BigInteger bi(long p){
        return BigInteger.valueOf(p);
    }
    
    long modinv(long k,long m){
        long n=1,a=0,b=1,c=k,d=m,q,r,t;
        while(c>0){
            q=d/c;
            r=d%c;
            t=q*b+a;
            a=b;
            b=t;
            d=c;
            c=r;
            n=1-n;
        }
        return (n>0)?m-a:a;
    }
    
    public static void main(String[] args){
        new PE700().solve();
    }

}