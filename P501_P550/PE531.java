package P501_P550;

import util.CRT;
import util.Primes;

public class PE531{

    static int N=1000000, M=1005000;
        
    public static void main(String[] args){
        long a,b,sum=0;
        Primes pr=new Primes(M);
        long[] phi=new long[M-N];
        for(int n=N;n<M;n++)
            phi[n-N]=pr.totient(n);
        for(int n=N;n<M;n++){
            for(int m=n+1;m<M;m++){
                a=phi[n-N];
                b=phi[m-N];
                sum+=CRT.solve(a,b,n,m);
            }
        }
        System.out.println(sum);
    }
    
}
