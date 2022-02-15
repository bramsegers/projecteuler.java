package P701_P750;

public class PE738 {

    public static void main(String[] args) {
        new PE738().solve(10,10);
        new PE738().solve(100,100);
        new PE738().solve(10000000000L,10000000000L);
    }

    int mod=1000000007;
    
    void solve(long n,long k){
        long ans=f(n,2,k+1)-1;
        System.out.format("D(%d,%d)=%d%n",n,k,ans);
    }
    
    long f(long n,long mn,long k){
        long a;
        if(n==0||k==0||mn>n) return 0;
        for(a=k%mod;mn*mn<=n;mn++) a=(a+f(n/mn,mn,k-1))%mod;
        return (a+((k-1)%mod)*((n-mn+1)%mod))%mod;
    }
     
}