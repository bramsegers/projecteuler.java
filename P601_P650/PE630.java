package P601_P650;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import util.Frac;

public class PE630 {

    public static void main(String[] args) {
        new PE630().solve(2500);
    }

    void solve(int k) {
        
        int[][] xy=new int[k][2];
        long t,s=290797;
        for(int i=0;i<2*k;i++){
            s=(s*s)%50515093;
            t=(s%2000)-1000;
            xy[i>>1][i&1]=(int)t;
        }
        
        Set<Line> set = new HashSet<>();
        for(int i=0;i<k;i++){
            for(int j=i+1;j<k;j++){
                int x1=xy[i][0];
                int y1=xy[i][1];
                int x2=xy[j][0];
                int y2=xy[j][1];
                if(x1==x2 && y1==y2) continue;
                set.add(new Line(x1,y1,x2,y2));
            }
        }
        
        long total=set.size();
        long count=0;
                
        Map<Frac,Long> map=new HashMap<>();
        for(Line line:set){
            Long i=map.get(line.a);
            if(i==null) i=0L;
            map.put(line.a,i+1);
        }
        
        for(Frac a:map.keySet()){
            long m=map.get(a);
            count+=m*(total-m);
        }
        
        System.out.println("M="+total);
        System.out.println("S="+count);
    }

    class Line {
        
        Frac a,b;

        public Line(int x1, int y1, int x2, int y2) {
            if(x1==x2) 
                b=new Frac(x1,1);
            else{
                a=new Frac(y2-y1,x2-x1);
                b=new Frac(y2,1).sub(a.mul(new Frac(x2,1)));
            }
        }

        @Override
        public int hashCode() {
            int hash=7;
            hash=19*hash+Objects.hashCode(a);
            hash=19*hash+Objects.hashCode(b);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if(this==obj) return true;
            if(obj==null) return false;
            if(getClass()!=obj.getClass()) return false;
            Line other=(Line)obj;
            if(!Objects.equals(a,other.a)) return false;
            if(!Objects.equals(b,other.b)) return false;
            return true;
        }      
    }
}
