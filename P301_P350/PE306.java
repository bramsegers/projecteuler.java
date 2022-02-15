package P301_P350;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PE306 {

    public static void main(String[] args) {
        //new PE306().brute();
        new PE306().solve(1000000);
    }    
    
    void solve(int N){        
        int[] a={1,5,9,15,21,25,29,35,39,43,55,59,63,73};
        List<Integer> list=new ArrayList<>();
        for(int i:a) list.add(i);
        int s=list.size();
        for(;;){
            list.add(list.get(s-5)+34);
            if(list.get(s)>N) break;
            s++;
        }
        System.out.format("P(%d)=%d%n",N,N-s);
    }
    
    //https://oeis.org/A215721        
    void brute(){
        for(int n=0;;n++){
            List<Integer> list=new ArrayList<>();
            list.add(n);
            boolean b=n>1 && p1CanForceWin(0,list);
            if(!b) System.out.println(n);
        }
    }
    
    Map<String,Boolean> mem=new HashMap<>();
    
    boolean p1CanForceWin(int player, List<Integer> state) {
        if(state.isEmpty()) return player==1;
        String key=player+""+state;
        if(mem.get(key)==null){ 
            boolean rv=true;
            for(List<Integer> s2:play(state)){
                boolean f=p1CanForceWin(1-player,s2);
                if(f&&player==0) {rv=true;break;}
                rv&=f;
            }
            mem.put(key,rv);
        }
        return mem.get(key);
    }      

    List<List<Integer>> play(List<Integer> state) {
        List<List<Integer>> states=new ArrayList<>();
        Set<Integer> set=new HashSet<>(state);
        for(int n:set){
            List<Integer> s2,s1=new ArrayList<>(state);
            s1.remove((Integer)n);
            for(int a=0,b;a+1<n&&(b=n-a-2)>=a;a++){
                s2=new ArrayList<>(s1);
                if(a>1) s2.add(a);
                if(b>1) s2.add(b);
                Collections.sort(s2);
                states.add(s2);
            }
        }
        return states;
    }
       
}