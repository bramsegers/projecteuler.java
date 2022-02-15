package P701_P750;

import java.util.ArrayList;
import java.util.List;
import util.Util;

public class PE712 {

    void solve(long N,long M){

        int S=(int)Math.sqrt(N);
        int[] prime=Util.primes(S);
	long[] s_cnt=new long[S+1];
	long[] l_cnt=new long[S+1];
	s_cnt[0]=-1;
	for(int i=1;i<=S;i++){
            s_cnt[i]=i-1;
            l_cnt[i]=N/i-1;
	}

	for(int p=2;p<=S;p++){
            if(s_cnt[p]==s_cnt[p-1]) continue;
            long p_cnt=s_cnt[p-1];
            long q=(long)p*p;
            int max=(int)Math.min(S,N/q);
            for(int i=1;i<=max;i++){
                long d=(long)i*p;
                if(d<=S) l_cnt[i]-=l_cnt[(int)d]-p_cnt;
                else l_cnt[i]-=s_cnt[(int)(N/d)]-p_cnt;
            }
            for(int i=S;i>=q;i--)
                s_cnt[i]-=s_cnt[i/p]-p_cnt;
	}

        long ans=0;
        List<Long> ls=new ArrayList<>();
	for(long p:prime){
            ls.clear();
            long x=1,y=p;
            while(x<=N){
                ls.add((N/x-N/y)%M);
                x*=p;
                y*=p;
            }
            int sz=ls.size();
            for(int j=0;j<sz-1;j++)
                for(int k=j+1;k<sz;k++){
                    ans+=(((ls.get(j)*ls.get(k))%M )*(k-j))%M;
                    if(ans>M) ans-=M;
                }
        }
	for(int i=2;i<=S;i++){
            long pi=(l_cnt[i-1]-l_cnt[i])%M;
            ans+=(((((N-i+1)%M)*(i-1))%M)*pi)%M;
            if(ans>M) ans-=M;
        }
	ans=(2*ans)%M;
        System.out.format("S(%d) mod %d = %d%n",N,M,ans);
    }


    public static void main(String[] args){
        new PE712().solve((long)1e2,1000000007);
        new PE712().solve((long)1e12,1000000007);
    }

}
