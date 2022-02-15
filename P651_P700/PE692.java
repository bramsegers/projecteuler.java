package P651_P700;

public class PE692 {

    // Problem 366
    // Wythoff array
    // http://mathworld.wolfram.com/WythoffArray.html
    public static void main(String[] args){
        new PE692().brute(13);
        new PE692().solve(7);
        new PE692().solve(80);
    }

    // f = n-th fibonacci number
    void solve(int f){
        long t,c,u=1,v=1,a=1,b=1;
        for(int i=3;i<=f;i++){
            t=u;
            u=v;
            v+=t;
            c=u+a+b;
            a=b;
            b=c;
        }
        System.out.format("F(%d) = G(%d) = %d%n",f,v,b);
    }

    void brute(int N){
        int sum=0;
        for(int n=1;n<=N;n++){
            int[][][] M=new int[n][n][2];
            for(int t=1;t<=n;t++){
                int win=(t==n)?1:winner(2*t,n-t,1,M);
                if(win!=1) continue;
                System.out.println(n+" "+t);
                sum+=t;
                break;
            }
        }
        System.out.println(sum);
    }

    int winner(int m,int n,int p,int[][][] M){
        if(m>=n) return p+1;
        if(M[m][n][p]==0){
            for(int t=1;t<=m;t++)
                if(winner(2*t,n-t,1-p,M)==p+1)
                    return M[m][n][p]=p+1;
            M[m][n][p]=2-p;
        }
        return M[m][n][p];
    }

}
