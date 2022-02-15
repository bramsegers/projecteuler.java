package P751_P800;

import java.util.Arrays;

public class PE757 {

    public static void main(String[] args) {
        new PE757().solve((long)1e6);
        new PE757().solve((long)1e14);
    }

    void solve(long N){
        int i=0;
        long s,t,x,y;
        long[] S=new long[(int)1e8];
        for(x=1;(t=x*(x+1))*t<=N;x++)
            for(y=x;(s=t*y*(y+1))<=N;y++) 
                S[i++]=s;
        s=0;
        t=0;
        Arrays.sort(S);
        for(long n:S){
            s+=n>t?1:0;
            t=n;
        }
        System.out.println(s);
    }
}
