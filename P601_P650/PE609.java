package P601_P650;

import java.util.BitSet;

public class PE609{

    public static void main(String[] args){
        new PE609().solve(10,1000000007);
        new PE609().solve(100000000,1000000007);
    }

    BitSet sievePrimes(int N){
        BitSet pr=new BitSet();
        pr.set(2,N+1);
        for(int i=2;i*i<=N;i++)
            if(pr.get(i))
                for(int j=i;i*j<=N;j++)
                    pr.clear(i*j);
        return pr;
    }

    void solve(int N,int M){
        BitSet pr=sievePrimes(N);
        int[] pi=new int[N+1];
        int[] map=new int[32];
        for(int sum=0,n=0;n<=N;n++){
            sum+=pr.get(n)?1:0;
            pi[n]=sum;
        }            
        for(int p=2;p>=0;p=pr.nextSetBit(p+1)){
            int c=0,p2=p,m=pr.nextSetBit(p+1);
            m=(m<0)?N-p:m-p-1;
            while((p2=pi[p2])>0){
                c+=pr.get(p2)?0:1;
                map[c]++;
                map[c+1]+=m;
            }
        }
        long P=1;
        for(int i=0;map[i]>0;i++) P=(P*map[i])%M;
        System.out.format("P(%d) mod %d = %d%n",N,M,P);
    }

}
