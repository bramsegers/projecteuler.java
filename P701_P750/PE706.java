package P701_P750;

public class PE706{

    public static void main(String[] args){
        new PE706().solve();
    }

    int N=100000;
    int M=1000000007;

    void solve(){

        int[][][] Q,P=new int[3][3][3];
        P[1][0][0]=1;
        for(int n=0;n<N;n++){
            Q=new int[3][3][3];
            for(int a=0;a<3;a++)
                for(int b=0;b<3;b++)
                    for(int c=0;c<3;c++)
                        for(int d=0;d<10;d++){
                            if(n+d==0) continue;
                            int aa=a,bb=b,cc=c;
                            if(d%3==1) {aa=c;bb=a;cc=b;}
                            if(d%3==2) {aa=b;bb=c;cc=a;}
                            aa=(aa+1)%3;
                            Q[aa][bb][cc]+=P[a][b][c];
                            Q[aa][bb][cc]%=M;
                        }
            P=Q;
        }

        int ans=0;
        for(int a=0;a<3;a++)
            for(int b=0;b<3;b++)
                for(int c=0;c<3;c++)
                    if((a*(a-1)+b*(b-1)+c*(c-1))%3==0)
                        ans=(ans+P[a][b][c])%M;
        System.out.format("P(%d)=%d%n",N,ans);

    }

}
