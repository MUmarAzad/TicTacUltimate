import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Coin implements ActionListener {
    private int finish;
    public static char turn;
    private String sideUp;
    private String user;
    public static char player1, player2;
    private JLabel label1, label2;
    private JButton button1, button2, b1, b2;
    private JPanel panel, pl;
    private JFrame frame, fr;

    public int toss() {
        Random ran = new Random();
        int num = ran.nextInt(2);
        return num;
    }

    public Coin() {
        finish = 0;
        frame = new JFrame("Time For a Quick Toss");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        label1 = new JLabel("Choose \"Heads\" or \"Tails\"");
        panel.add(label1);

        button1 = new JButton("Heads");
        button1.setSize(100, 50);
        button1.setBackground(Color.gray);
        button1.setFocusable(false);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                user = "Heads";
                determineWinner();
            }
        });
        panel.add(button1);

        button2 = new JButton("Tails");
        button2.setSize(100, 50);
        button2.setBackground(Color.gray);
        button2.setFocusable(false);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                user = "Tails";
                determineWinner();
            }
        });
        panel.add(button2);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(250, 100);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void determineWinner() {
        int num = toss();
        if (num == 0) {
            sideUp = "Heads";
        } else {
            sideUp = "Tails";
        }
        if (sideUp.equals(user)) {
            JOptionPane.showMessageDialog(null, "You won the toss!");
            choose();
        }else {
            JOptionPane.showMessageDialog(null, "Computer won the toss!");
            num = toss();
            if (num == 0) {
                player1 = 'X';
                player2 = 'O';
                JOptionPane.showMessageDialog(null, "Player1: X\nPlayer2: O");
                frame.dispose();
                finish = 1;
                turn = 'X';
            } else {
                player1 = 'O';
                player2 = 'X';
                JOptionPane.showMessageDialog(null, "Player1: O\nPlayer2: X");
                frame.dispose();
                finish = 1;
                turn = 'O';
            }
        }
        if(finish==1)
            new GUI();
    }

    public void choose(){
        fr = new JFrame("Choose");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLayout(new BorderLayout());

        pl = new JPanel();
        pl.setLayout(new GridLayout(0, 1));

        label2 = new JLabel("Please Choose \"X\" or \"O\"");
        pl.add(label2);
        b1 = new JButton("X");
        b1.setSize(100, 50);
        b1.setBackground(Color.gray);
        b1.setFocusable(false);
        b2 = new JButton("0");
        b2.setSize(100, 50);
        b2.setBackground(Color.gray);
        b2.setFocusable(false);
        pl.add(b1);
        pl.add(b2);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player1 = 'X';
                player2 = 'O';
                JOptionPane.showMessageDialog(null, "Player1: X\nPlayer2: O");
                fr.dispose();
                frame.dispose();
                finish = 1;
                turn = 'X';
                new GUI();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player1 = 'O';
                player2 = 'X';
                JOptionPane.showMessageDialog(null, "Player1: O\nPlayer2: X");
                fr.dispose();
                frame.dispose();
                finish = 1;
                turn = 'O';
                new GUI();
            }
        });

        fr.add(pl, BorderLayout.CENTER);
        fr.setSize(250, 100);
        fr.setResizable(false);
        fr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
