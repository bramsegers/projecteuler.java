package P251_P300;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PE262 {

    //https://www.wolframalpha.com/input/?i=z%3D(+5000-0.005*(x*x%2By*y%2Bx*y)%2B12.5*(x%2By)+)+*+exp(+-abs(0.000001*(x*x%2By*y)-0.0015*(x%2By)%2B0.7)+)
    public static void main(String[] args) {
        new PE262().solve();
    }

    int //
            P = (int) 1e5, //   precision, arr size
            S = 1600, //        area
            A = 200, //         point A
            B = 1400, //        point B
            R1 = 600, //        radius 1
            R2 = 900; //        radius 2

    void solve() {

        double mx = binpeak(0, 0, S);
        double mh = h(mx, 0);
        double[] x = new double[P];
        double[] y = new double[P];

        for (int i = 0; i < P; i++) {
            double pi = Math.PI;
            double a = 0.75 * pi - i * pi / P;
            double r = binsearch(a, R1, R2, mh);
            x[i] = S / 2 + r * Math.cos(a);
            y[i] = S / 2 - r * Math.sin(a);
        }

        int i = start(x, y);
        int j = end(x, y);

        double len = 0;
        len += dist(A, A, x[i], y[i]);
        len += dist(B, B, x[j], y[j]);
        for (int k = i; k < j; k++) {
            len += dist(x[k], y[k], x[k + 1], y[k + 1]);
        }

        System.out.format("len=%.3f %n", len);

        BufferedImage img = getImage(2000, mh, i, j, x, y);
        //saveImg(img);
        showImg(img);

    }

    int start(double[] x, double[] y) {
        int i = 0;
        while ((y[i] - A) / (x[i] - A) > (y[i + 1] - y[i]) / (x[i + 1] - x[i])) {
            i++;
        }
        return i;
    }

    int end(double[] x, double[] y) {
        int j = P - 1;
        while ((x[j] - B) / (y[j] - B) > (x[j] - x[j - 1]) / (y[j] - y[j - 1])) {
            j--;
        }
        return j;
    }

    double h(double x, double y) {
        return x < 0 || y < 0 || x > S || y > S
                ? 0
                : (5000 - 0.005 * (x * x + y * y + x * y) + 12.5 * (x + y))
                * Math.exp(-Math.abs(0.000001 * (x * x + y * y) - 0.0015 * (x + y) + 0.7));
    }

    double binpeak(double y, double f, double t) {
        double m = (f + t) / 2;
        double d = 1e-7;
        double a = h(m - d, y);
        double b = h(m, y);
        double c = h(m + d, y);
        return a < b && b < c
                ? binpeak(y, m, t)
                : a > b && b > c
                        ? binpeak(y, f, m)
                        : m;
    }

    double binsearch(double a, double r1, double r2, double dest) {
        double r = (r1 + r2) / 2;
        double x = r * Math.cos(a);
        double y = r * Math.sin(a);
        double h = h(S / 2 + x, S / 2 - y);
        return Math.abs(h - dest) < 1e-7
                ? r
                : h < dest
                        ? binsearch(a, r1, r, dest)
                        : binsearch(a, r, r2, dest);
    }

    double dist(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    BufferedImage getImage(int w, double mh, int i1, int i2, double[] x, double[] y) {

        double[][] h = new double[w][w];
        double minh = Double.MAX_VALUE;
        double maxh = Double.MIN_VALUE;
        for (int j = 0; j < w; j++) {
            for (int i = 0; i < w; i++) {
                h[i][j] = h(1D * S * i / w, 1D * S * j / w);
                minh = Math.min(minh, h[i][j]);
                maxh = Math.max(maxh, h[i][j]);
            }
        }

        BufferedImage img = new BufferedImage(w, w, BufferedImage.TYPE_INT_RGB);
        for (int j = 0; j < w; j++) {
            for (int i = 0; i < w; i++) {
                if (h[i][j] > mh) {
                    img.setRGB(i, j, Color.RED.getRGB());
                } else {
                    int rgb = (int) (255 * (h[i][j] - minh) / (maxh - minh));
                    img.setRGB(i, j, new Color(rgb, rgb, rgb).getRGB());
                }
            }
        }

        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.GREEN);
        int s = 24;
        int wa = w * A / S;
        int wb = w * B / S;
        g2.fillOval(wa - s, wa - s, 2 * s, 2 * s);
        g2.fillOval(wb - s, wb - s, 2 * s, 2 * s);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5));
        g2.drawOval(wa - s, wa - s, 2 * s, 2 * s);
        g2.drawOval(wb - s, wb - s, 2 * s, 2 * s);

        int r1 = 600 * w / S;
        int r2 = 900 * w / S;
        g2.drawOval(w / 2 - r1, w / 2 - r1, 2 * r1, 2 * r1);
        g2.drawOval(w / 2 - r2, w / 2 - r2, 2 * r2, 2 * r2);

        g2.setColor(Color.BLUE);
        s = 10;
        int cut = 80;
        for (int i = 0; i < P; i += P / cut) {
            int x1 = (int) (x[i] * w / S);
            int y1 = (int) (y[i] * w / S);
            g2.fillOval(x1 - s, y1 - s, 2 * s, 2 * s);
        }
        int x1 = (int) (x[i1] * w / S);
        int y1 = (int) (y[i1] * w / S);
        int x2 = (int) (x[i2] * w / S);
        int y2 = (int) (y[i2] * w / S);
        g2.drawLine(wa, wa, x1, y1);
        g2.drawLine(wb, wb, x2, y2);

        return img;
    }

    void saveImg(BufferedImage img) {
        try {
            File outputfile = new File("saved.png");
            ImageIO.write(img, "png", outputfile);
            System.out.println("Image saved");
        } catch (IOException e) {
            System.out.println("Error saving image");
        }
    }

    void showImg(BufferedImage img) {
        new PEFrame(800, 800, img);
    }
    
    
    class PEFrame extends JFrame {

    int w, h;
    BufferedImage img;

    PEFrame(int x, int y, BufferedImage i) {
        super("Project Euler 262");

        w = x;
        h = y;
        img = i;

        setBounds(0, 0, w + 20, h + 42);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new MyPanel());
        setVisible(true);
    }

    private class MyPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, w, h, 0, 0, img.getWidth(), img.getHeight(), null);
        }

    }

}
    

}
