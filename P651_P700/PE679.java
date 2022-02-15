package P651_P700;

import java.util.HashMap;
import java.util.Map;

public class PE679 {

    public static void main(String[] args){
        System.out.println(count(0,0,0));
    }
    
    static int N=30;
    static Map<Integer,Long> map=new HashMap<>();
    
    static long count(int n,int w,int s){
        if(n==N) return w==15?1:0;
        int key=(n<<10)|(w<<6)|s;
        if(map.containsKey(key)) return map.get(key);
        long c=0;
        for(int i=0;i<4;i++){
            int w2=w,s2=(s<<2)|i;
            if(s2==113) if(((w>>0)&1)==1) continue; else w2|=1;
            if(s2==156) if(((w>>1)&1)==1) continue; else w2|=2;
            if(s2==176) if(((w>>2)&1)==1) continue; else w2|=4;
            if(s2==194) if(((w>>3)&1)==1) continue; else w2|=8;
            c+=count(n+1,w2,s2&63);
        }
        map.put(key,c);
        return c;
    }

}
