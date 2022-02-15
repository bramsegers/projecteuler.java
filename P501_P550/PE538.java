package P501_P550;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class PE538{

    public static void main(String[] args){
        new PE538().solve(150);
        new PE538().solve(3000000);
    }

    long per;
    double area;
    List<Long> list;

    void solve(int N){
        long sum=0,top=10;
        list=new ArrayList<>();
        for(long b,n=1;n<=N;n++){
            b=Long.bitCount(n+1);
            b+=Math.pow(2,Long.bitCount(3*n));
            b+=Math.pow(3,Long.bitCount(2*n));
            list.add(b);
            Collections.sort(list);
            if(list.size()>top) list.remove(0);
            update();
            sum+=per;
        }
        System.out.format("%d%n Σ f(Un) = %d%nn=4%n%n",N,sum);
    }

    void update(){
        int n=list.size();
        for(int i=0;i<=n-4;i++){
            long p=0;
            double a=1;
            for(int j=0;j<4;j++) p+=list.get(i+j);
            for(int j=0;j<4;j++) a*=(p/2.0)-list.get(i+j);
            if(a>=area) {area=a;per=p;}
        }
    }

}
