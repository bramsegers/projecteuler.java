package P601_P650;

public class PE613 {

    // See: pe613_wolfram.png
    public static void main(String[] args) {
        double d=0.5+(32*Math.log(2)+9*Math.log(3)-25*Math.log(5))/(24*Math.PI);
        System.out.format("%.10f%n", d);
    }
    
    //N=131072
    //elapsed:    922039ms
    //iterations: 6442319873
    //estimation: 0.3916730957908245    
    void solve(int N){
        long c=0;
        double s=0; 
        double d=4D/N;
        for(int y=1;y<N;y++){
            double dx,dy=d*y,mx=3-(3*dy/4);
            for(int x=1;(dx=d*x)<mx;x++){
                s+=Math.atan((3-dx)/dy);
                s+=Math.atan((4-dy)/dx);
                c++;
            }
        }
        System.out.println(N+" "+c);
        double p=0.75-s/(2*Math.PI*c);
        System.out.println(p);
    }

}
