import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ChaosGame extends JPanel {
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
        lastX = random.nextInt(maxX);
        lastY = random.nextInt(maxY);

        g2d.setColor(Color.pink);
        g2d.drawLine(lastX, lastY, lastX, lastY);

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
