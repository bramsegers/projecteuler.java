package P301_P350;

import static java.lang.Math.sqrt;
import static java.lang.Math.abs;

//132.52756426
//BUILD SUCCESSFUL (total time: 56 minutes 58 seconds)
public class PE314 {

    /*
    Optimal shape in continuous space is rectangle of size 500 
    with rounded corner radius r=500/(2+sqrt(pi))
    Integral/lattice point solution is close to this    
    */
    public static void main(String[] args){
        new PE314().solve(500);
    }
    
    int N, M1=6, M2=110;;
    double r,max;
        
    void solve(int n){
        N=n/2;
        r=n/(2+sqrt(Math.PI));
        int s=(int)(N-r);
        for(int x=s-M1;x<=s+M1;x++)
            search(0,0,x,0,0,0);
    }
    
    void search(int i,int j,int x,int y,double a, double p){
        a+=area(i,j,x,y);
        p+=perim(i,j,x,y);
        if(y==N-x){
            if(max*p<a){
                max=a/p;
                System.out.format("%.8f%n",max);
            }
            return;
        }        
        if(y-j==x-i) return;
        for(int y2=y+1;y2<=N-x;y2++){
            for(int x2=x+y2-y;y2<=N-x2;x2++){
                if((y-j)*(x2-x)>=(x-i)*(y2-y)) break;
                if(close(x2,y2)) search(x,y,x2,y2,a,p);
            }
        }
    }
    
    double area(int i,int j,int x,int y){
        return abs(i*(y-N)+x*(N-j))/2.0;
    }
    
    double perim(int i,int j,int x,int y){
        return sqrt((x-i)*(x-i)+(y-j)*(y-j));
    }

    boolean close(int x,int y){
        double d=(N-r-x)*(N-r-x)+(r-y)*(r-y);
        return abs(r*r-d)<M2;
    }
 
}
