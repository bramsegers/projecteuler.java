package P601_P650;

import static java.lang.Math.*;

public class PE607{
    
    public static void main(String[] args){
        new PE607().solve();
    }
    
    double[] s={0,10,10,10,10,10,0};
    double[] v={10,9,8,7,6,5,10};
    
    void solve(){
        double a,t=0;
        s[0]=s[6]=(100/sqrt(2)-50)/2;
        for(int i=0;i<7;i++) 
            t+=sqrt(2)*s[i]/v[i];
        System.out.format("%.4f%n",t);
        a=binsearch(100/sqrt(2));
        t=time(a);
        System.out.format("%.10f%n",t);
    }
    
    double binsearch(double y){
        double m,ym,lo=PI/4,hi=PI/2;
        while(hi-lo>1e-12){
            m=(lo+hi)/2;
            ym=trace(m);
            if(ym>y) hi=m;
            else lo=m;
        }
        return hi;
    }
    
    double trace(double a){
        double r=s[0]*tan(a);
        for(int i=1;i<7;i++){
            a=asin(sin(a)*v[i]/v[i-1]);
            r+=s[i]*tan(a);
        }
        return r;
    }
    
    double time(double a){
        double r=s[0]/cos(a)/v[0];
        for(int i=1;i<7;i++){
            a=asin(sin(a)*v[i]/v[i-1]);
            r+=s[i]/cos(a)/v[i];
        }
        return r;
    }
    
}