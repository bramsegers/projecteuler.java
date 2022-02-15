package P601_P650;

import static util.Util.*;

public class PE603{

    public static void main(String[] args){
        new PE603().solve(1000000000000L,1000000,1000000007);
    }

    void solve(long N,int K,int M){

        int[] pr=getPrimes_len(K);
        StringBuilder P=new StringBuilder();
        for(int p:pr) P.append(p);

        int L=P.length();
        long[] b=new long[4];

        for(int k=1;k<=4;k++){
            if(k>1) P.append(P,0,L);
            long pow=modPow(10,k*L,M),
                 m10=modInv(10,M),
                 tsum=0,
                 t=0;
            for(int j,i=0;i<k*L;i++){
                j=P.charAt(i)-'0';
                t+=(i+1)*j;
                t%=M;
                pow*=m10;
                pow%=M;
                tsum+=pow*t;
                tsum%=M;
            }
            b[4-k]=tsum;
        }

        long p=modPow(10,L,M);
        long[][] m={
            {    p+3,-3*p-3, 3*p+1,    -p},
            {      1,     0,     0,     0},
            {      0,     1,     0,     0},
            {      0,     0,     1,     0}
        };
        m=matrixPow(m,N-4,M);

        long sum=0;
        for(int i=0;i<4;i++)
            sum=(sum+m[0][i]*b[i])%M;
        System.out.println((sum+M)%M);
    }

}