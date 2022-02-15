package P351_P400;

import java.math.BigDecimal;
import java.util.stream.LongStream;
import util.Util;

public class PE372{

    public static void main(String[] args){
        PE372.solve(100,10000);            // 29750422
        PE372.solve(2000000,1000000000);   // 301450082318807027 (23min 50sec)
    }
    
    static double[] sqr;
    static BigDecimal[] bsqr;

    static void solve(long M,long N){
        int max=(int)((N*N)/(M*M));
        sqr=new double[max+2];
        bsqr=new BigDecimal[max+2];
        for(int i=0;i<max+2;i++){
            sqr[i]=Math.sqrt(i);
            bsqr[i]=Util.sqrt(i,40);
        }        
        System.out.println(
            LongStream
                .range(0,100)
                .parallel()
                .map(n->count(M+1+n,N))
                .sum()
        );
    }
    
    static long count(long _x,long N){
        long f=0;
        for(long x=_x;x<=N;x+=100){
            double dz,d,th=1e-6;
            int maxn=(int)((N*N)/(x*x));
            long y1,y2,z=x,i=3,j=4,k=2;
            for(int n=1;n<=maxn;n++){
                y1=z;
                if(n+1==j){
                    z=k*x;
                    i+=2;
                    j+=i;
                    k++;
                }else{
                    dz=(sqr[n+1]*x);
                    d=dz-(long)dz;
                    z=(d<th||1-d<th)
                        ? bsqr[n+1]
                            .multiply(Util.bd(x))
                            .setScale(0,2)
                            .longValue()
                        :(long)(dz)+1;                    
                }
                y2=z-1>N?N:z-1;
                if(y2>=y1&&(n&1)==1) f+=y2-y1+1;
            }
        }
        return f;
    }

}