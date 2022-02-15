package P701_P750;

public class PE725 {

    public static void main(String[] args){
        new PE725().solve(   3,(long)1e16);
        new PE725().solve(   7,(long)1e16);
        new PE725().solve(2020,(long)1e16);
    }

    void solve(int N,long M){

        long[][][] cnt=new long[N+1][10][19];
        long[][][] sum=new long[N+1][10][19];
        cnt[0][0][0]=1;

        for(int i=0;i<N;i++)
            for(int m=0;m<=9;m++)
                for(int s=0;s<=18;s++)
                    for(int d=0;d<=9 && s+d<=18;d++){
                        int t=Math.max(m,d);
                        cnt[i+1][t][s+d]=(cnt[i+1][t][s+d]+cnt[i][m][s])%M;
                        sum[i+1][t][s+d]=(sum[i+1][t][s+d]+sum[i][m][s]*10+cnt[i][m][s]*d)%M;
                    }

        long ans=0;
        for (int m=0;m<=9;m++)
            ans=(ans+sum[N][m][2*m])%M;
        System.out.println(ans);
    }
}