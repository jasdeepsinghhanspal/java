import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FootballGame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BALL_SIZE = 40;
    private int ballX;
    private int ballY;
    private int ballSpeedX;
    private int ballSpeedY;
    private Timer timer;

    public FootballGame() {
        setTitle("Football Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addKeyListener(new BallController());
        ballX = WIDTH / 2 - BALL_SIZE / 2;
        ballY = HEIGHT / 2 - BALL_SIZE / 2;
        ballSpeedX = 2;
        ballSpeedY = 2;
        timer = new Timer(10, new TimerListener());
        timer.start();
    }

    private class BallController extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_UP) {
                ballSpeedY = -2;
            } else if (key == KeyEvent.VK_DOWN) {
                ballSpeedY = 2;
            } else if (key == KeyEvent.VK_LEFT) {
                ballSpeedX = -2;
            } else if (key == KeyEvent.VK_RIGHT) {
                ballSpeedX = 2;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
                ballSpeedY = 0;
            } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
                ballSpeedX = 0;
            }
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ballX += ballSpeedX;
            ballY += ballSpeedY;
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        g.fillRect(WIDTH / 2 - 5, 0, 10, HEIGHT);

        g.setColor(Color.BLACK);
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FootballGame::new);
    }
}
