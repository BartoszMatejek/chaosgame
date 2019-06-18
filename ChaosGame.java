package grawchaos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ChaosGame extends JPanel implements ActionListener {

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
    private final int DELAY = 100;
    private Timer timer;
    private boolean pointInitialized = false;
    private boolean isSupered = false;
    private Random random = new Random();

    public ChaosGame() {

        initTimer();
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Timer getTimer() {

        return timer;
    }

    public void paintComponent(Graphics g) {

        if (!isSupered) {
            setBackground(Color.black);
            super.paintComponent(g);
        } //żeby nie nadpisywać poprzedniej zawartości gdyż bez tego ekran jest czyszczony
        isSupered = true;
        drawAll(g);

    }

    private void drawAll(Graphics g) {
        if (!pointInitialized) {
            setBeginValues();
        }
        pointInitialized = true;

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(8));

        graphics2D.setColor(Color.pink);
        graphics2D.setStroke(new BasicStroke(8));

        graphics2D.setStroke(new BasicStroke((float) 1));

        for (int i = 0; i <= 100; i++) {
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

    public void setBeginValues() {
        initializeGreatPoints();
        generateStartGamePoint();
    }

    private void generateStartGamePoint() {
//        boolean shouldGenerateValueAgain = true;
//        Triangle triangle = new Triangle(ax, ay, bx, by, cx, cy);
//        while (shouldGenerateValueAgain) {
            lastX = random.nextInt(maxX);
            lastY = random.nextInt(maxY);
//            if (triangle.contains(lastX, lastY)) {
//                shouldGenerateValueAgain = false;
//            }
//        }
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
        drawPoint(graphics2D, lastX, lastY);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
