package P501_P550;

import java.util.Map;
import java.util.HashMap;
import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.copyOfRange;

public class PE534{

    public static void main(String[] args){
        new PE534().S(14);
    }
    
    int N,W;
    
    void S(int n){
        long Q,S=0;
        for(N=n,W=0;W<N;W++){
            S+=Q=(W<6)
                ?Q1(N,new int[N])
                :Q2(N,new char[N*N],new HashMap<>());
            System.out.format("Q(%d,%d)=%d%n",N,W,Q);
        }
        System.out.format("S(%d)=%d%n",N,S);
    }
    
    long Q1(int r,int[] a){
        if(r==0) return 1;
        long rv=0;
        for(int i=0,j,k=max(0,W-r+1),m;i<N;i++){
            for(m=1,j=k;j<=N-r-1&&m>0;j++)
                if(i==a[j]||abs(i-a[j])==N-r-j) m=0;
            if(m==1) {a[N-r]=i;rv+=Q1(r-1,a);}
        }
        return rv;
    }
    
    long Q2(int r,char[] a,Map<String,Long> m){
        if(r==0) return 1;
        String k=new String(a);
        Long v=m.get(k);
        if(v!=null) return v; v=0L;
        for(int i=0;i<N;i++){
            if(a[i]==1) continue;
            char[] b=copyOfRange(a,N,r*N);
            int m1=min(N-1-W,r-1),
                m2=min(m1,N-i-1),
                m3=min(m1,i);
            for(int j=0;j<m1;j++) b[i+N*j]=1;
            for(int j=0;j<m2;j++) b[i+1+(N+1)*j]=1;
            for(int j=0;j<m3;j++) b[i-1+(N-1)*j]=1;
            v+=Q2(r-1,b,m);
        }
        m.put(k,v);
        return v;
    }

}
