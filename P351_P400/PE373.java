package P351_P400;

import java.math.BigInteger;
import java.util.*;
import util.Util;

public class PE373 {
    
    public static void main(String[] args){
        new PE373().solve(10000000);
    }    
    
    int N;
    int[] pr;
    Map<List<Long>,long[]> sig=new HashMap<>();
    Map<Long,Set<Long>> pyt=new HashMap<>();
    Map<Long,Long> len=new HashMap<>();
    
    void solve(int n){
        N=n;
        pr=Util.primes(N);
        signature(1,0,new long[10],0);
        for(List<Long> list:sig.keySet()){
            long[] a=sig.get(list);
            len.put(a[0],a[1]);
            pyt.put(a[0],new TreeSet<>());            
        }
        long sum=0;
        pythTriplets();
        for(long r:pyt.keySet()){
            List<Long> list=new ArrayList<>(pyt.get(r));
            int s=list.size();
            long count=0;
            for(int a=0;a<s;a++){
                for(int b=a;b<s;b++){
                    for(int c=b;c<s;c++){
                        long A=list.get(a);
                        long B=list.get(b);
                        long C=list.get(c);
                        if(radius(r,A,B,C)) count++;
                    }
                }
            }
            sum+=count*len.get(r);          
        }
        System.out.format("S(%d)=%d%n",N,sum);
    } 
    
    void pythTriplets(){
        long a,b,c;
        for(long m=1;m*m<=N;m++){
            for(long n=(m&1)+1;n<m&&(c=m*m+n*n)<=N;n+=2){
                if(Util.gcd(m,n)==1){
                    a=m*m-n*n;
                    b=2*m*n;
                    for(long k:pyt.keySet()){
                        if(k%c>0) continue;
                        pyt.get(k).add(2*a*(k/c));
                        pyt.get(k).add(2*b*(k/c));
                        pyt.get(k).add(2*c*(k/c));
                    }
                }
            }
        }
    }
    
    void signature(long p,int pi,long[] s,int si){
        if(pi==pr.length||p*pr[pi]>N){
            if(s[0]>0) add(p,s);
            return;
        }
        long q=pr[pi],qe=1,e=0,m4=q%4;
        while(qe*p<=N){
            if(m4==1&&e>0){
                s[si]+=e;
                signature(p*qe,pi+1,s,si+1);
                s[si]-=e;
            } else signature(p*qe,pi+1,s,si);
            qe*=q;
            e++;
        }
    }
    
    void add(long p,long[] s){
        List<Long> list=new ArrayList<>();        
        for(int i=0;s[i]>0;i++) list.add(s[i]);
        Collections.sort(list);
        long[] set=sig.get(list);
        if(set==null) sig.put(list,new long[]{p,p});
        else {set[0]=Math.min(set[0],p);set[1]+=p;}
    }
        
    boolean radius(long r,long a,long b,long c){
        BigInteger R=BigInteger.valueOf(r);
        BigInteger A=BigInteger.valueOf(a);
        BigInteger B=BigInteger.valueOf(b);
        BigInteger C=BigInteger.valueOf(c);
        BigInteger ABC=A.multiply(B).multiply(C);
        BigInteger ABC2=ABC.multiply(ABC);
        BigInteger D=A.multiply(B).shiftLeft(1);
        BigInteger E=C.multiply(C).subtract(A.multiply(A)).subtract(B.multiply(B));
        BigInteger F=D.multiply(D).subtract(E.multiply(E));
        if(F.signum()<=0) return false;
        if(ABC2.mod(F).signum()>0) return false;
        return ABC2.divide(F).equals(R.multiply(R));     
    }

}