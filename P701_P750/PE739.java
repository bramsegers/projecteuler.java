package P701_P750;

public class PE739 {

    public static void main(String[] args) {
        new PE739().solve(100000000,1000000007);
    }

    void solve(int N,int M){
        long ans=0;
        int[] p=new int[2*N+1];
        int[] a=new int[2*N+1];
        int[] i=new int[2*N+1];
        i[0]=i[1]=1;p[1]=1;a[1]=1;a[2]=3;
        for(int n=2;n<=2*N;n++) i[n]=(int)(1L*i[M%n]*(M-M/n)%M);
        for(int n=2;n<=2*N;n++) i[n]=(int)(1L*i[n-1]*i[n]%M);
        for(int n=2;n<=2*N;n++) p[n]=(int)(1L*p[n-1]*n%M);
        for(int n=3;n<=  N;n++) a[n]=(a[n-1]+a[n-2])%M;
        for(int n=2;n<=N;n++){
            long v=n-1;
            v=v*p[2*N-n-2]%M;
            v=v*i[N-1]%M;
            v=v*i[N-n]%M;
            ans=(ans+a[n]*v%M)%M;
        }
        System.out.println(ans);
    }
     
}