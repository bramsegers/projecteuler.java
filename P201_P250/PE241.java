package P201_P250;

import java.util.TreeMap;
import util.Util;

public class PE241{

    public static void main(String[] args){
        new PE241().solve();
    }

    int maxP=350;
    long maxN=(long)1e18;
    
    int[] primes;
    long[][] pow;
    Frac[][] frac;

    void solve(){

        primes=Util.primes(maxP);
        int pi=primes.length;
        frac=new Frac[pi][100];
        pow =new long[pi][100];
        for(int i=0;i<pi;i++){
            int e=1,p=primes[i];
            for(long pe=1;pe<=maxN/p;e++){
                pe*=p;
                pow[i][e]=pe;
                frac[i][e]=phiDivN(p,e);
            }
        }

        long ans=0;
        for(int q=1;q<=17;q+=2){
            System.out.println("n="+(q-1)/2);
            Frac f=new Frac(2,q);
            ans+=search(1,0,f);
        }

        System.out.println("ans:"+ans);
    }

    long search(long n,int i,Frac f){
         
        long rv=0;
        
        if(f.P.isEmpty()&&f.Q.isEmpty()){
            System.out.println(n);
            rv+=n;
        }
        
        if(i==primes.length) return rv;
        if(!f.P.isEmpty() && f.P.firstKey()<primes[i]) return rv;

        for(int j=i;j<primes.length;j++){
            for(int e=1;pow[j][e]>0;e++){
                long n2=pow[j][e];
                if(n2>maxN/n) break;
                Frac f2=frac[j][e];
                if(f2!=null) rv+=search(n*n2,j+1,mul(f,f2));
            }
        }
        
        return rv;
    }

    Frac phiDivN(int p,int e){                                                  // phi(p^e) = (p^(e+1)-1)/(p-1) = 1+p+p^2+p^3+...+p^e
        long phi=0,q=1;
        for(int i=0;i<=e;i++){
            phi+=q;
            q*=p;
        }
        Frac f=new Frac(phi,1);
        if(f.P==null) return null;
        f.div(p,e);
        return f;
    }
    
    Frac mul(Frac f1,Frac f2){
        Frac f=new Frac(1,1);
        f.P=new TreeMap<>(f1.P);
        f.Q=new TreeMap<>(f1.Q);
        for(int p:f2.P.keySet())
            f.mul(p,f2.P.get(p));
        for(int q:f2.Q.keySet()) 
            f.div(q,f2.Q.get(q));
        return f;   
    }

    class Frac {

        TreeMap<Integer,Integer> P,Q;
        
        Frac(long p,long q){
            P=factorize(p);
            Q=factorize(q);
        } 

        TreeMap<Integer,Integer> factorize(long n){
            TreeMap<Integer,Integer> f=new TreeMap<>();
            for(int pr:primes){
                int e=0;
                while(n%pr==0){
                    n/=pr;
                    e++;
                }
                if(e>0) f.put(pr,e);
                if(n==1) return f;
            }
            return null;
        }
        
        void mul(int p,int e){
            int a=P.getOrDefault(p,0);
            int b=Q.getOrDefault(p,0);
            if(b==0) P.put(p,a+e);
            else if(b>e) Q.put(p,b-e);
            else if(b==e) Q.remove(p);
            else{
                Q.remove(p);
                P.put(p,e-b);
            }
        }

        void div(int p,int e){
            int a=P.getOrDefault(p,0);
            int b=Q.getOrDefault(p,0);
            if(a==0) Q.put(p,b+e);
            else if(a>e) P.put(p,a-e);
            else if(a==e) P.remove(p);
            else{
                P.remove(p);
                Q.put(p,e-a);
            }
        }
    }

}
