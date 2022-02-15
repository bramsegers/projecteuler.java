package P251_P300;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PE298 {
    
    public static void main(String[] args) {
        new PE298().solve(50);
    }
    
    void solve(int r){
        double d=f(new LinkedList<>(),new LinkedList<>(),r,0);
        System.out.format("E(%d)=%.8f%n",r,d);
    }
    
    int key(List<Integer> L,List<Integer> R,int r,int s) {
        int key=0;
        for(int i=0;i<L.size();i++){
            int j=R.indexOf(L.get(i))+1;
            key=(key<<4)+j;
        }
        key=(key<<6)+r;
        key=(key<<6)+(s+32);
        return key;
    }

    Map<Integer, Double> M = new HashMap<>();
    
    double f(List<Integer> L, List<Integer> R, int r, int s) {
        if(r==0) return Math.abs(s);
        int key=key(L,R,r,s);
        if(M.containsKey(key)) return M.get(key);
        double d=0;
        for(Integer n=1;n<=10;n++) {           
            int s2=s;
            LinkedList<Integer> L2=new LinkedList<>(L);
            if(!L2.contains(n)){
                L2.add(n);
                if(L2.size()>5) L2.pop();               
            }else{
                L2.remove(n);
                L2.add(n);
                s2++;
            }            
            LinkedList<Integer> R2=new LinkedList<>(R);
            if(!R2.contains(n)){
                R2.add(n);
                if(R2.size()>5) R2.pop();
            }else{
                s2--;
            }
            d+=0.1*f(L2,R2,r-1,s2);
        }
        M.put(key,d);
        return d;
    }

}