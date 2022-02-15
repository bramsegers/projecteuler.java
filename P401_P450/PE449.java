package P401_P450;

public class PE449{

    public static void main(String[] args){
        new PE449().solve(2,1,1);
        new PE449().solve(3,1,1);
    }

    void solve(double a,double b,double c){
        int N=40000000;
        double lastx=0,lasty=b+c,sum=0;
        for(int i=1;i<=N;i++){
            double x=a*i/N;
            double y=f(x,a,b);
            double s=-1d/df(x,a,b);
            double d=Math.atan(s);
            double x2=x+c*Math.cos(d);
            double y2=y+c*Math.sin(d);
            double h=lasty-y2;
            double r=lastx+(x2-lastx)/2;
            sum+=2*Math.PI*r*r*h;
            lasty=y2;
            lastx=x2;
        }
        sum-=4*a*a*b*Math.PI/3;
        System.out.format("%.8f%n",sum);
    }

    double f(double x,double a,double b){
        return Math.sqrt(a*a-x*x)*b/a;
    }

    double df(double x,double a,double b){
        return -(b*x)/(a*Math.sqrt(a*a-x*x));
    }

}
