package P751_P800;

import java.util.stream.LongStream;

public class PE752{

    public static void main(String[] args){
        new PE752().solve(100);
        new PE752().solve(1000);
        new PE752().solve(1000000);
    }

    void solve(int N){
        long ans=LongStream
            .range(5,N+1)
            .parallel()
            .map(n->g(n))
            .sum();
        System.out.format("G(%d)=%d%n",N,ans);
    }

    long g(long n){
        if(n%6!=1 && n%6!=5) return 0;
        long a=1,b=0,p=0,q,r,s;
        for(;p==0||b>0;p++){q=a;a=(a+7*b)%n;b=(q+b)%n;}
        for(r=a,s=p;a!=1;p+=s) a=r*a%n;
        return p;
    }
}