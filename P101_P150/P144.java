package P101_P150;


/*
 * In laser physics, a "white cell" is a mirror system that acts as a delay line 
 * for the laser beam. The beam enters the cell, bounces around on the mirrors, 
 * and eventually works its way back out.
 * 
 * The specific white cell we will be considering is an ellipse with the equation 
 * 4x^2 + y^2 = 100
 * 
 * The section corresponding to -0.01 <= x <= +0.01 at the top is missing, 
 * allowing the light to enter and exit through the hole.
 * 
 * The light beam in this problem starts at the point (0.0,10.1) just outside the 
 * white cell, and the beam first impacts the mirror at (1.4,-9.6).
 * 
 * Each time the laser beam hits the surface of the ellipse, it follows the usual 
 * law of reflection "angle of incidence equals angle of reflection." That is, both 
 * the incident and reflected beams make the same angle with the normal line at 
 * the point of incidence.
 * 
 * In the figure on the left, the red line shows the first two points of contact 
 * between the laser beam and the wall of the white cell; the blue line shows the 
 * line tangent to the ellipse at the point of incidence of the first bounce.
 * 
 * The slope m of the tangent line at any point (x,y) of the given ellipse is: m = 4x/y
 * The normal line is perpendicular to this tangent line at the point of incidence.
 * 
 * How many times does the beam hit the internal surface of the white cell before exiting?
 */
public class P144 {

    public static int solve() {
        int result = 0;
        double xA = 0.0;
        double yA = 10.1;
        double xO = 1.4;
        double yO = -9.6;
        while (xO > 0.01 || xO < -0.01 || yO < 0) {
            double slopeA = (yO - yA) / (xO - xA);
            double slopeO = -4 * xO / yO;
            double tanA = (slopeA - slopeO) / (1 + slopeA * slopeO);
            double slopeB = (slopeO - tanA) / (1 + tanA * slopeO);
            double interceptB = yO - slopeB * xO;
            double a = 4 + slopeB * slopeB;
            double b = 2 * slopeB * interceptB;
            double c = interceptB * interceptB - 100;
            double ans1 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
            double ans2 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
            xA = xO;
            yA = yO;
            xO = (Math.abs(ans1 - xO) > Math.abs(ans2 - xO)) ? ans1 : ans2;
            yO = slopeB * xO + interceptB;
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(P144.solve());
    }
}
