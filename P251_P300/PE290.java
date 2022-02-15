package P251_P300;

import java.util.HashMap;
import java.util.Map;
import static util.Util.*;

public class PE290 {
        
    public static void main(String[] args) {
        new PE290().solve();
    }

    void solve() {
        timer();
        String key;
        Map<String,Long> D1=new HashMap<>(), D2;
        D1.put(key(0,0),1L);
        long n,d,count,dd1,dd2,cr1,cr2;
        for (n=0;n<18;n++) {
            D2=new HashMap<>();
            for (d=0;d<10;d++) {
                for(String k:D1.keySet()){
                    dd1=key(k,0);
                    cr1=key(k,1);
                    count=D1.get(k);                                                           
                    cr2=cr1+137*d;
                    dd2=dd1+d-(cr2%10);
                    key=key(dd2,cr2/10);                   
                    D2.put(key,D2.getOrDefault(key,0L)+count);
                }
            }
            D1=D2;
        }
        long t=0;
        for(String k:D1.keySet()){
            dd1=key(k,0);
            cr1=key(k,1);
            count=D1.get(k);                   
            if(dd1==digsum(cr1)) t+=count;       
        }
        print(t,timer());       
    }
    
     public static String key(long... a) {        
        String k=""+a[0];
        for(int i=1;i<a.length;i++){
            k+=","+a[i];
        }
        return k;
      }
     
    public static long key(String key, int i) {
        return Long.parseLong(key.split(",")[i]);
    }
}
