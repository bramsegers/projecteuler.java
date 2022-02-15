package P301_P350;

public class PE312 {

    // https://oeis.org/A246959
    public static void main(String[] args){
        new PE312().solve();
    }
    
    int N=10000;
    int M=815730721;
        
    void solve(){
        long m=period(M);
        long c=C(C(C(10000,m),m),M);
        System.out.println(c);
    }

    long period(long mod){
        long c=8,c2,per;        
        for(per=1;;per++){
            c2=(27*c)%mod;
            c2=(c2*c)%mod;
            c2=(c2*c)%mod;
            if(c2==8) return per;
            c=c2;
        }
    }
    
    long C(long n,long mod){
        if(n<3) return 1;
        long c=8,c2,i;
        for(i=4;i<=n;i++){
            c2=(27*c)%mod;
            c2=(c2*c)%mod;
            c2=(c2*c)%mod;
            c=c2;
        }
        return c;
    }

    
    

}