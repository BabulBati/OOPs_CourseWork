package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Handles database operations related to quiz questions.
 */
public class QuestionDBManager {
    
	/**
     * Adds a new question to the database.
     *
     * @return true if question is added successfully
     */
    public static boolean addQuestion(
            String questionText,
            String optionA,
            String optionB,
            String optionC,
            String optionD,
            String correctOption,
            String difficulty) {

    	String sql = "INSERT INTO Questions (questionText, optionA, optionB, optionC, optionD, correctOption, difficulty) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, questionText);
            pstmt.setString(2, optionA);
            pstmt.setString(3, optionB);
            pstmt.setString(4, optionC);
            pstmt.setString(5, optionD);
            pstmt.setString(6, correctOption);
            pstmt.setString(7, difficulty);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error adding question: " + e.getMessage());
            return false;
        }
    }
}
