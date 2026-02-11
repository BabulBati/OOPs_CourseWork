package main;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class QuizResultGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private int playerID, competitorID;

    public QuizResultGUI(int playerID, int competitorID, String difficulty, int correct, int total) {
    	setBackground(Color.GRAY);
        this.playerID = playerID;
        this.competitorID = competitorID;

        setTitle("Quiz Result");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 350);

        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Quiz Result");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(150, 10, 200, 30);
        contentPane.add(lblTitle);

        double percentage = (correct * 100.0) / total;

        JLabel lblDifficulty = new JLabel("Difficulty Level: " + difficulty);
        lblDifficulty.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDifficulty.setBounds(40, 100, 300, 25);
        contentPane.add(lblDifficulty);

        JLabel lblTotal = new JLabel("Total Questions: " + total);
        lblTotal.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTotal.setBounds(40, 137, 150, 25);
        contentPane.add(lblTotal);

        JLabel lblCorrect = new JLabel("Correct Answers: " + correct);
        lblCorrect.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCorrect.setBounds(271, 137, 150, 25);
        contentPane.add(lblCorrect);

        JLabel lblScore = new JLabel(String.format("Score: %.2f%%", percentage));
        lblScore.setFont(new Font("Arial", Font.BOLD, 16));
        lblScore.setBounds(40, 52, 300, 30);
        contentPane.add(lblScore);

        JLabel lblMessage = new JLabel(getMessage(percentage));
        lblMessage.setFont(new Font("Arial", Font.ITALIC, 14));
        lblMessage.setBounds(40, 177, 350, 25);
        contentPane.add(lblMessage);

        JButton btnBack = new JButton("Back to Dashboard");
        btnBack.setBounds(40, 214, 180, 35);
        btnBack.addActionListener(e -> {
            new PlayerDashboardGUI(playerID, competitorID).setVisible(true);
            dispose();
        });
        contentPane.add(btnBack);
    }

    private String getMessage(double score) {
        if (score >= 80) return "Excellent";
        if (score >= 60) return "Good";
        if (score >= 40) return "Better";
        return "Try again!";
    }
}
