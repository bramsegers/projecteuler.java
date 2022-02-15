package P601_P650;

import java.util.TreeSet;
import util.Util;

public class PE634 {

    public static void main(String[] args){
        new PE634().solve((long)3e6);
        new PE634().solve((long)9e18);
    }
    
    int[] pr,list;
    
    void solve(long n){
        pr=Util.primes((int)Math.sqrt(n/8));
        list=new int[16];
        list[0]=2;
        System.out.format("F(%d)=%d%n",n,search(n,4,0,2));
    }
    
    long search(long N, long p, int pi, int e){
        long rv=valid()?count(N,1,0,0,e):0;
        if(p>N/pr[pi]) return rv;
        list[pi]++;
        rv+=search(N,p*pr[pi],pi,e+1);
        list[pi]--;
        if(p>N/(pr[pi+1]*pr[pi+1])) return rv;
        list[pi+1]=2;
        rv+=search(N,p*pr[pi+1]*pr[pi+1],pi+1,e+2);
        list[pi+1]=0;
        return rv;
    }

    boolean valid(){
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=0;list[i]>0;i++) set.add(list[i]);
        String s=set.toString();
        if(s.equals("[2]")) return false;
        if(s.equals("[3]")) return false;
        if(s.equals("[4]")) return false;
        if(s.equals("[2, 4]")) return false;
        if(s.equals("[6]") && list[1]==0) return false;
        return true;
    }
    
    long count(long N, long p, int pi, int s, int e){
        if(list[s]==0) return 1;
        long rv=0;
        for(int i=pi;i<pr.length;i++){
            if(p*Math.pow(pr[i],e)>N) break;
            double p3=p*Math.pow(pr[i],list[s]);
            rv+=count(N,(long)p3,i+1,s+1,e-list[s]);
        }
        return rv;
    }
   
}
