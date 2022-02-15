package P701_P750;

import util.Util;

public class PE705{

    public static void main(String[] args){
        new PE705().solve();
    }

    int N=100000000;
    int M=1000000007;

    void solve(){

        long ans=0,m=1;
        int[] pr=Util.primes(N);
        long[] freq=new long[10];
        int[][] div={{},{1},{1,2},{1,3},{1,2,4},{1,5},{1,2,3,6},{1,7},{1,2,4,8},{1,3,9}};

        for(int i=pr.length-1;i>=0;i--)
            while(pr[i]>0){
                int n=pr[i]%10;
                pr[i]/=10;
                if(n==0) continue;
                int s=div[n].length;
                long a=ans;
                ans=0;
                for(int d:div[n]){
                    ans+=a;
                    if(ans>=M) ans-=M;
                    for(int j=1;j<d;j++){
                        ans+=freq[j];
                        if(ans>=M) ans-=M;
                    }
                }
                for(int j=0;j<10;j++)
                    freq[j]=(freq[j]*s)%M;
                for(int d:div[n]){
                    freq[d]+=m;
                    if(freq[d]>=M) freq[d]-=M;
                }
                m=(m*s)%M;
            }

        System.out.println(ans);
    }

}
