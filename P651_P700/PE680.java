package P651_P700;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import static util.Util.bi;

// 563917241 (91 minutes 42 secs)
public class PE680 {


    long N=(long)1e18;
    int K=(int)1e6;
    int M=(int)1e9;

    List<Node> list=new ArrayList<>();

    int binsearch(long i,int a,int b){
        if(a==b) return a;
        Node na=list.get(a);
        Node nb=list.get(b);
        if(na.index<=i && i<=na.index+len(na)-1) return a;
        if(nb.index<=i && i<=nb.index+len(nb)-1) return b;
        int m=(a+b)/2;
        Node nm=list.get(m);
        return (nm.index>i)?binsearch(i,a,m):binsearch(i,m,b);
    }

    void swap(long i,long j){
        if(i==j) return;
        if(i>j) {swap(j,i);return;}

        int ia=binsearch(i,0,list.size()-1);
        int ib=binsearch(j,0,list.size()-1);

        Node a=list.get(ia);
        if(a.index<i){
            long len=i-a.index;
            long to=(a.from<a.to)?a.from+len-1:a.from-len+1;
            Node a2=new Node(a.index,a.from,to);
            list.add(ia,a2);
            a.index+=len;
            a.from=a2.to+((a.from>a.to)?-1:1);
            ia++;
            ib++;
        }

        Node b=list.get(ib);
        if(j<b.index+len(b)-1){
            long len=j-b.index;
            long to=(b.from<b.to)?b.from+len:b.from-len;
            Node b2=new Node(b.index,b.from,to);
            list.add(ib,b2);
            b.index+=len+1;
            b.from=b2.to+((b.from>b.to)?-1:1);
        }

        long xa=list.get(ia).index;
        long xb=list.get(ib).index+len(list.get(ib));
        while(ia<ib){
            a=list.get(ia);
            b=list.get(ib);
            list.set(ia,new Node(xa,b.to,b.from));
            xa+=len(b);
            xb-=len(a);
            list.set(ib,new Node(xb,a.to,a.from));
            ia++;
            ib--;
        }
        if(ia==ib){
            a=list.get(ia);
            list.set(ia,new Node(xa,a.to,a.from));
        }

    }

    long len(Node n){
        return Math.abs(n.to-n.from)+1;
    }

    BigInteger sum(Node n){
        if(n.from==n.to) return bi(n.from).multiply(bi(n.index));//return n.from*n.index;
        long a=n.index;
        long b=n.index+len(n)-1;
        long k=-n.index+((n.from<n.to)?n.from:-n.from);
        long c=(n.from<n.to)?(-a+b+1):(a-b-1);
        long d=2*b+3*k;
        // return c*(2*a*a+a*(d-1)+b*(d+1))/6;
        return bi(c).multiply((bi(2*a).multiply(bi(a))).add(bi(a).multiply(bi(d-1))).add(bi(b).multiply(bi(d+1)))).divide(bi(6));
    }

    class Node{
        long index,from,to;
        Node(long i,long f,long t){index=i;from=f;to=t;}
        //public String toString(){return index+"("+from+","+to+")"+len();}
    }

    void solve(){
        list.add(new Node(0,0,N-1));
        long s=1,t=1,u=2;
        for(int k=0;k<K;k++){
            if(k%1000==0) System.out.println(k/1000+"/1000");
            swap(s,t);
            s=u;
            t+=u;
            if(t>=N) t-=N;
            u+=t;
            if(u>=N) u-=N;
        }
        BigInteger SUM=bi(0);
        for(Node n:list)
            SUM=SUM.add(sum(n));
        System.out.println(SUM);
        System.out.println(SUM.mod(bi(M)));
    }


    public static void main(String[] args){
        new PE680().solve();
    }


}
