package grawchaos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MyFrame extends JFrame {

    public MyFrame() {

        initUI();
    }

    private void initUI() {

        final ChaosGame chaosGame = new ChaosGame();
        add(chaosGame);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = chaosGame.getTimer();
                timer.stop();
            }
        });

        setTitle("Gra w Chaos");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            MyFrame ex = new MyFrame();
            ex.setResizable(false);
            ex.setVisible(true);
        });
    }
}
