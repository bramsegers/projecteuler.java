package P101_P150;

import java.awt.Polygon;
import java.util.List;
import util.Util;

/*
 * Three distinct points are plotted at random on a Cartesian plane, 
 * for which -1000 <= x,y <= 1000, such that a triangle is formed.
 * 
 * Consider the following two triangles:
 * A(-340,495), B(-153,-910), C(835,-947)
 * X(-175,41) , Y(-421,-714), Z(574,-645)
 * 
 * It can be verified that triangle ABC contains the origin, whereas triangle XYZ does not. 
 * Using P102.txt, find the number of triangles for which the interior contains the origin.
 */
public class P102 {

    public static int solve(List<String> list) {
        int sum = 0;
        for (String s : list) {
            String[] c = s.split(",");
            int x1 = Integer.parseInt(c[0]);
            int x2 = Integer.parseInt(c[2]);
            int x3 = Integer.parseInt(c[4]);
            int y1 = Integer.parseInt(c[1]);
            int y2 = Integer.parseInt(c[3]);
            int y3 = Integer.parseInt(c[5]);
            Polygon p = new Polygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
            sum += p.contains(0, 0) ? 1 : 0;
        }
        return sum;
    }

    public static void main(String[] args) {
        List<String> list = Util.readText("files/P102.txt");
        System.out.println(P102.solve(list));
    }
}
