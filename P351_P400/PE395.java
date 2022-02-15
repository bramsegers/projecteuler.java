package P351_P400;

import static java.lang.Math.PI;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.List;

public class PE395{

    public static void main(String[] args){
        new PE395().solve();
    }
    
    double minx,miny,maxx=1,maxy=1;

    void solve(){        
        List<S> t2,t1=new ArrayList<>();
        t1.add(new S(0,1,0,0));
        double a=1,lasta=0,d=0.001;
        while(a-lasta>1e-10){
            lasta=a;
            while(t1.size()<1000000){
                t2=new ArrayList<>();
                for(S s:t1){
                    t2.add(s.left());
                    t2.add(s.right());
                }
                t1=t2;        
            }
            d*=0.1;
            t2=new ArrayList<>();
            for(S s:t1){
                if(!(s.x-minx>d 
                  && s.y-miny>d 
                  && maxx-s.x>d 
                  && maxy-s.y>d)) 
                    t2.add(s);
            }
            t1=t2;
            a=(maxx-minx)*(maxy-miny);
            System.out.format("%.10f%n",a);           
        }    
    }
    
    class S{
        
        double a,r,x,y;
        
        S(double _a,double _r,double _x,double _y){
            a=_a;
            r=_r;
            x=_x;
            y=_y;
            if(x<minx) minx=x;
            if(y<miny) miny=y;
            if(x>maxx) maxx=x;
            if(y>maxy) maxy=y;
        }

        S left() {
            double a2=a+atan(3d/4);
            double r2=0.8*r;
            double x2=x+r2*cos(a2+PI/2);
            double y2=y+r2*sin(a2+PI/2);
            return new S(a2,r2,x2,y2);
        }

        S right() {
            double a2=a-atan(4d/3);
            double r2=0.6*r;
            double x2=x+1.4*r*cos(a+atan(3d/4));
            double y2=y+1.4*r*sin(a+atan(3d/4));
            return new S(a2,r2,x2,y2);
        }
        
    }
    
}
