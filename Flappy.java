import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Flappy extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int GRAVITY = 2;
    private static final int JUMP = -20;
    private int birdY;
    private int velocity;
    private Timer timer;

    public Flappy() {
        setTitle("Flappy Bird");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addKeyListener(new BirdController());
        birdY = HEIGHT / 2;
        velocity = 0;
        timer = new Timer(20, new TimerListener());
        timer.start();
    }

    private class BirdController extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                velocity = JUMP;
            }
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            birdY += velocity;
            velocity += GRAVITY;
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.ORANGE);
        g.fillRect(0, HEIGHT - 120, WIDTH, 120);

        g.setColor(Color.GREEN);
        g.fillRect(0, HEIGHT - 120, WIDTH, 20);

        g.setColor(Color.RED);
        g.fillRect(100, birdY, 40, 40);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Flappy::new);
    }
}
