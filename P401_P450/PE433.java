package P401_P450;

public class PE433{

    public static void main(String[] args){
        new PE433().solve(100);
        new PE433().solve(5000000);
    }

    int N,K;
    int[][] S;

    void solve(int n){
        N=n;
        K=(int)Math.pow(n,2d/3);
        S=new int[K][K];
        for(int y=1;y<K;y++){
            for (int s=0,x=1;x<=y;x++){
                int a=x,b=y,t;
                while(b>0){
                    s++;
                    t=a;
                    a=b;
                    b=t%b;
                }
                S[y][x]=s;
            }
        }
        long s=2*solve(1,0,0)+1L*n*(n+1)/2;
        System.out.format("S(%d)=%d%n",n,s);
    }

    long solve(int a,int b,int c){
        int p=(N-a)/(b+=a);
        if(p>=K) return solve(a,b,c)+solve(b,a,c+1)+N/(a+b)*(c+1);
        long rv=0;
        for(int y=1;y<=p;y++){
            int q=(N-y*b)/a;
            rv+=q*c+S[y][y]*(q/y)+S[y][q%y];
        }
        return rv;
    }

}
