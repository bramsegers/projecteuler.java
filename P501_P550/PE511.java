package P501_P550;

import static util.Util.divisors;

public class PE511{

    public static void main(String[] args){
        new PE511().solve(3,4,1000000000);
        new PE511().solve(4,11,1000000000);
        new PE511().solve(1234567898765L,4321,1000000000);
    }

    int K,M;

    void solve(long n,int k,int m){
        K=k; M=m;
        long[] D=divisors(n);
        long[] a=new long[k];
        for(long d:D) a[(int)(d%k)]++;
        a=matrixPow(a,n);
        int i=(int)((k-(n%k))%k);
        System.out.format("S(%d,%d)=%d%n",n,k,a[i]);
    }

    long[] matrixPow(long[] m,long pow){
        long[] p=new long[K];
        p[0]=1;
        while(pow>0){
            if((pow&1)==1) p=matrixMult(p,m);
            if(pow>1) m=matrixMult(m,m);
            pow>>=1;
        }
        return p;
    }

    long[] matrixMult(long[] m1,long[] m2){
        long[] m=new long[K];
        for(int j=0;j<K;j++){
            long t=0;
            for(int k=0;k<K;k++)
                t=(t+m1[k]*m2[(j-k+K)%K])%M;
            m[j]=t;
        }
        return m;
    }

}