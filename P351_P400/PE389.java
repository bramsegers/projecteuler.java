package P351_P400; 

public class PE389{

    public static void main(String[] args){
        new PE389().solve1(4,6,8,12,20);
        new PE389().solve2(4,6,8,12,20);
    }
    
    void solve1(int... x){
        int L=x[0], N=1;
        for(int i:x) N*=i; N++;
        double[] p=new double[N];
        double[] q=new double[N];
        double[] pre=new double[N];
        double[] cur=new double[N];
	for(int i=1;i<=L;i++) p[i]=1D/L;
	for(int it=1;it<x.length;it++){
            int M=x[it];
            for(int i=0;i<=L*M;i++) 
                q[i]=pre[i]=0;            
            pre[0]=1;
            for(int j=1;j<=L;j++){
                cur[0]=0;
		for(int i=1;i<=j*M;i++)
                    cur[i]=cur[i-1]+(pre[i-1]
                    -(i>M?pre[i-M-1]:0))/M;
                for(int i=0;i<=j*M;i++)
                    q[i]+=(pre[i]=cur[i])*p[j];
            }
            L*=M;
            for(int i=0;i<=L;i++)
                p[i]=q[i];
	}
	double E=0,E2=0;
	for(int i=0;i<=L;i++){
            E+=i*p[i];
            E2+=i*i*p[i];
	}
        System.out.format("%.4f%n",E2-E*E);
    }
                
    void solve2(int... dice){ //wtf??
        double E=1;
        for(int d:dice) E=E*(d+1)/2;
        System.out.format("%.4f%n",E*(E-1)/3);
    }

}