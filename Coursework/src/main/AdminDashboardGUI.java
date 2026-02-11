package main;

import java.awt.*;
import javax.swing.*;

public class AdminDashboardGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminDashboardGUI frame = new AdminDashboardGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AdminDashboardGUI() {
    	setBackground(Color.GRAY);
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);

        
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(50, 20, 400, 40);
        contentPane.add(lblTitle);

        JButton btnAddQuestion = new JButton("Add Question");
        btnAddQuestion.setFont(new Font("Arial", Font.PLAIN, 16));
        btnAddQuestion.setBackground(Color.WHITE);
        btnAddQuestion.setBounds(16, 107, 150, 40);
        btnAddQuestion.addActionListener(e -> new QuestionAddGUI().setVisible(true));
        contentPane.add(btnAddQuestion);

        JButton btnViewQuestions = new JButton("View Questions");
        btnViewQuestions.setFont(new Font("Arial", Font.PLAIN, 16));
        btnViewQuestions.setBackground(Color.WHITE);
        btnViewQuestions.setBounds(178, 107, 150, 40);
        btnViewQuestions.addActionListener(e -> new QuestionsViewGUI().setVisible(true));
        contentPane.add(btnViewQuestions);

        JButton btnViewReports = new JButton("View Reports");
        btnViewReports.setFont(new Font("Arial", Font.PLAIN, 16));
        btnViewReports.setBackground(Color.WHITE);
        btnViewReports.setBounds(344, 107, 150, 40);
        btnViewReports.addActionListener(e -> new AdminReportsGUI().setVisible(true));
        contentPane.add(btnViewReports);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 16));
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setBounds(161, 174, 200, 40);
        btnLogout.addActionListener(e -> {
            new LoginRegisterGUI().setVisible(true);
            dispose();
        });
        contentPane.add(btnLogout);
    }
}
