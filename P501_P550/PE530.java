package P501_P550;

import java.util.HashMap;
import java.util.Map;

public class PE530{

    public static void main(String[] args){
        new PE530().solve();
    }
    
    long N=(long)1e15;
    Map<Long,Long> M=new HashMap<>();

    void solve(){
        M.put(1L,1L);
        long i,rv=0;
        for(i=1;i*i<=N;i++)
            rv+=i*solve(N/(i*i));
        System.out.println(rv);
    }
    
    long solve(long n){
        Long v=M.get(n);
        if(v!=null) return v;
        long i,m,rv=0,c=1;
	while(c<=n){
            i=n/c;
            m=n/i;
            m=(n<m)?n:m;
            rv+=(m-c+1)*i;
            c=m+1;
	}
	for(i=2;i*i<=n;i++)
            rv-=solve(n/(i*i));
	M.put(n,rv);
	return rv;
    }
    
}
