package P451_P500;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static java.lang.Math.max;

public class PE476{

    public static void main(String[] args){
        new PE476().solve(5);
        new PE476().solve(1803);
    }
    
    void solve(int n){
        double sum=0;
        long count=0;
        for(int a=1;a<=n;a++)
            for(int b=a;a+b<=n;b++)
                for(int c=b;c<a+b;c++){
                    sum+=R(a,b,c);
                    count++;
                }
        System.out.format("S(%d)=%.5f%n",n,sum/count);
    }
    
    double R(int a,int b,int c){
        double s0=a+b+c;
        double r0=sqrt((b+c-a)*(b+a-c)*(a+c-b)/s0)/2;
        double xb=a,yb=0,xc=0,yc=0;
        double xa=(a*a+b*b-c*c)/(2.0*a);
        double ya=sqrt(b*b-xa*xa);
        double xi=(a*xa+b*xb+c*xc)/s0;
        double yi=(a*ya+b*yb+c*yc)/s0;
        double da=sqrt((xa-xi)*(xa-xi)+(ya-yi)*(ya-yi));
        double db=sqrt((xb-xi)*(xb-xi)+(yb-yi)*(yb-yi));
        double ra=(da-r0)*r0/(da+r0);
        double rb=(db-r0)*r0/(db+r0);
        double rc=(da-r0-2*ra)*ra/(da-r0);
        return PI*(r0*r0+ra*ra+max(rb*rb,rc*rc));
    }
    
}
