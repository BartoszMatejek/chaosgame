import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ChaosGame extends JPanel {
    class Triangle {
        Triangle(double x1, double y1, double x2, double y2, double x3,
                 double y3) {
            this.x3 = x3;
            this.y3 = y3;
            y23 = y2 - y3;
            x32 = x3 - x2;
            y31 = y3 - y1;
            x13 = x1 - x3;
            det = y23 * x13 - x32 * y31;
            minD = Math.min(det, 0);
            maxD = Math.max(det, 0);
        }

        boolean contains(double x, double y) {
            double dx = x - x3;
            double dy = y - y3;
            double a = y23 * dx + x32 * dy;
            if (a < minD || a > maxD)
                return false;
            double b = y31 * dx + x13 * dy;
            if (b < minD || b > maxD)
                return false;
            double c = det - a - b;
            if (c < minD || c > maxD)
                return false;
            return true;
        }

        private final double x3, y3;
        private final double y23, x32, y31, x13;
        private final double det, minD, maxD;
    }

    private int maxX;
    private int maxY;
    private int ax;
    private int ay;
    private int bx;
    private int by;
    private int cx;
    private int cy;
    private int lastX;
    private int lastY;

    private Random random = new Random();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.black);
        initalizeGreatPoints();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke((float) 1));
        g2d.setColor(Color.red);
        g2d.drawLine(ax, ay, ax, ay);
        g2d.setColor(Color.blue);
        g2d.drawLine(bx, by, bx, by);
        g2d.setColor(Color.yellow);
        g2d.drawLine(cx, cy, cx, cy);
        System.out.println(getWidth());
        System.out.println(getHeight());
        boolean shouldGenerateValueAgain = true;
        Triangle triangle = new Triangle(ax, ay, bx, by, cx, cy);
        while (shouldGenerateValueAgain) {
            lastX = random.nextInt(maxX);
            lastY = random.nextInt(maxY);
            if (triangle.contains(lastX, lastY)) {
                shouldGenerateValueAgain = false;
            }
        }

        g2d.setColor(Color.pink);
        g2d.setStroke(new BasicStroke((float) 8));
        g2d.drawLine(lastX, lastY, lastX, lastY);
        g2d.setStroke(new BasicStroke((float) 1));
        for (int i = 0; i <= 1000000; i++) {
            if (i < 50) {
                continue;
            }
            int randomPointFromDefaults = random.nextInt(3);
            if (randomPointFromDefaults == 0) {
                g2d.setColor(Color.red);
                g2d.drawLine((ax + lastX) / 2, (ay + lastY) / 2, (ax + lastX) / 2, (ay + lastY) / 2);
                this.lastX = (ax + lastX) / 2;
                this.lastY = (ay + lastY) / 2;
            } else if (randomPointFromDefaults == 1) {
                g2d.setColor(Color.blue);
                g2d.drawLine((bx + lastX) / 2, (by + lastY) / 2, (bx + lastX) / 2, (by + lastY) / 2);
                this.lastX = (bx + lastX) / 2;
                this.lastY = (by + lastY) / 2;
            } else {
                g2d.setColor(Color.yellow);
                g2d.drawLine((cx + lastX) / 2, (cy + lastY) / 2, (cx + lastX) / 2, (cy + lastY) / 2);
                this.lastX = (cx + lastX) / 2;
                this.lastY = (cy + lastY) / 2;
            }

        }
    }

    private void initializePoints() {
        maxX = getWidth() - 8;
        maxY = getHeight() - 10;
        ax = random.nextInt(maxX);
        ay = random.nextInt(maxY);
        bx = random.nextInt(maxX);
        by = random.nextInt(maxY);
        cx = random.nextInt(maxX);
        cy = random.nextInt(maxY);
    }

    private void initalizeGreatPoints() {
        maxX = getWidth() - 8;
        maxY = getHeight() - 10;
        ax = maxX;
        ay = maxY;
        bx = 10;
        by = maxY;
        cx = getWidth() / 2;
        cy = 5;
    }

    public static void main(String[] args) {
//        ChaosGame points =
        JFrame frame = new JFrame("Chaos Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().add(new ChaosGame());
        frame.setVisible(true);
    }
}
