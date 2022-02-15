package P651_P700;

public class PE655{

    public static void main(String[] args){
        new PE655().solve(5,109);
        new PE655().solve(32,10000019);
    }

    int D,N;
    int[] P;
    int[][] M;

    void solve(int d,int n){
        N=n;
        P=new int[d];
        for(int i=0;i<d;i++)
            P[i]=i==0?1:(10*P[i-1])%N;
        int s,sum=0;
        while(++D<=d){
            int k=(D+1)/2;
            M=new int[k][N];
            for(int i=0;i<k*N;i++)
                M[i/N][i%N]=-1;
            sum+=s=rec(0,0);
            System.out.println(D+" "+s);
        }
        System.out.format("P(%d,%d)=%d%n",d,n,sum);
    }

    int rec(int p,int m){
        if(2*p>=D) return m==0?1:0;
        if(M[p][m]>=0) return M[p][m];
        int r=0, m2=P[p], m3;
        if(2*p<D-1) m2+=P[D-p-1];
        for(int d=(p==0)?1:0;d<10;d++){
            m3=(m2*d)%N;
            m3=(m-m3+N)%N;
            r+=rec(p+1,m3);
        }
        return M[p][m]=r;
    }

}