package main;

import java.sql.*;

/**
 * Manages user authentication and registration.
 */
public class UsersDBManager {
	
	/**
     * Registers a new player account.
     */
    public static boolean registerPlayer(String username, String password) {
        String checkSql = "SELECT userID FROM Users WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String sql = "INSERT INTO Users (username, password, role) VALUES (?, ?, 'player')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Authenticates a user and returns role.
     */
    public static String login(String username, String password) {
        String sql = "SELECT role, userID FROM Users WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves user ID using username.
     */
    public static int getUserID(String username) {
        String sql = "SELECT userID FROM Users WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt("userID");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }
}
