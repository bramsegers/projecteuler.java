package P351_P400;

import java.util.ArrayList;
import java.util.List;
import util.Util;

public class PE364 {
    
    public static void main(String[] args){
        //new PE().brute();
        new PE364().solve(1000000,100000007);
    }
    
    // https://oeis.org/A192008
    void brute(){
        for(int n=2;;n++){
            long c=count(n,new boolean[n]);
            System.out.println(n+" "+c);
        }
    }  

    long count(int n, boolean[] b) {
        if(n==0) return 1;
        List<Integer>[] cat=new List[]{
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()};
        int c=0;
        for(int i=0;i<b.length;i++){
            if(b[i]) continue;
            c=0;
            if(i==0 || i==b.length-1) c++;
            if(i>0 && !b[i-1]) c++;
            if(i<b.length-1 && !b[i+1]) c++;
            cat[c].add(i);
        }
        long rv=0;
        for(c=2;cat[c].isEmpty();c--){}
        for(int i:cat[c]){
            b[i]=true;
            rv+=count(n-1,b);
            b[i]=false;
        }
        return rv;
    }
    
    // a(n) = SUM (m+k+1)!*binomial(m+k,m)*2^k*(k+v1+v2)!*(m+k)!,
    // where the sum is taken over v1,v2 each from 0 to 1,
    // and over nonnegative m,k such that 2*m+3*k = n-1-v1-v2
    void solve(int n, int M){
        long[] fac=new long[n/2+1];
        long[] pow=new long[n/3+1];
        fac[0]=1;
        pow[0]=1;
        for(int i=1;i<fac.length;i++) fac[i]=(i*fac[i-1])%M;
        for(int i=1;i<pow.length;i++) pow[i]=(2*pow[i-1])%M;
        long s,sum=0;
        for(int v1=0;v1<=1;v1++){
            for(int v2=0;v2<=1;v2++){
                int g=n-1-v1-v2;
                for(int k,m=0;2*m<=g;m++){
                    if((g-2*m)%3!=0) continue;
                    k=(g-2*m)/3;
                    s=fac[m+k+1];
                    s=(s*fac[m+k])%M;
                    s=(s*pow[k])%M;
                    s=(s*fac[k+v1+v2])%M;
                    s=(s*fac[m+k])%M;
                    s=(s*Util.modInv(fac[m]*fac[k],M))%M;
                    sum=(sum+s)%M;
                }
            }
        }
        System.out.println(sum);
    }

}