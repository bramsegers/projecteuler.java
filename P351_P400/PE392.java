package P351_P400;

import static java.lang.Math.*;

public class PE392{

    public static void main(String[] args){
        new PE392().solve(10);
        new PE392().solve(400);
    }

    void solve(int N){
	int n=N/2+1;
	double[] f=new double[n+1];
        double M=0;
        double L=0;
        double R= PI/2;
	while(R-L>1e-14){
            M=(L+R)/2;
            f[0]=PI/2;
            f[1]=M;
            for(int j=2;j<=n;j++)
                f[j]=calc(f[j-2],f[j-1]);
            if(f[n]<0) L=M;
            else R=M;
	}
	f[0]=PI/2;
	f[1]=M;
	for(int j=2;j<=n;j++)
            f[j]=calc(f[j-2],f[j-1]);
	double a=0,b;
	for(int i=1;i<=n;i++){
            b=cos(f[i])-cos(f[i-1]);
            b*=1-sin(f[i-1]);
            a+=b;
        }
        a=(1-a)*4;
        System.out.format("A(%d)=%.10f%n",N,a);
    }

    double calc(double a,double b){
        if(a<0||b<0) return -1;
        double sa=sin(a);
        double sb=sin(b);
        double cb=cos(b);
	if(abs((sa*sb-sb*sb+cb*cb)/cb)>1) return -1;
	return acos((sa*sb-sb*sb+cb*cb)/cb);
    }

}
