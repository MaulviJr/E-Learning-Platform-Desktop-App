/*
 * DAO class for Learner table
 */
package com.ecms.dbms.data;

import com.ecms.dbms.models.Learner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LearnerDAO {

    private Connection conn;

    // Constructor: accepts a connection object from DBConnection
    public LearnerDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE: Add new learner
    public boolean addLearner(Learner learner) {
        String sql = "INSERT INTO learner (Learner_ID, Name, Gender, DOB, Email, Contact_No, Registration_Date, Username, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, learner.getLearnerId());
            stmt.setString(2, learner.getName());
            stmt.setString(3, learner.getGender());
            stmt.setString(4, learner.getDob());
            stmt.setString(5, learner.getEmail());
            stmt.setString(6, learner.getContactNo());
            stmt.setString(7, learner.getRegistrationDate());
            stmt.setString(8, learner.getUsername());
            stmt.setString(9, learner.getPassword()); // Later replace with hashed password
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error adding learner: " + e.getMessage());
            return false;
        }
    }

    // READ: Get all learners
    public List<Learner> getAllLearners() {
        List<Learner> learners = new ArrayList<>();
        String sql = "SELECT * FROM learner";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Learner l = new Learner(
                        rs.getInt("Learner_ID"),
                        rs.getString("Name"),
                        rs.getString("Gender"),
                        rs.getString("DOB"),
                        rs.getString("Email"),
                        rs.getString("Contact_No"),
                        rs.getString("Registration_Date"),
                        rs.getString("Username"),
                        rs.getString("Password")
                );

                learners.add(l);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching learners: " + e.getMessage());
        }
        return learners;
    }

    // READ: Get a single learner by ID
    public Learner getLearnerById(int learnerId) {
        Learner learner = null;
        String sql = "SELECT * FROM learner WHERE Learner_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, learnerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                learner = new Learner(
                        rs.getInt("Learner_ID"),
                        rs.getString("Name"),
                        rs.getString("Gender"),
                        rs.getString("DOB"),
                        rs.getString("Email"),
                        rs.getString("Contact_No"),
                        rs.getString("Registration_Date"),
                        rs.getString("Username"),
                        rs.getString("Password")
                );

            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching learner by ID: " + e.getMessage());
        }
        return learner;
    }

    // UPDATE: Modify learner info
    public boolean updateLearner(Learner learner) {
        String sql = "UPDATE learner SET Name=?, Gender=?, DOB=?, Email=?, Contact_No=?, Registration_Date=? WHERE Learner_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, learner.getName());
            stmt.setString(2, learner.getGender());
            stmt.setString(3, learner.getDob());
            stmt.setString(4, learner.getEmail());
            stmt.setString(5, learner.getContactNo());
            stmt.setString(6, learner.getRegistrationDate());
            stmt.setInt(7, learner.getLearnerId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error updating learner: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Remove learner by ID
    public boolean deleteLearner(int learnerId) {
        String sql = "DELETE FROM learner WHERE Learner_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, learnerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error deleting learner: " + e.getMessage());
            return false;
        }
    }
    
    // LOGIN: Validate learner credentials
public Learner loginLearner(String username, String password) {
    Learner learner = null;
    String sql = "SELECT * FROM learner WHERE Username = ? AND Password = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        stmt.setString(2, password); // Later hash password before checking
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            learner = new Learner(
                rs.getInt("Learner_ID"),
                rs.getString("Name"),
                rs.getString("Gender"),
                rs.getString("DOB"),
                rs.getString("Email"),
                rs.getString("Contact_No"),
                rs.getString("Registration_Date"),
                rs.getString("Username"),
                rs.getString("Password")
            );
        }
    } catch (SQLException e) {
        System.out.println(" Error during login: " + e.getMessage());
    }
    return learner;
}

// CHECK: Verify if username already exists
public boolean usernameExists(String username) {
    String sql = "SELECT COUNT(*) FROM instructor WHERE Username = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        System.out.println("Error checking username: " + e.getMessage());
    }
    return false;
}


}
