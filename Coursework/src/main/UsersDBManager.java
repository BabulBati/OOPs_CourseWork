package main;

import java.sql.*;

/**
 * Manages user authentication and registration.
 */
public class UsersDBManager {

    /**
     * Registers a new player account.
     */
	public static int registerPlayer(String username, String password) {

	    String checkSql = "SELECT userID FROM Users WHERE username = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

	        checkStmt.setString(1, username);
	        ResultSet rs = checkStmt.executeQuery();

	        if (rs.next()) {
	            return -1; // Username already exists
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;
	    }

	    String insertSql = "INSERT INTO Users (username, password, role) VALUES (?, ?, 'player')";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {

	        pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        pstmt.executeUpdate();

	        ResultSet generatedKeys = pstmt.getGeneratedKeys();

	        if (generatedKeys.next()) {
	            return generatedKeys.getInt(1); // return new userID
	        }

	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	    }

	    return -1;
	}


    /**
     * Authenticates user.
     * Returns userID if successful, otherwise -1.
     */
    public static int login(String username, String password) {

        String sql = "SELECT userID FROM Users WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("userID"); // Login success
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return -1; // Login failed
    }

    /**
     * Gets role using userID.
     */
    public static String getRole(int userID) {

        String sql = "SELECT role FROM Users WHERE userID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }
}
