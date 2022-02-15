package P401_P450;

import java.util.HashMap;
import java.util.Map;

public class PE413{

    public static void main(String[] args) {
        //new PE413().brute();
        new PE413().solve();
    }
    
    int d;
    Map<Long,Long> map;
    
    void solve(){
        long s,z,count=0;
        for(d=1;d<=19;d++){
            map=new HashMap<>();
            for(int i=1;i<10;i++){
                s=1<<(2*(i%d));
                z=(i%d==0)?1:0;
                count+=solve(s,z,d-1);
            }
        }
        System.out.println(count);
    }
    
    long solve(long s,long z,int t){
        if(t==0) return z;
        long k=(((s<<1)+z)<<5)+t;
        Long v=map.get(k);
        if(v!=null) return v;
        long s2,z2,p,q,r,rv=0;
        for(int i=0;i<10;i++){
            p=2*(i%d);
            z2=(p==0)?z+1:z;
            s2=1L<<p;
            for(int j=0;j<d&&z2<2;j++){
                q=(s>>(2*j))&3;
                if(q==0) continue;
                p=2*((10*j+i)%d);
                z2+=(p==0)?q:0;
                r=((s2>>p)&3)+q;
                r=(r>2)?2:r;
                s2&=~(3L<<p);
                s2|=r<<p;
            }
            if(z2<2) rv+=solve(s2,z2,t-1);
        }
        map.put(k,rv);
        return rv;
    }
    
    void brute(){
        long count=0;
        for(int i=1;i<10000000;i++)
            count+=oneChild(i)?1:0;
        System.out.println(count);
    }
    
    boolean oneChild(int n) {
        String s=""+n;
        int c=0,tlen=s.length();
        for(int len=1;len<=tlen;len++){
            for(int i=0;i<=tlen-len;i++){
                String t=s.substring(i,i+len);
                int v=Integer.parseInt(t);
                if(v%tlen==0) c++;
                if(c>1) return false;
            }
        }
        return c==1;
    }
    
}