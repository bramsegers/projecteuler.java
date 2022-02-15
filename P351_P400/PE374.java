package P351_P400;

import static util.Util.modInv;
import static util.Util.modMul;

public class PE374{

    public static void main(String[] args){
        new PE374().brute(100);
        new PE374().solve(100,982451653);
        new PE374().solve((long)1e14,982451653);
    }
    
    void solve(long N,long M){
        long s=1,a=1,f=1,n=1,p=2,q;
        long m=modInv(2,M);
        while(n<=N){
            s+=modMul(f,p-2,p+1,m,M);
            s+=modMul(a,p-2,M);
            a =modMul(a,p+1,M);
            f =modMul(f,p,M);
            s%=M;         
            a+=f;
            p+=1;
            n+=p;
        }
        q =p;
        n-=p-1;
        f =modMul(f,p,M);
        while(n<=N){
            m =modInv(q,M);
            s+=modMul(p-2,f,m,M);
            s%=M;
            q-=1;
            n+=1;
        }
        String out="S(%d) mod %d = %d%n";
        System.out.format(out,N,M,s);
    }
 
    
    String ms;
    long mp,mlen;

    void brute(int N){
        long sum=0;
        for(int n=1;n<=N;n++){
            mp=0;
            rec(n,1,1,0,"");
            sum+=mlen*mp;
            String out="%d (%s) f=%d m=%d ∑=%d%n";
            System.out.format(out,n,ms,mp,mlen,sum);
        }
    }
    
    void rec(int n,int i,long p,int len,String s){
        if(n==0&&p>mp) 
            {mp=p;mlen=len;ms=s.trim();}
        for(int j=i;j<=n;j++) 
            rec(n-j,j+1,p*j,len+1,s+j+" ");
    }

}