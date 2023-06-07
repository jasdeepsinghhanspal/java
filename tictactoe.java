import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tictactoe extends JFrame implements ActionListener {
    private JButton[] buttons;
    private JLabel turnLabel;
    private boolean isPlayer1Turn;
    private boolean gameWon;

    public tictactoe() {
        super("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 3));

        buttons = new JButton[9];
        isPlayer1Turn = true;
        gameWon = false;

        turnLabel = new JLabel("Player 1's turn");
        turnLabel.setHorizontalAlignment(JLabel.CENTER);
        add(turnLabel);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (!gameWon && clickedButton.getText().equals("")) {
            if (isPlayer1Turn) {
                clickedButton.setText("X");
                turnLabel.setText("Player 2's turn");
            } else {
                clickedButton.setText("O");
                turnLabel.setText("Player 1's turn");
            }

            isPlayer1Turn = !isPlayer1Turn;
            checkWinConditions();
        }
    }

    private void checkWinConditions() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = buttons[i].getText();
        }

        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (!board[i].equals("") && board[i].equals(board[i + 1]) && board[i].equals(board[i + 2])) {
                highlightWinningCells(i, i + 1, i + 2);
                declareWinner(board[i]);
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!board[i].equals("") && board[i].equals(board[i + 3]) && board[i].equals(board[i + 6])) {
                highlightWinningCells(i, i + 3, i + 6);
                declareWinner(board[i]);
                return;
            }
        }

        // Check diagonals
        if (!board[0].equals("") && board[0].equals(board[4]) && board[0].equals(board[8])) {
            highlightWinningCells(0, 4, 8);
            declareWinner(board[0]);
            return;
        }

        if (!board[2].equals("") && board[2].equals(board[4]) && board[2].equals(board[6])) {
            highlightWinningCells(2, 4, 6);
            declareWinner(board[2]);
            return;
        }

        // Check for a draw
        boolean isDraw = true;
        for (String cell : board) {
            if (cell.equals("")) {
                isDraw = false;
                break;
            }
        }

        if (isDraw) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }
    }

    private void highlightWinningCells(int cell1, int cell2, int cell3) {
        buttons[cell1].setBackground(Color.GREEN);
        buttons[cell2].setBackground(Color.GREEN);
        buttons[cell3].setBackground(Color.GREEN);
        gameWon = true;
    }

    private void declareWinner(String winner) {
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        resetGame();
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setBackground(null);
        }

        isPlayer1Turn = true;
        gameWon = false;
 	  System.out.println();
        turnLabel.setText("/nPlayer 1's turn");
	 }

    public static void main(String[] args) {
        new tictactoe();
    }
}
