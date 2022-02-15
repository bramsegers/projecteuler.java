package P401_P450;

import java.math.BigInteger;

public class PE435{

    public static void main(String[] args){
        new PE435().solve((long)1e15,100,1307674368000L);
    }

    void solve(long n,long X,long m){
        BigInteger k0,k1,a,ma,f0,f1,b,sum=bi(0);
        for(long x=1;x<=X;x++){
            a=bi(x*x+x-1);
            ma=a.multiply(bi(m));
            f0=fibmod(bi(n),ma)[1];
            f1=fibmod(bi(n+1),ma)[1];
            k0=bi(x).modPow(bi(n+2),ma);
            k1=bi(x).modPow(bi(n+1),ma);
            b=f0.multiply(k0);
            b=b.add(f1.multiply(k1));
            b=b.subtract(bi(x));
            b=b.mod(ma);
            sum=sum.add(b.divide(a));
        }
        System.out.println(sum.mod(bi(m)));
    }

    BigInteger[] fibmod(BigInteger n,BigInteger m){
        if(n.signum()==0) return new BigInteger[]{bi(1),bi(0)};
        BigInteger[] ab=fibmod(n.shiftRight(1),m);
        BigInteger a=ab[0],b=ab[1],c,d;
        BigInteger a2=a.multiply(a);
        BigInteger b2=b.multiply(b);
        BigInteger ab2=(a.multiply(b)).shiftLeft(1);
        BigInteger apb=a.add(b);
        if(n.testBit(0)) {c=ab2.add(b2); d=(b2.add(apb.pow(2)));}
        else             {c=a2.add(b2);  d=ab2.add(b2);}             
        return new BigInteger[]{c.mod(m),d.mod(m)};
    }
    
    BigInteger bi(long p) {return BigInteger.valueOf(p);}

 }