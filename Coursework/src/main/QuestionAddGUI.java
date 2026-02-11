package main;

import java.awt.*;
import javax.swing.*;

public class QuestionAddGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                QuestionAddGUI frame = new QuestionAddGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public QuestionAddGUI() {
    	setBackground(Color.GRAY);
        setTitle("Add Question");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 550, 420);

        // Main panel
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JLabel lblTitle = new JLabel("Add Question", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(160, 10, 250, 30);
        contentPane.add(lblTitle);
        
        JLabel lblQuestion = new JLabel("Question:");
        lblQuestion.setFont(new Font("Arial", Font.PLAIN, 16));
        lblQuestion.setBounds(30, 30, 100, 25);
        contentPane.add(lblQuestion);

        JTextField txtQuestion = new JTextField();
        txtQuestion.setFont(new Font("Arial", Font.PLAIN, 14));
        txtQuestion.setBounds(30, 63, 480, 25);
        contentPane.add(txtQuestion);

        JLabel lblA = new JLabel("Option A:");
        lblA.setFont(new Font("Arial", Font.PLAIN, 16));
        lblA.setBounds(30, 100, 100, 25);
        contentPane.add(lblA);

        JTextField txtA = new JTextField();
        txtA.setFont(new Font("Arial", Font.PLAIN, 14));
        txtA.setBounds(30, 137, 200, 25);
        contentPane.add(txtA);

        JLabel lblB = new JLabel("Option B:");
        lblB.setFont(new Font("Arial", Font.PLAIN, 16));
        lblB.setBounds(310, 100, 100, 25);
        contentPane.add(lblB);

        JTextField txtB = new JTextField();
        txtB.setFont(new Font("Arial", Font.PLAIN, 14));
        txtB.setBounds(310, 137, 200, 25);
        contentPane.add(txtB);

        JLabel lblC = new JLabel("Option C:");
        lblC.setFont(new Font("Arial", Font.PLAIN, 16));
        lblC.setBounds(30, 174, 100, 25);
        contentPane.add(lblC);

        JTextField txtC = new JTextField();
        txtC.setFont(new Font("Arial", Font.PLAIN, 14));
        txtC.setBounds(30, 217, 200, 25);
        contentPane.add(txtC);

        JLabel lblD = new JLabel("Option D:");
        lblD.setFont(new Font("Arial", Font.PLAIN, 16));
        lblD.setBounds(310, 180, 100, 25);
        contentPane.add(lblD);

        JTextField txtD = new JTextField();
        txtD.setFont(new Font("Arial", Font.PLAIN, 14));
        txtD.setBounds(310, 217, 200, 25);
        contentPane.add(txtD);

        JLabel lblCorrect = new JLabel("Correct:");
        lblCorrect.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCorrect.setBounds(30, 260, 100, 25);
        contentPane.add(lblCorrect);

        JTextField txtCorrect = new JTextField();
        txtCorrect.setFont(new Font("Arial", Font.PLAIN, 14));
        txtCorrect.setBounds(100, 260, 80, 25);
        contentPane.add(txtCorrect);

        JLabel lblLevel = new JLabel("Difficulty:");
        lblLevel.setFont(new Font("Arial", Font.PLAIN, 16));
        lblLevel.setBounds(231, 260, 100, 25);
        contentPane.add(lblLevel);

        JComboBox<String> cmbLevel = new JComboBox<>();
        cmbLevel.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbLevel.addItem("Beginner");
        cmbLevel.addItem("Intermediate");
        cmbLevel.addItem("Advanced");
        cmbLevel.setBounds(310, 261, 160, 25);
        contentPane.add(cmbLevel);

        JButton btnSave = new JButton("Save");
        btnSave.setFont(new Font("Arial", Font.PLAIN, 16));
        btnSave.setBackground(Color.WHITE);
        btnSave.setBounds(30, 338, 100, 30);
        contentPane.add(btnSave);

        btnSave.addActionListener(e -> {
            String question = txtQuestion.getText();
            String a = txtA.getText();
            String b = txtB.getText();
            String c = txtC.getText();
            String d = txtD.getText();
            String correct = txtCorrect.getText().toUpperCase();
            String difficulty = cmbLevel.getSelectedItem().toString();

            if (question.isEmpty() || a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all question and option fields.",
                        "Missing Information",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!correct.matches("[A-D]")) {
                JOptionPane.showMessageDialog(this,
                        "Correct option must be A, B, C, or D!",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = QuestionDBManager.addQuestion(question, a, b, c, d, correct, difficulty);

            if (success) {
                JOptionPane.showMessageDialog(this, "Question Added Successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error Adding Question!");
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 16));
        btnBack.setBackground(Color.WHITE);
        btnBack.setBounds(142, 338, 100, 30);
        btnBack.addActionListener(e -> dispose());
        contentPane.add(btnBack);
    }
}
