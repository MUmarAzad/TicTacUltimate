import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JPanel {
    public static boolean game;
    public static char[][] superGrid;
    public static JFrame frame;
    public JLabel turnLabel;

    public GUI() {
        game = true;
        superGrid = new char[9][9];
        frame = new JFrame("Super Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                superGrid[row][col] = ' ';
            }
        }
        JPanel board = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 =(Graphics2D)g;

                int cell = 600 / 9;

                for (int row = 0; row < 9; row++) {
                    int y = row * cell;
                    g.drawLine(0, y, 600, y);
                    if(row == 2 || row == 5 || row == 8) {
                        g2.setStroke(new BasicStroke(3.0f));
                    } else{
                        g2.setStroke(new BasicStroke(1.0f));
                    }
                }

                g.drawLine(0, 600, 600, 600);
                for (int col = 0; col < 9; col++) {
                    int x = col * cell;
                    g.drawLine(x, 0, x, 600);
                    if(col == 2 || col == 5 || col == 8) {
                        g2.setStroke(new BasicStroke(3.0f));
                    } else{
                        g2.setStroke(new BasicStroke(1.0f));
                    }
                }

                g.drawLine(600, 0, 600, 600);
                g.setColor(new Color(117, 81, 66));
                g.fillRect(602, 0, 250, 80);

                g.setColor(Color.ORANGE);
                g.setFont(new Font("Arial", Font.PLAIN, 40));
                g.drawString("Player Turn", 630, 50);
                g.setColor(Color.GRAY);
                g.fillRect(602, 160, 250, 120);

                g.setColor(new Color(5, 0, 105));
                g.drawString("Player 1: " + Coin.player1, 610, 200);
                g.drawString("Player 2: " + Coin.player2, 610, 250);

                g.setColor(new Color(28, 71, 34));
                g.setFont(new Font("Arial", Font.PLAIN, 80));
                g.drawString(Coin.turn +"", 690, 150);

//                g.drawLine(602, 160, 852, 160);
//                g.drawLine(602, 300, 852, 300);

                g.drawRect(602, 0, 250, 600);

                for (int row = 0; row < 9; row++) {
                    for (int col = 0; col < 9; col++) {
                        char value = superGrid[row][col];
                        if (value == 'X') {
                            drawX(g, col * cell, row * cell);
                        } else if (value == 'O') {
                            drawO(g, col * cell, row * cell);
                        }
                    }
                }
            }
        };

        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cell = 600 / 9;
                int col = e.getX() / cell;
                int row = e.getY() / cell;

                if (isValidMove(row, col)) {
                    // Update the superGrid with the player's move (e.g., 'X')
                    superGrid[row][col] = Coin.turn;
                    // Call a method to handle game logic
                    handleGameLogic(row, col);
                    // Repaint the board to update the display
                    board.repaint();
                }
            }
        });

        panel.add(board);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(880, 640);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    private void drawX(Graphics g, int x, int y) {
        Graphics2D g2 =(Graphics2D)g;
        g2.setStroke(new BasicStroke(3.0f));
        g.setColor(Color.BLUE);
        g.drawLine(x + 10, y + 10, x + 50, y + 50);
        g.drawLine(x +10, y + 50, x + 50, y+ 10);
    }

    private void drawO(Graphics g, int x, int y) {
        Graphics2D g2 =(Graphics2D)g;
        g2.setStroke(new BasicStroke(3.0f));
        g.setColor(Color.RED);
        g.drawOval(x + 10, y + 10, 50, 50);
    }

    private boolean isValidMove(int row, int col) {
        return (row >= 0 && row < 9 && col >= 0 && col < 9 && superGrid[row][col] == ' ');
    }

    private void handleGameLogic(int row, int col) {
        // Implement your game logic here
        Coin.turn = (Coin.turn == 'X') ? 'O' : 'X';
    }

}