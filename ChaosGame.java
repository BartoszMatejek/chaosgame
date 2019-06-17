package dooddania;

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
        initializeGreatPoints();
        Graphics2D graphics2D = (Graphics2D) g;
        System.out.println(getWidth());
        System.out.println(getHeight());
//        graphics2D.setStroke(new BasicStroke(1));
        drawFirstPoints(graphics2D);
        generateStartGamePoint();

        graphics2D.setColor(Color.pink);
        graphics2D.setStroke(new BasicStroke(8));
        drawPoint(graphics2D, lastX, lastY); // rysujemy startowy punkt w grze

        graphics2D.setStroke(new BasicStroke((float) 1));

        for (int i = 0; i <= 1000000; i++) {
            int randomPointFromDefaults = random.nextInt(3);

            if (randomPointFromDefaults == 0) {
                graphics2D.setColor(Color.red);
                drawPoint(graphics2D, (ax + lastX) / 2, (ay + lastY) / 2);
                setLastPoints(ax, ay);
            } else if (randomPointFromDefaults == 1) {
                graphics2D.setColor(Color.blue);
                drawPoint(graphics2D, (bx + lastX) / 2, (by + lastY) / 2);
                setLastPoints(bx, by);
            } else {
                graphics2D.setColor(Color.yellow);
                drawPoint(graphics2D, (cx + lastX) / 2, (cy + lastY) / 2);
                setLastPoints(cx, cy);
            }

        }
    }

    private void generateStartGamePoint() {
        boolean shouldGenerateValueAgain = true;
        Triangle triangle = new Triangle(ax, ay, bx, by, cx, cy);
        while (shouldGenerateValueAgain) {
            lastX = random.nextInt(maxX);
            lastY = random.nextInt(maxY);
            if (triangle.contains(lastX, lastY)) {
                shouldGenerateValueAgain = false;
            }
        }
    }

    private void setLastPoints(int x, int y) {
        this.lastX = (this.lastX + x) / 2;
        this.lastY = (this.lastY + y) / 2;
    }

    private void drawPoint(Graphics2D graphics2D, int x, int y) {
        graphics2D.drawLine(x, y, x, y);
    }

    private void drawFirstPoints(Graphics2D graphics2D) {
        graphics2D.setColor(Color.red);
        drawPoint(graphics2D, ax, ay);
        graphics2D.setColor(Color.blue);
        drawPoint(graphics2D, bx, by);
        graphics2D.setColor(Color.yellow);
        drawPoint(graphics2D, cx, cy);
    }

    private void initializePoints() {
        maxX = getWidth();
        maxY = getHeight();
        ax = random.nextInt(maxX);
        ay = random.nextInt(maxY);
        bx = random.nextInt(maxX);
        by = random.nextInt(maxY);
        cx = random.nextInt(maxX);
        cy = random.nextInt(maxY);
    }

    private void initializeGreatPoints() {
        maxX = getWidth();
        maxY = getHeight();
        ax = maxX;
        ay = maxY;
        bx = 0;
        by = maxY;
        cx = getWidth() / 2;
        cy = 0;
    }

    public static void main(String[] args) {
//        ChaosGame points =
        JFrame frame = new JFrame("Chaos Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1024, 1024);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().add(new ChaosGame());
        frame.setVisible(true);
    }
}