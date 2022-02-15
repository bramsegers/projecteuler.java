package P201_P250;

import java.util.HashMap;
import java.util.Map;

public class PE208 {

    public static void main(String[] args) {
        new PE208().solve(25);
        new PE208().solve(70);
    }

    void solve(int N) {
        int n=N/5;
        long s=solve(N,n,n,n,n,n,0)*2;
        System.out.format("P(%d)=%d%n",N,s);
    }

    Map<String,Long> mem=new HashMap<>();
    long solve(int sum,int a,int b,int c,int d,int e,int f){
        if(sum==0) return 1;
        String key=a+","+b+","+c+","+d+","+e+","+f;
        if(mem.get(key)==null){
            long s=0;
            int f2=(f+1)%5;
            for(int i=f==0?a:f==1?b:f==2?c:f==3?d:e;i>0;i--){
                int a2=f==0?a-i:a;
                int b2=f==1?b-i:b;
                int c2=f==2?c-i:c;
                int d2=f==3?d-i:d;
                int e2=f==4?e-i:e;
                s+=solve(sum-i,a2,b2,c2,d2,e2,f2);
            }
            mem.put(key,s);
        }
        return mem.get(key);
    }

}
