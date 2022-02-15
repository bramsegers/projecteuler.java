package P651_P700;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import util.Util;

public class PE678 {

    // 1986065
    // total time: 17 minutes 51 seconds
    public static void main(String[] args){
        new PE678().solve();
    }    

    long N=(long)1e18;
   
    public void solve(){        
        int t=0;
        for(long n=N;n>0;n/=2) t++;
        HashMap<Integer,List<Long>> pow=new HashMap<>();
        pow.put(3,new ArrayList<>());
        for(long j,i=1;(j=i*i*i)<=N;i++){
            pow.get(3).add(j);
            for(int a=4;a<t && j<=N/i;a++){
                if(i==1) pow.put(a,new ArrayList<>());
                pow.get(a).add(j*=i);
            }
        }

        HashMap<Long,Integer> pow3=new HashMap<>();
        HashMap<Long,Integer> pow4=new HashMap<>();
        for(int i=3;i<t;i++){
            for(long j:pow.get(i)){
                pow3.put(j,pow3.getOrDefault(j,0)+1);
                if(i>3) pow4.put(j,pow4.getOrDefault(j,0)+1);
            }
        }
        
        long count=0;
       
        // e=2
        int[] primes=Util.primes((int)Math.cbrt(N));
        for(long k:pow3.keySet())
            count+=repr2(k,primes)*pow3.get(k);
        
        // e=3
        HashSet<Long> set=new HashSet<>(pow.get(3));
        for(long n:pow4.keySet())
            count+=repr3(n,pow.get(3),set)*pow4.get(n);
        
        // e>3
        for(int e=4;e<t;e++){
            for(int ia=0;ia<pow.get(e).size();ia++){
                long a=pow.get(e).get(ia);
                if(2*a>N) break;
                for(int ib=ia+1;ib<pow.get(e).size();ib++){
                    long ab=a+pow.get(e).get(ib);
                    if(ab>N) break;
                    count+=pow3.getOrDefault(ab,0);
                }
            }
        }
       
        System.out.println(count);
    }
   
    int repr2(long n,int[] primes){
        int B=1,f2=0,f,sq;
        while(n%2==0) {f2++;n/=2;}
        sq=(f2%2==1)?1:0;
        for(long p:primes){
            if(n%p>0) continue;
            for(f=0;n%p==0;n/=p) f++;
            if(p%4==3 && f%2>0) return 0;
            if(p%4==1) B*=f+1;
            if(f%2>0) sq=0;
            if(n==1) break;
        }
        if(B%2>0) B-=(f2%2>0)?-1:1;
        return B/2-sq;
    }
    
    int repr3(long n, List<Long> cubes, HashSet<Long> cubeset){
        int count=0;
        for(long a:cubes){
            if(2*a>=n) break;
            if(cubeset.contains(n-a)) count++;
        }
        return count;
    }

}