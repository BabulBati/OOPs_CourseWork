package main;

import java.sql.*;

/**
 * Handles all database operations related to competitors and their scores.
 */
public class CompetitorDBManager {

    /**
     * Adds a new competitor record to the database.
     *
     * @param userID    linked user ID
     * @param firstName competitor first name
     * @param lastName  competitor last name
     * @param age       competitor age
     */
    public static void addCompetitor(int userID, String firstName, String lastName, int age) {
        String sql = "INSERT INTO competitors (userID, firstName, lastName, age) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userID);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setInt(4, age);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves competitor ID using user ID.
     *
     * @param userID user identifier
     * @return competitor ID or -1 if not found
     */
    public static int getCompetitorID(int userID) {
        String sql = "SELECT competitorID FROM competitors WHERE userID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt("competitorID");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Adds a quiz score for a competitor.
     * If score record exists, it fills the next available score column.
     *
     * @param competitorID competitor identifier
     * @param level        difficulty level
     * @param score        quiz score
     * @return true if score added successfully
     */
    public static boolean addQuizScore(int competitorID, String level, int score) {

        if (competitorID <= 0) return false;

        String selectSql = "SELECT score1, score2, score3, score4, score5 " +
                           "FROM score WHERE competitorID = ? AND level = ?";

        String insertSql = "INSERT INTO score (competitorID, level, score1) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            selectStmt.setInt(1, competitorID);
            selectStmt.setString(2, level);
            ResultSet rs = selectStmt.executeQuery();

            // If no score record exists, create a new one
            if (!rs.next()) {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, competitorID);
                    insertStmt.setString(2, level);
                    insertStmt.setInt(3, score);
                    insertStmt.executeUpdate();
                    return true;
                }
            }

            // Find first empty score column and update it
            for (int i = 1; i <= 5; i++) {
                int val = rs.getInt("score" + i);
                if (rs.wasNull()) {
                    String updateSql = "UPDATE score SET score" + i +
                            " = ? WHERE competitorID = ? AND level = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, score);
                        updateStmt.setInt(2, competitorID);
                        updateStmt.setString(3, level);
                        updateStmt.executeUpdate();
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
