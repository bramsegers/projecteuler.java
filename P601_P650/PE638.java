package P601_P650;

public class PE638 {
    
    // 1. brute force #occurances of distinct areas for small a.b
    // 2. A063746
    // 3. if a=b, C(a,b,k)= QBinomial[2a,a,k]
    // 4. http://www-groups.mcs.st-andrews.ac.uk/~pjc/Teaching/MT5821/1/l6.pdf
    public static void main(String[] args){
        //new PE638().brute(2,2,2);
        //new PE638().brute(10,10,1);
        //new PE638().C(10000,10000,4);
        new PE638().solve();
    }
    
    long mod=1000000007;
    
    void solve(){
        long sum=0,p=1,c;
        for(int k=1;k<=7;k++){
            p*=10;
            c=C(p+k,p+k,k);
            sum=(sum+c)%mod;
            String t="S(%d,%d,%d) mod %d = %d%n";
            System.out.format(t,p+k,p+k,k,mod,c);
        }
        System.out.println("∑="+sum);
    }
    
    long C(long a,long b,int k){
        long c=1;
        for(long f,n=2*a;n>0;n--){
            f=(k==1)?n:modpow(k,n,mod)-1;            
            f=(n>a)?f:modinv(f,mod);
            c=(c*f)%mod;
        }
        return c;
    }
        
    long modpow(long b,long e,long m){
        b%=m;
        long mp=1;
        while(e>0){
            if((e&1)==1) mp=(mp*b)%m;
            b=(b*b)%m;
            e>>=1;
        }
        return mp;
    }
    
    long modinv(long k,long m){
        long n=1,p1=1,p2=0,k1=k,m1=m,q,r,t;
        while(k1>0){
            q=m1/k1;
            r=m1%k1;
            t=q*p1+p2;
            p2=p1;
            p1=t;
            m1=k1;
            k1=r;
            n=1-n;
        }
        return n==1?m-p2:p2;
    }
    
    void brute(int a,int b,int k){
        long p=rec(0,0,0,a,b,k);
        System.out.format("S(%d,%d,%d)=%d%n",a,b,k,p);
    }
    
    long rec(long a,int x,int y,int i,int j,int k){
        if(x==i&&y==j) return modpow(k,a,mod);
        long rv=0;
        int h0=(x+y==0)?0:y+1;
        for(int h=h0;h<=j;h++){
            int w0=(h==j)?i:x+1;
            for(int w=w0;w<=i;w++){
                rv+=rec(a+(w-x)*h,w,h,i,j,k);
                rv%=mod;
            }
        }
        return rv;
    }
    
}
