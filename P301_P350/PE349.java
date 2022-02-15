package P301_P350;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PE349 extends JFrame {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PE349().setVisible(true);
            }
        });
    }

    private final int w = 600, h = 600;
    private final BufferedImage img;

    private final long STEPS = 1000000000000000000L;

    private class MyPanel extends JPanel {

        public MyPanel() {
            setSize(w, h);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, w, h, 250, 250, 350, 350, null);
        }

    }

    public PE349() {
        pack();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(w + getInsets().left + getInsets().right, h + getInsets().top + getInsets().bottom);
        setLocationRelativeTo(null);
        add(new MyPanel());

        img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        System.out.println("Rendering steps...");
        new Thread(renderSteps()).start();
    }

    private Runnable renderSteps() {

        return new Runnable() {

            int X = w / 2;
            int Y = h / 2;
            int dir = 0;

            int white = Color.WHITE.getRGB();
            int black = Color.BLACK.getRGB();

            int steps = 12000;
            int[] count = new int[steps];
            String bitset = "-";

            @Override
            public void run() {

                for (int j = 0; j < h; j++) {
                    for (int i = 0; i < w; i++) {
                        img.setRGB(i, j, white);
                    }
                }

                for (int step = 1; step < steps; step++) {

                    // flip 
                    if (img.getRGB(X, Y) == black) {
                        img.setRGB(X, Y, white);
                        dir = (dir + 3) % 4;
                        bitset = "0" + bitset;
                        count[step] = count[step - 1] - 1;
                    } else {
                        img.setRGB(X, Y, black);
                        dir = (dir + 1) % 4;
                        bitset = "1" + bitset;
                        count[step] = count[step - 1] + 1;
                    }

                    // move
                    switch (dir) {
                        case 0:
                            Y--;
                            break;
                        case 1:
                            X++;
                            break;
                        case 2:
                            Y++;
                            break;
                        case 3:
                            X--;
                            break;
                    }

                    // animate
                    repaint();
                    try {
                        Thread.sleep(step / 9000);
                    } catch (InterruptedException ex) {
                    }
                }

                // solve
                dispose();
                long a = solve(count, bitset);
                System.out.println(a);
            }
        };
    }

    private long solve(int[] count, String bitset) {

        if (STEPS < count.length) {
            return count[(int) STEPS];
        }

        System.out.println("Analyzing pattern...");
        String pattern = null;
        for (int len = bitset.length() / 3; len > 10; len--) {
            String p = bitset;
            String sub = p.substring(0, len);
            p = p.replaceFirst(sub, "");
            if (p.startsWith(sub)) {
                pattern = sub;
            }
        }
        if (pattern == null) {
            System.out.println("No pattern found...");
            return -1;
        }

        System.out.println("Pattern found... (" + pattern + ")");
        System.out.println("Calculating " + STEPS + "th step...");
        
        int offset = count.length - pattern.length() - 1;
        long i = STEPS - offset - 1;
        long m = i / pattern.length();
        int e = (int) (i % pattern.length());
        return m * (count[offset + pattern.length()] - count[offset]) + count[offset + e + 1];
    }

}
