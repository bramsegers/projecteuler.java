package P201_P250;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import util.Frac;

public class PE236 {

    public static void main(String[] args) {
        new PE236().solve(); 
    }
    
    long A,B;
    TreeMap<Frac,List<Long>[]> map=new TreeMap<>();
    int[][] p={{5248,640},{1312,1888},{2624,3776},{5760,3776},{3936,5664}};        
        
    void solve() {
        
        for (int i=0;i<5;i++) { 
            A+=p[i][0];
            B+=p[i][1];
            search(i,p[i][0],p[i][1]);
        }
        
        int c=0;
        for (Frac f:map.keySet()) {      
            if(valid(map.get(f),f.P,f.Q))  
                System.out.format("%d %d/%d %n",++c,f.P,f.Q);
            if(c==35) break;
        }            
    }
    
    void search(int i, long A, long B) {
        for (long a=A;a>0;a--) {
            for (long b=B;B*a<A*b;b--) {
                Frac f = new Frac(b*A,a*B);
                boolean key=map.containsKey(f);
                if(i==0 && !key) map.put(f,arr());
                if(i==0 ||  key) map.get(f)[i].add((a<<32)|b);
            }
        }        
        Iterator<Frac> it = map.keySet().iterator();
        while(it.hasNext())
            if(map.get(it.next())[i].isEmpty()) it.remove();         
    }
    
    List<Long>[] arr() {
        List[] t=new List[5];
        for (int j=0;j<5;j++)
            t[j]=new ArrayList<>();
        return t;
    }
    
    boolean valid(List<Long>[] k,long p, long q){         
        long a1,b1,a5,b5,p5,a,b,m=(1L<<32)-1,s=k[4].size();
        for(long p1:k[0]){
            for(long p2:k[1]){
                for(long p3:k[2]){
                    for(long p4:k[3]){
                        a1=p1>>32;
                        b1=p1&m;
                        a1+=p2>>32;
                        b1+=p2&m;
                        a1+=p3>>32;
                        b1+=p3&m;
                        a1+=p4>>32;
                        b1+=p4&m;                 
                        p5=k[4].get((int)s-1);
                        a5=p5>>32;
                        b5=p5&m;                            
                        a=q*B*a1-p*A*b1;
                        b=b5*p*A-a5*q*B;        
                        if(a>=b && a%b==0 && a<=b*s) return true;
                    }    
                }    
            }    
        }
        return false;
    }
    
}
