package P601_P650;

import static util.Util.getPrimes;
import static util.Util.modInv;

public class PE635 {

    public static void main(String[] args) {
        //new PE635().brute();
        new PE635().solve(100000000,1000000009);
    }

    // https://oeis.org/A169888
    // https://oeis.org/A318431
    void brute(){
        for(int n=2;n<=10;n++){
            long a2=brute(0,0,1,2,n);
            long a3=brute(0,0,1,3,n);
            System.out.println(n+" "+a2+" "+a3);
        }
    }

    long brute(int sum,int len,int i,int q,int n){
        if(len==n) return (sum%n==0)?1:0;
        if(i>q*n) return 0;
        return brute(sum,len,i+1,q,n)
          +brute(sum+i,len+1,i+1,q,n);
    }

    void solve(int N,int mod){
        int[] fac=new int[3*N+1];
        fac[0]=1;
        for(int i=1;i<=3*N;i++)
            fac[i]=(int)((1L*i*fac[i-1])%mod);
        long a,b,c,d,e,f,g,sum=0;
        for(int p:getPrimes(N)){
            a=modInv(p,mod);
            b=modInv(fac[p],mod);
            c=modInv(fac[2*p],mod);
            d=fac[2*p];
            e=fac[3*p];
            d=(d*b)%mod;
            d=(d*b)%mod;
            e=(e*b)%mod;
            e=(e*c)%mod;
            f=(5*p-5)%mod;
            g=(d+e+f)%mod;
            sum=(sum+g*a)%mod;
        }
        String out="S2(%d) + S3(%d) mod %d = %d%n";
        System.out.format(out,N,N,mod,sum-5);
    }

}
 