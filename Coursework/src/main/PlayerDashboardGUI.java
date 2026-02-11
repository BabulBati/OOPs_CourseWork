package main;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PlayerDashboardGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private int userID, competitorID;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new PlayerDashboardGUI(1, CompetitorDBManager.getCompetitorID(1)).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PlayerDashboardGUI(int playerID, int competitorID) {
    	setBackground(Color.GRAY);
        this.userID = playerID;
        this.competitorID = competitorID;

        setTitle("Player Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);

        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Welcome Player");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitle.setBounds(100, 10, 300, 30);
        contentPane.add(lblTitle);

        JButton btnStartQuiz = new JButton("Start Quiz");
        btnStartQuiz.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnStartQuiz.setBounds(150, 80, 200, 30);
        btnStartQuiz.addActionListener(e -> {
            String[] options = {"Beginner", "Intermediate", "Advanced"};
            String difficulty = (String) JOptionPane.showInputDialog(
                    PlayerDashboardGUI.this,
                    "Select Difficulty Level:",
                    "Quiz Difficulty",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (difficulty != null) {
            	new QuizGUI(userID, competitorID, difficulty).setVisible(true);
            }
        });
        contentPane.add(btnStartQuiz);

        JButton btnViewReports = new JButton("Reports");
        btnViewReports.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnViewReports.setBounds(150, 130, 200, 30);
        btnViewReports.addActionListener(e -> new PlayerReportsGUI(competitorID).setVisible(true));
        contentPane.add(btnViewReports);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnLogout.setBounds(150, 180, 200, 30);
        btnLogout.addActionListener(e -> {
            new LoginRegisterGUI().setVisible(true);
            dispose();
        });
        contentPane.add(btnLogout);
    }
}
