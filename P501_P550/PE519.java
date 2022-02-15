package P501_P550;

public class PE519 {

    public static void main(String[] args){
        new PE519().solve();
    }

    int N=20000;
    int M=1000000000;
    long[][] cache;

    void solve(){
        int d=(int)((Math.sqrt(8*N+1)+1)/2);
        cache=new long[N+1][d+1];
        long a=ways(N,0);
        System.out.format("T(%d)=%d\n",N,a);
    }

    long ways(int n,int d){
        if(n==0) return 1;
        if(cache[n][d]>0) return cache[n][d];
        long s=0;
        for(int e=Math.max(d-1,1);e*(e+1)/2<=n;e++){
            if(d==0 && e==1) s=(s+3*ways(n-e,e))%M;
            if(d==0 && e!=1) s=(s+6*ways(n-e,e))%M;
            if(d==1 && e==1) s=(s+2*ways(n-e,e))%M;
            if(d==1 && e!=1) s=(s+4*ways(n-e,e))%M;
            if(d>1) s=(s+ways(n-e,e))%M;
        }
        return cache[n][d]=s;
    }

}