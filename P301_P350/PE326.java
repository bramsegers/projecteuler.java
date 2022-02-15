package P301_P350;

public class PE326{

    public static void main(String[] args){
        new PE326().f(10,10);
        new PE326().f(10000,1000);
        new PE326().f(1000000000000L,1000000);
    }

    void f(long N,int M){
        int T=N<6*M?(int)N:6*M;
        long[] m=new long[M];
        m[1]=(N-1)/T+1;
        int a=1,n=1;
        while(++n<=T){
            a=(a+a(n))%M;
            m[a]+=(N-n)/T+1;
        }
        long sum=m[0];
        for(int i=0;i<M;i++)
            sum+=m[i]*(m[i]-1)/2;
        System.out.format("f(%d,%d)=%d%n",N,M,sum);
    }

    int[] a={1,5,3,2,4,8};
    int[] b={0,5,3,2,3,5};
    int a(int n){
        int k=n+2;
        int m=k%6;
        int d=k/6;
        n-=a[m]+(d-1)*b[m];
        return n;
    }

}