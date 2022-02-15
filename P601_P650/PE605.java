package P601_P650;

public class PE605{

    public static void main(String[] args){
        new PE605().brute();
        new PE605().solve(100000007,10007,100000000);
    }
    
    void solve(long n,long k,long m){
        long p,q,M;
        p=powmod(2,n,m)-1;
        p=(((p*(k-1))%m)+n)%m;
        p=(p*(powmod(2,n-k,m)))%m;
        q=powmod(2,n,m)-1;
        q=(q*q)%m;
        M=(p*q)%m;
        System.out.format("P(%d,%d)=%d%n",n,k,M);
    }
    
    long powmod(long b,long e,long m){
        for(long p=1;;e>>=1){
            if(e==0) return p;
            if((e&1)==1) p=(p*b)%m;
            b=(b*b)%m;
        }
    }
    
    
    
    
    
    /*    
        1. Brute force:      P(n,k) for small n,k
    
                                        ∞   nx+k-1
        2. Observe:          P(n,k) =   ∑  ─────
                                       x=0  2^(nx+k)   
    
                                        2^(n-k)*((k-1)*(2^n-1)+n)
        3. Wolfram Alpha:    P(n,k) =   ───────────────
                                              (2^n-1)^2
       
        4. Assume:           P(n,k) is in reduced form
    */    
    
    int n=6,k=2;
    
    void brute(){
        int[] m=new int[60];
        brute(0,1,0,m);
        String s="";
        for(int i=0;i<m.length;i++) 
            if(m[i]>0) s+=+m[i]+"/2^"+i+" + ";
        System.out.format("P(%d,%d)=%s...%n",n,k,s);
    }    
    
    void brute(int r,int w1,int w2,int[] m){
        if(r==m.length) return;
        if(w1==w2 && w1==k) m[r]++;
        if(w1==w2) return;        
        r++;
        int p1=((r-1)%n)+1;
        int p2=(r%n)+1;
        brute(r,w2,p1,m);
        brute(r,w2,p2,m);
    }
    
}