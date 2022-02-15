package P701_P750;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PE719 {

    public static void main(String[] args) {
        new PE719().solve(12);
    }

    long[] pow10;
    Map<Integer,List<List<Integer>>> parts;

    void solve(int N){
        parts=new HashMap<>();
        pow10=new long[N+2];
        pow10[0]=1;
        for(int n=1;n<=N+1;n++)
            pow10[n]=10*pow10[n-1];
        for(int n=1;n<=N+1;n++){
            parts.put(n,new ArrayList<>());
            long min=(long)Math.sqrt(pow10[n-1]);
            search(n,n,min,0,new ArrayList<>());
        }
        long sum=0;
        int len=1;
        for(long n=1;n*n<=pow10[N];n++){
            long n2=n*n;
            if(n2>=pow10[len]) len++;
            if(n%9>1 || !valid(n,n2,len)) continue;
            System.out.println(n2);
            sum+=n2;
        }
        System.out.println(sum);
    }

    void search(int k,int n,long min,long max,List<Integer> s){
        if(n==0 && max>=min && s.size()>1) parts.get(k).add(s);
        for(int p=1;p<=n;p++){
            List<Integer> s2=new ArrayList<>(s);
            s2.add(p);
            search(k,n-p,min,max+pow10[p]-1,s2);
        }
    }

    boolean valid(long n,long n2,int len){
        for(List<Integer> a:parts.get(len)){
            long t=n2;
            long sum=0;
            for(int b:a){
                sum+=t%pow10[b];
                if(sum>n) break;
                t/=pow10[b];
            }
            if(sum==n) return true;
        }
        return false;
    }
    
    // optimized ////////////////////////////////////
    long T(long N){
        long sum=0;
        for(long m=9;m<=10;m++)
            for(long n=m;n*n<=N;n+=9)
                if(f(n,n*n)) sum+=n*n;
        return sum;
    }
    
    boolean f(long n,long x){
	if(x<=n) return n==x;
	for(long t=10;t<=x && x%t<n;t*=10)
            if(f(n-(x%t),x/t)) return true;
	return false;
    }
    /////////////////////////////////////////////////

}