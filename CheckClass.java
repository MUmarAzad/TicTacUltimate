import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class TicTacToe extends JPanel implements ActionListener {

    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToe() {
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.WHITE);
        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;

        // Initialize the board with empty cells
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (!gameOver) {
                    int row = e.getY() / 100;
                    int col = e.getX() / 100;
                    if (isValidMove(row, col)) {
                        board[row][col] = currentPlayer;
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                        repaint();
                        if (isGameOver()) {
                            gameOver = true;
                            JOptionPane.showMessageDialog(null, "Game Over! Player " + currentPlayer + " wins!");
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the game board
        g.setColor(Color.BLACK);
        g.drawLine(100, 0, 100, 300);
        g.drawLine(200, 0, 200, 300);
        g.drawLine(0, 100, 300, 100);
        g.drawLine(0, 200, 300, 200);

        // Draw X and O symbols
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 'X') {
                    drawX(g, col * 100, row * 100);
                } else if (board[row][col] == 'O') {
                    drawO(g, col * 100, row * 100);
                }
            }
        }
    }

    private void drawX(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.drawLine(x + 20, y + 20, x + 80, y + 80);
        g.drawLine(x + 20, y + 80, x + 80, y + 20);
    }

    private void drawO(Graphics g, int x, int y) {
        g.setColor(Color.BLUE);
        g.drawOval(x + 20, y + 20, 60, 60);
    }

    private boolean isValidMove(int row, int col) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ');
    }

    private boolean isGameOver() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true; // Row win
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true; // Column win
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true; // Diagonal win (top-left to bottom-right)
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true; // Diagonal win (top-right to bottom-left)
        }

        // Check for a draw
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false; // There are empty cells, the game is not over
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Game Over! It's a draw!");
        return true; // It's a draw
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tic-Tac-Toe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new TicTacToe());
            frame.pack();
            frame.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
