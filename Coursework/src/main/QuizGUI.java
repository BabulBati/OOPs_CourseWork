package main;

import java.awt.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

public class QuizGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblQuestion;
    private JButton[] optionButtons;
    private int currentQuestionIndex = 0;
    private List<Question> questions;
    private int score = 0;

    private int playerID, competitorID;
    private String difficulty;

    public QuizGUI(int playerID, int competitorID, String difficulty) {
    	setBackground(Color.GRAY);
        this.playerID = playerID;
        this.competitorID = competitorID;
        this.difficulty = difficulty;

        setTitle("Quiz - " + difficulty);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lblQuestion = new JLabel();
        lblQuestion.setFont(new Font("Arial", Font.BOLD, 16));
        lblQuestion.setBounds(30, 20, 520, 60);
        contentPane.add(lblQuestion);

        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            JButton btn = new JButton();
            btn.setBounds(50, 100 + i*60, 500, 50);
            final int index = i;
            btn.addActionListener(e -> checkAnswer(index));
            contentPane.add(btn);
            optionButtons[i] = btn;
        }

        loadQuestions();
        displayQuestion();
    }

    private void loadQuestions() {
        questions = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE difficulty = ? ORDER BY RAND() LIMIT 10";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, difficulty);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                questions.add(new Question(
                        rs.getInt("questionID"),
                        rs.getString("questionText"),
                        rs.getString("optionA"),
                        rs.getString("optionB"),
                        rs.getString("optionC"),
                        rs.getString("optionD"),
                        rs.getString("correctOption")
                ));
            }
            
            java.util.Collections.shuffle(questions);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            finishQuiz();
            return;
        }

        Question q = questions.get(currentQuestionIndex);
        lblQuestion.setText("<html>Q" + (currentQuestionIndex+1) + ": " + q.getQuestionText() + "</html>");
        optionButtons[0].setText(q.getOptionA());
        optionButtons[1].setText(q.getOptionB());
        optionButtons[2].setText(q.getOptionC());
        optionButtons[3].setText(q.getOptionD());
    }

    private void checkAnswer(int index) {
        Question q = questions.get(currentQuestionIndex);
        String chosen = "ABCD".charAt(index)+"";
        if (chosen.equalsIgnoreCase(q.getCorrectOption())) score++;
        currentQuestionIndex++;
        displayQuestion();
    }

    private void finishQuiz() {
        boolean saved = CompetitorDBManager.addQuizScore(competitorID, difficulty, score);

        if (!saved) {
            JOptionPane.showMessageDialog(this,"You already used 5 attempts.");
        }

        new QuizResultGUI(playerID, competitorID, difficulty, score, questions.size()).setVisible(true);
        dispose();
    }
}
