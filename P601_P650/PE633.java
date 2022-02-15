package P601_P650;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import util.Util;
import static util.Util.bi;

public class PE633 {
    
    public static void main(String[] args) {
        new PE633().solve((long)8e11); //sqrt of upper limit
        // tot:64076635317378 1.00119742683403125E-10
        // BUILD SUCCESSFUL (total time: 66 minutes 59 seconds)
        new PE633().solve2(7); // lionel.m in thread
        // C(7)=1.0012E-10
        // BUILD SUCCESSFUL (total time: 7 minutes 51 seconds)         
    }
    
    void solve2(int k){
        primes=Util.primes(200000000);
        double d=search2(1,0,0,k);
        d*=6/(Math.PI*Math.PI);
        System.out.format("C(%d)=%.4E%n",k,d);
    }
    
    double search2(double d,int pi,int np,int k){
        if(np==k) return 1/d;
        double rv=0;
        for(int i=pi;i<primes.length;i++){
            long p=primes[i];
            if(d*Math.pow(p*p-1,k-np)>1e24) break;
            rv+=search2(d*(p*p-1),i+1,np+1,k);
        }
        return rv;
    }
    
    int[] primes;
    long N;
    List<Integer> excl=new ArrayList<>(), comb=new ArrayList<>();
    BigInteger tot=bi(0);
    
    void solve(long n) {
        N=n;
        long p6=2*3*5*7*11*13;
        int mp=(int)(n/p6);
        primes=Util.primes(mp);
        BigInteger t=search(new int[0],1,0,0,7);
        t=t.subtract(search(new int[0],1,0,0,8)); 
        BigDecimal d=new BigDecimal(t);
        d=d.divide(new BigDecimal(bi(n).pow(2)));
        System.out.println("tot:"+t+" "+d);    
    }
    
    BigInteger search(int[] f, long p, int pi, int len, int max) {
        if(len==max){
            long snp=N/p;
            excl.clear();
            for(int j=0,i=0;i<primes.length;i++){
                int q=primes[i];
                if(q>=f[len-1]) break;
                if(q>snp) break;
                if(q==f[j]) j++;
                else excl.add(q);
            }
            BigInteger NP=bi(N).pow(2).divide(bi(p).pow(2));
            BigInteger tsum=NP;
            long sign=-1;
            for(int i=1;i<=excl.size();i++){
                comb.clear();
                combine(1,0,i,snp);
                if(comb.isEmpty()) break;
                BigInteger sum=bi(0);
                for(int r:comb) sum=sum.add(NP.divide(bi((long)r*r)));
                if(sign>0) tsum=tsum.add(sum);
                else tsum=tsum.subtract(sum);
                sign=-sign;
            }
            return tsum;
        }
        BigInteger sum=bi(0);                
        for(int i=pi;i<primes.length;i++){
            long p2=primes[i];
            if(Math.pow(p2,max-len)*p>N) break;
            int[] f2=Arrays.copyOf(f,len+1);
            f2[len]=primes[i];
            sum=sum.add(search(f2,p*p2,i+1,len+1,max));
        }
        return sum;
    }

    void combine(int p, int pi, int level, long snp) {
        if(level==0) {comb.add(p);return;}
        for(int i=pi;i<excl.size();i++){
            int p2=excl.get(i);
            if(p*p2>snp) break;
            combine(p*p2,i+1,level-1,snp);
        }
    }

}
