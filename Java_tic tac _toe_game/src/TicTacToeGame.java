import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGame extends JFrame {
    private JButton[] buttons;
    private boolean xTurn;
    private JButton statustxt;
    private JButton rest;
    private JButton exit;
    private int num1=0;
    private int num2=0;
    private JButton player1txt;
    private  JButton player2txt;
    public TicTacToeGame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        //top panel
        JPanel top=new JPanel();
        top.setBounds(30,10,640,60);
        //heading
        JLabel heading =new JLabel("TIC TAC TOE");
        heading.setBounds(0,5,500,75);
        heading.setFont(new Font("Arial", Font.BOLD, 34));
        heading.setForeground(new Color(120,120,250));
        top.add(heading);

        //body panel
        JPanel body=new JPanel(new GridLayout(3,3));
        body.setBounds(25,80,400,400);

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setFont(new Font("Arial", Font.PLAIN, 60));
            button.setFocusable(false);
            button.setBackground(new Color(204,229,255));
            button.addActionListener(new ButtonClickListener());
            buttons[i] = button;
            body.add(button);
        }
        //score panel
        JPanel score =new JPanel(null);
        score.setBounds(440,80,250,400);

        //players score
        JLabel player1=new JLabel("PLAYER X : ");
        player1.setBounds(20,50,110,25);
        player1.setFont(new Font("Arial", Font.BOLD, 16));
        score.add(player1);

        player1txt=new JButton();
        player1txt.setBounds(140,50,50,25);
        player1txt.setFont(new Font("Arial", Font.BOLD, 16));
        player1txt.setHorizontalAlignment(JButton.CENTER);
        player1txt.setFocusable(false);
        score.add(player1txt);

        JLabel player2=new JLabel("PLAYER O : ");
        player2.setBounds(20,140,110,25);
        player2.setFont(new Font("Arial", Font.BOLD, 16));
        score.add(player2);

        player2txt=new JButton();
        player2txt.setBounds(140,140,50,25);
        player2txt.setFont(new Font("Arial", Font.BOLD, 16));
        player2txt.setHorizontalAlignment(JButton.CENTER);
        player2txt.setFocusable(false);
        score.add(player2txt);

        //player status
        JLabel status=new JLabel("Status : ");
        status.setBounds(20,230,90,25);
        status.setFont(new Font("Arial", Font.BOLD, 16));
        score.add(status);

        statustxt=new JButton();
        statustxt.setBounds(90,230,150,25);
        statustxt.setFont(new Font("Arial", Font.BOLD, 16));
        statustxt.setHorizontalAlignment(JButton.CENTER);
        statustxt.setText("Player X's turn");
        statustxt.setFocusable(false);
        score.add(statustxt);

        //buttons
        rest=new JButton("Rest All");
        rest.setBounds(30,300,90,30);
        rest.setFont(new Font("Arial", Font.BOLD, 14));
        rest.setHorizontalAlignment(JButton.CENTER);
        rest.setFocusable(false);
        rest.setBackground(new Color(120,120,250));
        rest.setForeground(Color.white);
        rest.addActionListener(new ButtonClickListener());
        score.add(rest);

        exit=new JButton("Exit");
        exit.setBounds(150,300,90,30);
        exit.setFont(new Font("Arial", Font.BOLD, 14));
        exit.setHorizontalAlignment(JButton.CENTER);
        exit.setFocusable(false);
        exit.setBackground(new Color(120,120,250));
        exit.setForeground(Color.white);
        exit.addActionListener(new ButtonClickListener());
        score.add(exit);

        setSize(750, 550);
        setResizable(false);
        setVisible(true);
        setLocation(350,200);
        add(body);
        add(top);
        add(score);
        xTurn = true;
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            int buttonIndex = -1;
            {
                for (int i = 0; i < 9; i++) {
                    if (clickedButton == buttons[i]) {
                        buttonIndex = i;
                        break;
                    }
                }
                if (buttonIndex != -1) {
                    if (buttons[buttonIndex].getText()=="") {
                        if (xTurn) {
                            buttons[buttonIndex].setText("X");
                            buttons[buttonIndex].setForeground(Color.RED);
                            statustxt.setText("Player O's turn");
                        } else {
                            buttons[buttonIndex].setText("O");
                            buttons[buttonIndex].setForeground(Color.BLUE);
                            statustxt.setText("Player X's turn");
                        }
                        xTurn = !xTurn;

                        if (checkWinCondition()) {
                            String winner = xTurn ? "O" : "X";
                            if (winner=="O") {
                                num2++;
                                player2txt.setText(String.valueOf(num2));
                            }
                            else {
                                num1++;
                                player1txt.setText(String.valueOf(num1));
                            }
                            statustxt.setText("Player " + winner + " wins!");
                            disableAllButtons();
                        } else if (isBoardFull()) {
                            statustxt.setText("It's a draw!");
                            disableAllButtons();
                        }
                    }
                }
                if (clickedButton == rest) {
                    for (JButton button : buttons) {
                        button.setEnabled(true);
                        button.setText("");
                    }
                    xTurn=true;
                    statustxt.setText("Player X's turn");
                }
                if(clickedButton==exit)
                {
                    System.exit(0);
                }
            }
        }
        private void disableAllButtons() {
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
        }
    }

    private boolean checkWinCondition() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = buttons[i].getText();
        }

        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (board[i].equals(board[i + 1]) && board[i].equals(board[i + 2]) && !board[i].isEmpty()) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[i].equals(board[i + 3]) && board[i].equals(board[i + 6]) && !board[i].isEmpty()) {
                return true;
            }
        }

        // Check diagonals
        if (board[0].equals(board[4]) && board[0].equals(board[8]) && !board[0].isEmpty()) {
            return true;
        }
        if (board[2].equals(board[4]) && board[2].equals(board[6]) && !board[2].isEmpty()) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
       new TicTacToeGame();
    }
}
