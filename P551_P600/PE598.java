package P551_P600;

import java.util.HashMap;
import util.Frac;
import util.Util;

public class PE598{

    public static void main(String[] args){
        new PE598().solve(); //543194779059 (15min 31sec)
    }

    int N=100;
    int[] exp;
    HashMap<Frac,Long>[] map;

    void solve(){
        int[] pr=Util.primes(N);
        int pi=pr.length;
        exp=new int[pi];
        map=new HashMap[pi];
        for(int i=0;i<pi;i++){
            map[i]=new HashMap<>();
            exp[i]=f(pr[i],N);
        }
        long a=search(new Frac(1,1),pi-1);
        System.out.println(a/2);
    }

    int f(int p,int n){
        return (n<p)?0:n/p+f(p,n/p);
    }

    long search(Frac f,int i){
        if(i<0) return (f.P==1&&f.Q==1)?1:0;
        long v=map[i].getOrDefault(f,-1L);
        if(v>=0) return v; else v=0;
        for(int j=0;j<=exp[i];j++){
            int a=j+1;
            int b=exp[i]-j+1;
            Frac g=new Frac(f.P*a,f.Q*b);
            v+=search(g,i-1);
        }
        map[i].put(f,v);
        return v;
    }

}
