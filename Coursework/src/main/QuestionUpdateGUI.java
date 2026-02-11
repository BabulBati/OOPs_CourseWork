package main;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class QuestionUpdateGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtQuestion, txtA, txtB, txtC, txtD, txtCorrect;
    private JComboBox<String> cmbLevel;
    private int questionID;
    private QuestionsViewGUI parentFrame;

    public QuestionUpdateGUI(int questionID, QuestionsViewGUI parentFrame) {
        this(questionID);
        this.parentFrame = parentFrame;
    }

    public QuestionUpdateGUI(int questionID) {
        this.questionID = questionID;

        setTitle("Update Question");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 550, 420);

        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Updat Question", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(140, 10, 300, 30);
        contentPane.add(lblTitle);

        JLabel lblQuestion = new JLabel("Question:");
        lblQuestion.setFont(new Font("Arial", Font.PLAIN, 16));
        lblQuestion.setBounds(30, 68, 100, 25);
        contentPane.add(lblQuestion);

        txtQuestion = new JTextField();
        txtQuestion.setFont(new Font("Arial", Font.PLAIN, 14));
        txtQuestion.setBounds(30, 108, 492, 25);
        contentPane.add(txtQuestion);

        JLabel lblA = new JLabel("Option A:");
        lblA.setFont(new Font("Arial", Font.PLAIN, 16));
        lblA.setBounds(30, 142, 100, 25);
        contentPane.add(lblA);

        txtA = new JTextField();
        txtA.setFont(new Font("Arial", Font.PLAIN, 14));
        txtA.setBounds(30, 179, 200, 25);
        contentPane.add(txtA);

        JLabel lblB = new JLabel("Option B:");
        lblB.setFont(new Font("Arial", Font.PLAIN, 16));
        lblB.setBounds(322, 142, 100, 25);
        contentPane.add(lblB);

        txtB = new JTextField();
        txtB.setFont(new Font("Arial", Font.PLAIN, 14));
        txtB.setBounds(322, 179, 200, 25);
        contentPane.add(txtB);

        JLabel lblC = new JLabel("Option C:");
        lblC.setFont(new Font("Arial", Font.PLAIN, 16));
        lblC.setBounds(30, 216, 100, 25);
        contentPane.add(lblC);

        txtC = new JTextField();
        txtC.setFont(new Font("Arial", Font.PLAIN, 14));
        txtC.setBounds(30, 253, 200, 25);
        contentPane.add(txtC);

        JLabel lblD = new JLabel("Option D:");
        lblD.setFont(new Font("Arial", Font.PLAIN, 16));
        lblD.setBounds(322, 216, 100, 25);
        contentPane.add(lblD);

        txtD = new JTextField();
        txtD.setFont(new Font("Arial", Font.PLAIN, 14));
        txtD.setBounds(322, 253, 200, 25);
        contentPane.add(txtD);

        JLabel lblCorrect = new JLabel("Correct:");
        lblCorrect.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCorrect.setBounds(30, 305, 100, 25);
        contentPane.add(lblCorrect);

        txtCorrect = new JTextField();
        txtCorrect.setFont(new Font("Arial", Font.PLAIN, 14));
        txtCorrect.setBounds(101, 305, 80, 25);
        contentPane.add(txtCorrect);

        JLabel lblLevel = new JLabel("Difficulty:");
        lblLevel.setFont(new Font("Arial", Font.PLAIN, 16));
        lblLevel.setBounds(203, 305, 100, 25);
        contentPane.add(lblLevel);

        cmbLevel = new JComboBox<>();
        cmbLevel.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbLevel.addItem("Beginner");
        cmbLevel.addItem("Intermediate");
        cmbLevel.addItem("Advanced");
        cmbLevel.setBounds(290, 306, 160, 25);
        contentPane.add(cmbLevel);

        JButton btnSave = new JButton("Update");
        btnSave.setFont(new Font("Arial", Font.PLAIN, 16));
        btnSave.setBackground(Color.WHITE);
        btnSave.setBounds(17, 345, 100, 30);
        btnSave.addActionListener(e -> updateQuestion());
        contentPane.add(btnSave);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 16));
        btnBack.setBackground(Color.WHITE);
        btnBack.setBounds(130, 345, 100, 30);
        btnBack.addActionListener(e -> dispose());
        contentPane.add(btnBack);

        loadQuestionDetails();
    }

    private void loadQuestionDetails() {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Questions WHERE questionID = ?")) {
            pstmt.setInt(1, questionID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                txtQuestion.setText(rs.getString("questionText"));
                txtA.setText(rs.getString("optionA"));
                txtB.setText(rs.getString("optionB"));
                txtC.setText(rs.getString("optionC"));
                txtD.setText(rs.getString("optionD"));
                txtCorrect.setText(rs.getString("correctOption"));
                cmbLevel.setSelectedItem(rs.getString("difficulty"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading question: " + e.getMessage());
        }
    }

    private void updateQuestion() {
        if (txtQuestion.getText().isEmpty() || txtA.getText().isEmpty() || txtB.getText().isEmpty() ||
            txtC.getText().isEmpty() || txtD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Missing Information", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String correct = txtCorrect.getText().toUpperCase();
        if (!correct.matches("[A-D]")) {
            JOptionPane.showMessageDialog(this, "Correct option must be A, B, C, or D!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE Questions SET questionText=?, optionA=?, optionB=?, optionC=?, optionD=?, correctOption=?, difficulty=? WHERE questionID=?")) {

            pstmt.setString(1, txtQuestion.getText());
            pstmt.setString(2, txtA.getText());
            pstmt.setString(3, txtB.getText());
            pstmt.setString(4, txtC.getText());
            pstmt.setString(5, txtD.getText());
            pstmt.setString(6, correct);
            pstmt.setString(7, cmbLevel.getSelectedItem().toString());
            pstmt.setInt(8, questionID);

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Question updated successfully!");
                if (parentFrame != null) {
                    parentFrame.loadQuestions();
                }
                dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating question: " + e.getMessage());
        }
    }
}
