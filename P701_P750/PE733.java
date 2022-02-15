package P701_P750;

import java.util.Arrays;
import util.BIT;

public class PE733 {

    public static void main(String[] args) {
        new PE733().solve(1000000,1000000007);
    }
    
    void solve(int N,int M){

        int[] a=new int[N];
        int[] b=new int[N];
        int[] p=new int[N];
        int[][] L=new int[N][3];
        int[][] R=new int[N][3];

        for(int i=0;i<N;i++)
            a[i]=b[i]=(153*(i==0?1:a[i-1]))%10000019;

        Arrays.sort(b);
        for(int i=0;i<N;i++)
            p[i]=Arrays.binarySearch(b,a[i]);

        for(int i=0;i<3;i++){
            BIT _L=new BIT(N+1,M);
            BIT _R=new BIT(N+1,M);
            for(int j=0,k=N-1;j<N;j++,k--){
                L[j][i]=_L.sum(1+p[j]);
                R[k][i]=_R.sum(N-p[k]);
                _L.add(1+p[j],i==0?1:L[j][i-1]);
                _R.add(N-p[k],i==0?1:R[k][i-1]);
            }
        }

        long ans=0;
        for (int i=0;i<N;i++)
            ans=(ans+(((L[i][2]+(long)L[i][1]*R[i][0]+(long)L[i][0]*R[i][1]+R[i][2])%M)*a[i])%M)%M;
        System.out.println(ans);
    }

}