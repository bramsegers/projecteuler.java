package P601_P650;

import util.Util;

public class PE618{

    // https://oeis.org/A002098
    public static void main(String[] args){
        new PE618().solve();
    }

    int M=1000000000;
    int[] P=Util.primes(50000);
    int[][] R=new int[50000][5000];

    void solve(){
        int f,f1=1,f2=1,k=2,i=0,sum=0;
        while(++k<=24){
            f=f1+f2;
            f1=f2;
            f2=f;
            while(P[i]<=f) i++;
            sum=(sum+B(f,i))%M;
        }
        System.out.println(sum);
    }

    int B(int n,int i){
        if(n<0) return 0;
        if(n==0) return 1;
        if(i==0) return 0;
        if(R[n][i]==0)
            R[n][i]=(int)((B(n,i-1)+1L*B(n-P[i-1],i)*P[i-1])%M);
        return R[n][i];
    }

}
