package P301_P350;

public class PE317 {

    public static void main(String[] args) {
        new PE317().solve(20, 100);
    }

    /*
     * Bergparabool f(x) met y-max in (0,Y) en x-max in (X,0) 
     * 
     * x(t) = V*t*cos(a)
     * y(t) = -0.5*G*t^2 + V*t*sin(a) + H
     *
     * y max -> a=PI/4, t = V/G, y = V^2/2*G + H
     * x max -> (V/G)*sqrt(V*V+2*G*H) (*)
     * (*) http://math.stackexchange.com/questions/127300/maximum-range-of-a-projectile-launched-from-an-elevation
     * 
     * f(x)= -p*x^2 + q -> q=Y, p=Y/X^2
     * 
     * Volume = PI*X^2*Y / 2 (**)
     * (**) https://www.youtube.com/watch?v=QLo5dRFEyl8  
     */
    void solve(double V, double H) {
        double G = 9.81;
        double ymax = V * V / (2 * G) + H;
        double xmax = (V / G) * Math.sqrt(V * V + 2 * G * H);
        double volume = Math.PI * xmax * xmax * ymax / 2;
        System.out.format("P(%.0f,%.0f)=%.4f%n", V, H, volume);
    }

}
