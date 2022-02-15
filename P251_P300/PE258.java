package P251_P300;

public class PE258{

    // thread: fergemann
    public static void main(String[] args){
        new PE258().solve();
    }

    int  N=2000;
    int  M=20092010;
    long K=1000000000000000000L;

    void solve(){
        int[] m=new int[N];
        m[0]=1;
        m=pow(m,K);
        long sum=0;
        for(long n:m) sum+=n;
        System.out.println(sum%M);
    }

    int[] pow(int[] m,long pow){
        int[] p=new int[N];
        p[N-1]=1;
        while(pow>0){
            if((pow&1)==1) p=mul(p,m);
            if(pow>1) m=mul(m,m);
            pow>>=1;
        }
        return p;
    }

    int[] mul(int[] m1,int[] m2){
        int[] m=new int[N];
        for(int r=0;r<N;r++)
            for(int c=0;c<N;c++){
                m[r]+=(get(m1,r,c)*m2[c])%M;
                if(m[r]>M) m[r]-=M;
            }
        return m;
    }

    long get(int[] m,int r,int c){
        if(c==N-1) return m[r];
        if(r>c) return m[r-c-1];
        return m[N+r-c-1]+m[N+r-c-2];
    }

}
