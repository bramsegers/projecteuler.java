package P701_P750;

import static util.Util.modPowI;

public class PE750{

    public static void main(String[] args){
        new PE750().solve(6);
        new PE750().solve(16);
        new PE750().solve(976);
    }

    void solve(int N){
        int[] pos=new int[N+1];
        int[][] cost=new int[N+1][N+1];
        for(int n=1;n<=N;n++) pos[modPowI(3,n,N+1)]=n;
        for(int n=2;n<=N;n++) for(int a,g,m=1;m<=N-n+1;m++){
            for(g=-1,a=1;a<=n-1;a++){
                int b=cost[m+n-1][m+a];
                int c=cost[m+a-1][m];
                int d=pos[m+n-1];
                int e=pos[m+a-1];
                int f=b+c+Math.abs(d-e);
                if(g<0||f<g) g=f;
            }
            cost[m+n-1][m]=g;
        }
        System.out.println(cost[N][1]);
    }
}