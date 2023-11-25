package com.example.mathchallenge.Maze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MazeRecord {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mathchallengedb";
    private static final String USER = "root";
    private static final String PASS = "Alzahrani@500";

    public static void main(String[] args) {
        // Here we call a method
        new MazeRecord().recordMazeCompletion(1); // change it with user ID
    }

    public void recordMazeCompletion(int userId) {
        String sql = "UPDATE mathchallengedb.reports SET TotalScore = TotalScore + 1 WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User stats updated successfully for user ID: " + userId);
            } else {
                System.out.println("No update made. User ID might not exist: " + userId);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            // We can use any exceptions here.
        }
    }

    // remaining class....etc
}
