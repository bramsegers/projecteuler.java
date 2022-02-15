package P501_P550;

public class PE525 {

    public static void main(String[] args){
        new PE525().solve();
    }

    void solve(){
        //System.out.format("%.8f%n",C(2,4));
        System.out.format("%.8f%n",C(1,4)+C(3,4));
    }

    double C(double a,double b){
        double c=0;
        int steps=100000;
        for(int i=0;i<steps;i++){
            double theta = i*Math.PI/(2*steps);
            double theta1 = (i+1)*Math.PI/(2*steps);
            double x = a*Math.cos(theta);
            double y = b*Math.sin(theta);
            double x1 = a*Math.cos(theta1);
            double y1 = b*Math.sin(theta1);
            double r_par = (a*a-b*b)*x*y / Math.hypot(a*a*y,b*b*x);
            double r_perp = (b*b*x*x+a*a*y*y) / Math.hypot(a*a*y,b*b*x);
            double r1_par = (a*a-b*b)*x1*y1 / Math.hypot(a*a*y1,b*b*x1);
            double r1_perp = (b*b*x1*x1+a*a*y1*y1) / Math.hypot(a*a*y1,b*b*x1);
            double ds = Math.hypot(x1-x,y1-y);
            c += Math.hypot(ds+r1_par-r_par,r1_perp-r_perp);
        }
        return 4*c;
    }

}
