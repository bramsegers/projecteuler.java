package P701_P750;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.LongStream;
import static util.Util.bi;

public class PE714 {

    public static void main(String[] args) {
        new PE714().solve(50000);
    }


    List<Long> duos=new ArrayList<>();
    List<Long> todo=new ArrayList<>();
    BigInteger SUM;


    void solve(int N){
        search1(0,0,0,15);
        Collections.sort(duos);
        SUM=bi(LongStream
            .range(1,N+1)
            .parallel()
            .map(n->duo(n))
            .sum());
        for(int len=15;todo.size()>0;len++)
            for(int d=1;d<10;d++)
                search2(d,bi(0),len);
        System.out.println(SUM);
    }


    long duo(long n){
        for(long d:duos)
            if(d%n==0) return d;
        todo.add(n);
        return 0;
    }


    void search1(long n,int t,int b,int len){
        if(n>0) duos.add(n);
        for(int d=(n==0)?1:0;d<10;d++){
            int t2=t,b2=b;
            int i=(b>>d)&1;
            if(i==0) {t2++;b2|=(1<<d);}
            if(len>0 && t2<=2) search1(10*n+d,t2,b2,len-1);
        }
    }


    void search2(int d,BigInteger n,int len){
        int sig=n.signum();
        BigInteger n10=n.multiply(bi(10));
        if(len==0 && sig>0){
            for(int i=todo.size()-1;i>=0;i--){
                long q=todo.get(i);
                int m=n10.mod(bi(q)).signum();
                if(m>0) continue;
                SUM=SUM.add(n10);
                todo.remove(i);
            }
        }
        if(len>0 && sig>0) search2(d,n10,len-1);
        if(len>0) search2(d,n10.add(bi(d)),len-1);
    }

}