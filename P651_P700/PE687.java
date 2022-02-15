package P651_P700;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PE687 {
    
    public static void main(String[] args){
        new PE687().solve();
    }
    
    BigInteger zero=BigInteger.ZERO;
    BigInteger one=BigInteger.ONE;
    Map<List<Integer>,BigInteger> map;
    
    void solve(){
        BigInteger P=zero;
        BigInteger Q=zero;  
        for(int t=0;t<=13;t++){
            map=new HashMap<>();
            List<Integer> rd=new ArrayList<>();
            List<Integer> ra=new ArrayList<>();
            for(int i=0;i<13;i++) ra.add(i==0?3:4);
            BigInteger s=solve(t,0,13,rd,ra);
            System.out.println(t+" "+s);
            if(t==2||t==3||t==5||t==7||t==11||t==13) P=P.add(s);
            Q=Q.add(s);
        }
        BigInteger g=P.gcd(Q);
        P=P.divide(g);
        Q=Q.divide(g);
        double q=P.doubleValue()/Q.doubleValue();
        System.out.println(P+"/"+Q);
        System.out.format("%.10f%n",q);
    }
    
    
    BigInteger solve(int target,int last,int pr,List<Integer> rd,List<Integer> ra){        
        if(pr<target || pr-ra.size()>target) return zero;
        if(rd.size()+ra.size()==0) return pr==target?one:zero;
        List<Integer> key=new ArrayList<>();
        key.add(last);
        key.add(pr);
        key.add(rd.size());
        key.addAll(rd);
        key.addAll(ra);
        BigInteger rv=map.get(key);
        if(rv!=null) return rv;
        rv=zero;
        for(int i=0;i<rd.size();i++){
            int val=rd.get(i)-1;
            List<Integer> rd2=new ArrayList<>(rd);
            if(val==0) rd2.remove(i);
            else {rd2.set(i,val);Collections.sort(rd2);}
            rv=rv.add(solve(target,-1,pr,rd2,ra));
        }
        for(int i=0;i<ra.size();i++){
            int val=ra.get(i)-1;
            List<Integer> ra2=new ArrayList<>(ra);
            if(val==0){
                ra2.remove(i);
                int pr2=(last==i)?pr-1:pr;
                rv=rv.add(solve(target,-1,pr2,rd,ra2));
            }
            else if(last==i){
                ra2.remove(i);
                List<Integer> rd2=new ArrayList<>(rd);
                rd2.add(val);
                Collections.sort(rd2);
                rv=rv.add(solve(target,-1,pr-1,rd2,ra2));
            }else{
                ra2.set(i,val);
                Collections.sort(ra2);
                int last2=ra2.indexOf(val);
                rv=rv.add(solve(target,last2,pr,rd,ra2));
            }
        }
        map.put(key,rv);
        return rv;
    }

}
