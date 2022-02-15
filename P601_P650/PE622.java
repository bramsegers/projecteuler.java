package P601_P650;

import java.util.ArrayList;
import java.util.TreeSet;

public class PE622 {

    public static void main(String[] args) {
        new PE622().solve(8);
        new PE622().solve(60);
    }
    
    void solve(int s){
        TreeSet<Long> set = factors(s);
        for(int d=2;d<s;d++)
            if(s%d==0) set.removeAll(factors(d));
      
        long sum=0;
        for(long n:set) sum+=n+1;
        System.out.format("s(%d)=%d%n",s,sum);
    }
    
    TreeSet<Long> factors(int A){
        ArrayList<Long> list=new ArrayList<>();
        TreeSet<Long> set=new TreeSet<>();
        long d=2,N=(1L<<A)-1;
        while(N>1){
            while(N%d==0){
                N/=d;
                list.add(d);
            }
            d++;
        }
        int len=list.size();
        for(int i=0;i<(1L<<len);i++){
            long p=1;
            for(int k=i,j=0;j<len;j++){
                if((k&1)==1) p*=list.get(j);
                k>>=1;
            }
            set.add(p);
        }
        return set;
    }

    int test(int n) {
        int count=0;
        int[] a=new int[n];
        for(int i=0;i<n;i++)
            a[i]=i;
        while(a!=null){
            a=riffle(a);
            count++;
        }
        return count;
    }
    
    int[] riffle(int[] a) {
        int n=a.length;
        int[] rv=new int[n];
        boolean ordered=true;
        for(int i=0;i<n;i++){
            rv[i]=((i&1)==0)?a[i/2]:a[(i+n)/2];
            ordered&=i==rv[i];
        }
        return ordered?null:rv;
    }

}
