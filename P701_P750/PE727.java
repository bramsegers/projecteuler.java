package P701_P750;

import util.Util;

public class PE727 {

    public static void main(String[] args) {
        new PE727().solve();
    }
    
    void solve(){
        long n=0;
        double d=0;
        for(int a=1;a<=100;a++)
            for(int b=a+1;b<=100;b++)
                for(int c=b+1;c<=100;c++)
                    if(Util.gcd(Util.gcd(a,b),c)==1){
                        d+=diff(c,b,a);
                        n++;
                    }
        System.out.format("%.8f%n",d/n);
    }

    double diff(double a,double b, double c){
        double r, r1=0, r2=a+b+c;
        while(true){
            r=(r1+r2)/2;
            double s=Math.acos(((a+r)*(a+r)+(b+r)*(b+r)-(a+b)*(a+b))/(2*(a+r)*(b+r)))+
                     Math.acos(((b+r)*(b+r)+(c+r)*(c+r)-(b+c)*(b+c))/(2*(b+r)*(c+r)))+
                     Math.acos(((a+r)*(a+r)+(c+r)*(c+r)-(a+c)*(a+c))/(2*(a+r)*(c+r)));
            double d=2*Math.PI-s;
            if(Math.abs(d)<1e-12) break;
            if(d<0) r1=r;
            else r2=r;
        }
        double x= (a+r)*((a+b)*(a+b)+(a+r)*(a+r)-(b+r)*(b+r))/(2*(a+b)*(a+r));
        double y=Math.sqrt((a+r)*(a+r)-x*x);
        
        // A=(a,0)
        double xA=a;
        double yA=0;
        
        // B=(xB,yB)
        double t= b*((a+b)*(a+b)+(b+c)*(b+c)-(a+c)*(a+c))/(2*(a+b)*(b+c));
        double xB=a+b-t;
        double yB=Math.sqrt(b*b-t*t);
        
        // C=(xC,yC)
        double xC= a*((a+b)*(a+b)+(a+c)*(a+c)-(b+c)*(b+c))/(2*(a+b)*(a+c));
        double yC=Math.sqrt(a*a-xC*xC);
        
        double D=2*(xA*(yB-yC)+xB*(yC-yA)+xC*(yA-yB));
        double X=((xA*xA+yA*yA)*(yB-yC)+(xB*xB+yB*yB)*(yC-yA)+(xC*xC+yC*yC)*(yA-yB))/D;
        double Y=((xA*xA+yA*yA)*(xC-xB)+(xB*xB+yB*yB)*(xA-xC)+(xC*xC+yC*yC)*(xB-xA))/D;
        
        double diff=Math.sqrt((X-x)*(X-x)+(Y-y)*(Y-y));
        return diff;
    }
}