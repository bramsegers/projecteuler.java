package P201_P250;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;

public class PE246{
    /*    
        After translation by (-3000,-1500), we have:
        • point   G : (5000,0)
        • circle  c : center (-5000,0) and radius 15000
        • ellipse e : f(x)= ±√(5*(7500-x)(7500+x))/3

        For any point P(px,py) outside e:
        Let t1 and t2 be the lines through P and touching e in R(x1,f(x1)) and S(x2,f(x2)). 
        Then x1 and x2 are obtained by solving for x: f'(x)*(x-px)+py=f(x)  (I asked Wolfram).
        Now angle ∠RPS can be obtained (atan2 function is convenient). P is valid iff ∠RPS > PI/4.

        From the center (0,0) of e, consider points P(x,y) with x>=0 and y>=0. 
        For each x, the number of valid points can be obtained by calculating:
        • ymin:= ceiling of f(x), or 0 if x outside domain of f(x)
        • ymax:  using binary search with safe initial upper bound
        Increment x until no valid points are found.

        In case of x outside domain of f(x), S is in lower half of e, so set f(x2) negative.
        Taking symmetry into account, multiply answer by 2 (if P on axis) or 4 otherwise.

        Java, <100ms    
    */    
    public static void main(String[] args){
        new PE246().solve();
    }
   
    void solve(){
        long x,ymin,ymax,sum=0;
        for(x=0;;x++){
            ymin=(x>7500)?0:(long)(f(x)+1);
            ymax=ymax(x,ymin,50000);
            if(ymax==0) break;
            sum+=(ymax-ymin+1)*((x==0)?2:4);
            if(ymin==0) sum-=2;
        } 
        System.out.println(sum); 
    }

    long ymax(long x,long yA,long yB){
        if(yB-yA<2) return yA;
        long y=(yA+yB)/2;
        double x1=x1(x,y);
        double x2=x2(x,y);
        double y1=f(x1);
        double y2=(x>7500)?-f(x2):f(x2);
        double a=atan2(y2-y,x2-x)-atan2(y1-y,x1-x);
        if(a<0) a+=2*PI;
        return (a>PI/4)?ymax(x,y,yB):ymax(x,yA,y);
    }

    double f(double x)           {return sqrt(5*(7500-x)*(7500+x))/3;}    
    double x1(double x,double y) {return 22500*(12500*x-sqrt(5*x*x+9*y*y-281250000)*y)/(5*x*x+9*y*y);}
    double x2(double x,double y) {return 22500*(12500*x+sqrt(5*x*x+9*y*y-281250000)*y)/(5*x*x+9*y*y);}
    
}
