package P351_P400;

import static util.Util.matrixPow;

public class PE356{

    public static void main(String[] args){
        new PE356().solve(30,987654321,100000000);
    }

    void solve(int n,int pow,int mod){
        long a,b,c,sum=0;
        for(long i=1,j=1;i<=n;i++){
            j<<=1;
            long[][] m={
                { 0, 1, 0 },
                { 0, 0, 1 },
                {-i, 0, j }
            };
            m=matrixPow(m,pow,mod);
            a=(m[0][0]*3)%mod;
            b=(m[0][1]*(j%mod))%mod;
            c=(m[0][2]*((j*j)%mod))%mod;
            sum=(sum+a+b+c-1)%mod;
        }
        System.out.format("S(%d)=%d%n",n,sum);
    }

}
