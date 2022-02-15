package P451_P500;

import static java.lang.Math.*;

public class PE460{

    public static void main(String[] args){
        new PE460().solve(4,1);
        new PE460().solve(10,0.5);
        new PE460().solve(100,0.25);
        new PE460().solve(10000,0.25);
    }

    int r,s;
    int[][] p;
    double[] mem;
    
    void solve(int n,double diff){
        r=n/2;
        p=new int[5000][2];
        for(int i=0;i<=r;i++)
            for(int j=1;j<=r+diff;j++){
                double d=sqrt((r-i)*(r-i)+j*j);
                if(abs(r-d)<=diff) p[s++]=new int[]{i,j};
            }
        mem=new double[s];
        System.out.format("F(%d)=%.9f%n",n,dp(0,0,1));
    }

    double dp(int i,int x,int y){
        if(x==r) return 0;
        if(mem[i]>0) return mem[i];
        double t=2D*(r-x)/y,t2;
        for(int j=i;j<s;j++){
            int x2=p[j][0], y2=p[j][1];
            if(y2<=y || (x==x2&&x>0)) continue;
            t2=sqrt((y2-y)*(y2-y)+(x2-x)*(x2-x));
            t2=2*t2*(log(y2)-log(y))/(y2-y);
            t=Math.min(t,t2+dp(j+1,x2,y2));
        }
        return mem[i]=t;
    }

}
