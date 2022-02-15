package P501_P550;

import static java.lang.Math.log10;
import static java.lang.Math.pow;
import static util.Util.modInv;
import static util.Util.modPow;
import static util.Util.modPow;

public class PE506{

    public static void main(String[] args) {
        new PE506().brute(1000,123454321);
        new PE506().solve(100000000000000L,123454321);        
    }
    
    void brute(int N,int M){
        int sum=0;
        int[] a={1,2,3,4,3,2};
        for(int m,k,i=0,n=1;n<=N;n++){
            for(k=0,m=0;m<n;i=++i%6){
                m+=a[i];
                k=((10*k)+a[i])%M;
            } 
            sum=(sum+k)%M;
        }
        System.out.println(sum);
        
    }
    
    void solve(long N,long M){
        int[] a={1,2,3,4,32,123,43,2123,432,1234,32123,43212,34321,23432,123432};
        int[] b={123432,234321,343212,432123,321234,123432,432123,212343,432123,123432,321234,432123,343212,234321,123432};    
        long s,q,m=1000000,sum=0,n=N/15+1;
        for(int i=0;i<15;i++){
            if((N%15)==i) n--;
            q=(int)pow(10,1+(int)log10(a[i]));
            s=((1-m)*(n%M))%M;
            s=(s+modPow(m,n,M)-1)%M;
            s=(s*b[i])%M;
            s=(s*q)%M;
            s=(s*modInv((m-1)*(m-1),M))%M;
            s=(s+(n%M)*a[i])%M;
            sum=(sum+s)%M;
        }
        System.out.println(sum);
    }    
    
}