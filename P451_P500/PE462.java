package P451_P500;

import java.math.BigInteger;

public class PE462{

    //https://www.youtube.com/watch?v=vgZhrEs4tuk
    public static void main(String[] args){
        new PE462().F((long)1e3);
        new PE462().F((long)1e18);
    }

    void F(long N){
        BigInteger f=BigInteger.ONE;
        int[][] a=new int[64][64];
        for(long c=0,p2=1,e2=0;p2<=N;p2*=2,e2++)
            for(long p3=p2,e3=0;p3<=N;p3*=3,e3++){
                a[(int)e2][(int)e3]=1;
                f=f.multiply(BigInteger.valueOf(++c));
            }
        for(int i=0;i<64;i++)
            for(int j=0;j<64;j++){
                if(a[i][j]==0) continue;
                int hook=1;
                for(int k=i+1;a[k][j]==1;k++) hook++;
                for(int k=j+1;a[i][k]==1;k++) hook++;
                f=f.divide(BigInteger.valueOf(hook));
            }
        String  s=f.toString(),
                t= s.charAt(0)+"."
                 + s.substring(1,11)+"e"
                 +(s.length()-1);
        System.out.format("F(%d)=%s%n",N,t);
    }

}