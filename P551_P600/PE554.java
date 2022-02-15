package P551_P600;

import static util.Util.modChoose;

public class PE554{

    //C(n)=8*(choose(2n,n)-(3n^2+2n+7)
    public static void main(String[] args){
        new PE554().brute(1);
        new PE554().brute(2);
        new PE554().brute(10);
        new PE554().solve();
    }

    long[] f;
    int mod=100000007;

    void solve(){
        f=new long[mod];
        f[0]=1;
        for(int i=1;i<mod;i++)
            f[i]=(f[i-1]*i)%mod;
        long sum=0,a=0,b=0,c=1;
        for(int n=2;n<=90;n++){
            a=b;
            b=c;
            c=a+b;
            sum+=C(c);
            sum%=mod;
        }
        System.out.println("∑="+sum);
    }

    long C(long n){
        long q,r;
        r=modChoose(2*n,n,mod,f);
        r=(8*r)%mod;
        n%=mod;
        q=3*n*n+2*n+7;
        q%=mod;
        r+=mod-q;
        r%=mod;
        return r;
    }





    void brute(int n){
        N=n;
        b=new boolean[2*N][2*N];
        long s=dp(0);
        System.out.format("C(%d)=%d%n",N,s);
    }

    int N;
    boolean[][] b;
    int[] x={-1, 1,-2,-1, 0, 1,-1,-2,-1},
          y={-2,-2,-1,-1,-1,-1, 0, 1, 1};

    long dp(int n){
        if(n==N*N) return 1;
        int r=(n/N)*2;
        int c=(n%N)*2;
        long rv=0;
        for(int j=0;j<4;j++){
            int r2=r+(j/2);
            int c2=c+(j%2);
            if(valid(r2,c2)){
                b[r2][c2]=true;
                rv+=dp(n+1);
                b[r2][c2]=false;
            }
        }
        return rv;
    }

    boolean valid(int r,int c){
        for(int i=0;i<9;i++){
            int r2=r+y[i];
            int c2=c+x[i];
            if(r2<0||r2>=2*N) continue;
            if(c2<0||c2>=2*N) continue;
            if(b[r2][c2]) return false;
        }
        return true;
    }

}