package P551_P600;

import java.util.List;
import java.util.ArrayList;
import static java.util.Collections.sort;

public class PE583{

    public static void main(String args[]){
        new PE583().solve(10000);
        new PE583().solve(10000000);
    }

    void solve(int N){
        List<int[]> t=new ArrayList<>();
        for(int a,b,c,d,e,f,n,m=1;m*m<=N/2;m++)
            for(n=(m&1)+1;n<m&&(c=m*m+n*n)<=N/2;n+=2)
                if(gcd(m,n)==1){
                    a=m*m-n*n; b=2*m*n;
                    for(d=a,e=b,f=c;f<=N/2;d+=a,e+=b,f+=c){
                        if((d&1)==0) t.add(new int[]{d,e,f});
                        if((e&1)==0) t.add(new int[]{e,d,f});
                    }
                }        
        sort(t,(a,b)->a[0]-b[0]);
        long s=0,n=t.size();
        for(int j,i=0;i<n;i++){
            int[] q,p=t.get(i);
            for(j=i;j<n&&(q=t.get(j))[0]==p[0];j++)
                s+=perim(p[0],p[1],q[1],q[2],N)
                  +perim(q[0],q[1],p[1],p[2],N);
        }
        System.out.format("S(%d)=%d%n",N,s);
    }

    long perim(long a,long b,long c,long d,long N){
        if((d&1)==1) return 0;
        if(a+2*b+d>N) return 0;
        if(d*d-a*a>=4*b*b) return 0;
        long e=b*b+d*d/4+b*c;
        long f=(long)Math.sqrt(e);
        return (f*f!=e)?0:a+2*b+d;
    }
    
    int gcd(int a,int b) {return b==0?a:gcd(b,a%b);}

}
