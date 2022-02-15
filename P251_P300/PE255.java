package P251_P300;

public class PE255{

    public static void main(String[] args){
        new PE255().solve(5);
        new PE255().solve(14);
    }

    void solve(int d){
        long L=pow(10,d-1);
        long R=pow(10,d)-1;
        long x=(d%2==1)
            ? 2*pow(10,(d-1)/2)
            : 7*pow(10,(d-2)/2);
        double ans=solve(L,R,x);
        System.out.format("P(%d)=%.10f%n",d,ans);
    }

    double solve(long L,long R,long x){
	if(f(L,x)==x && f(R,x)==x) return 1;
	if(f(L,x)==f(R,x)) return 1+solve(L,R,f(L,x));
	long k=ceil(L,x);
	double res=0;
	long sum=0;
	while(true){
            long k1=(k-1)*x+1;
            long k2=k*x;
            if(L>k1) k1=L;
            if(R<k2) k2=R;
            if(k1>k2) return res/sum+1;
            if(f(k1,x)!=x) res+=(k2-k1+1)*solve(k1,k2,f(k1,x));
            sum+=k2-k1+1;
            k++;
	}
    }

    long pow(int a,int b)       {return (long)Math.pow(a,b);}

    long f(long n,long x)       {return (x+ceil(n,x))/2;}

    long ceil(long a,long b)    {return (a+b-1)/b;}

}