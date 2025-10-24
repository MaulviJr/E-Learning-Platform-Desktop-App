/*
 * DAO class for Instructor table
 */
package com.ecms.dbms.data;

import com.ecms.dbms.models.Instructor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {

    private Connection conn;

    // Constructor takes a database connection
    public InstructorDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE: Add new instructor
    public boolean addInstructor(Instructor instructor) {
        String sql = "INSERT INTO instructor (Instructor_ID, Name, Qualification, Specialization, Email, Contact_No, Join_Date, Username, Password) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, instructor.getInstructorId());
            stmt.setString(2, instructor.getName());
            stmt.setString(3, instructor.getQualification());
            stmt.setString(4, instructor.getSpecialization());
            stmt.setString(5, instructor.getEmail());
            stmt.setString(6, instructor.getContactNo());
            stmt.setString(7, instructor.getJoinDate());
            stmt.setString(8, instructor.getUserName());
            stmt.setString(9, instructor.getPassword());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding instructor: " + e.getMessage());
            return false;
        }
    }

    // READ: Get all instructors
    public List<Instructor> getAllInstructors() {
        List<Instructor> list = new ArrayList<>();
        String sql = "SELECT * FROM instructor";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Instructor i = new Instructor(
                        rs.getInt("Instructor_ID"),
                        rs.getString("Name"),
                        rs.getString("Qualification"),
                        rs.getString("Specialization"),
                        rs.getString("Email"),
                        rs.getString("Contact_No"),
                        rs.getString("Join_Date"),
                        rs.getString("Username"),
                        rs.getString("Password")
                );
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching instructors: " + e.getMessage());
        }
        return list;
    }

    // UPDATE: Modify instructor info
    public boolean updateInstructor(Instructor instructor) {
        String sql = "UPDATE instructor SET Name=?, Qualification=?, Specialization=?, Email=?, Contact_No=?, Join_Date=? WHERE Instructor_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, instructor.getName());
            stmt.setString(2, instructor.getQualification());
            stmt.setString(3, instructor.getSpecialization());
            stmt.setString(4, instructor.getEmail());
            stmt.setString(5, instructor.getContactNo());
            stmt.setString(6, instructor.getJoinDate());
            stmt.setInt(7, instructor.getInstructorId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating instructor: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Remove instructor by ID
    public boolean deleteInstructor(int instructorId) {
        String sql = "DELETE FROM instructor WHERE Instructor_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, instructorId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting instructor: " + e.getMessage());
            return false;
        }
    }
    
    // LOGIN: Validate instructor credentials
public Instructor loginInstructor(String username, String password) {
    Instructor instructor = null;
    String sql = "SELECT * FROM instructor WHERE Username = ? AND Password = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        stmt.setString(2, password);  // (Later hash this before comparing)
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            instructor = new Instructor(
                rs.getInt("Instructor_ID"),
                rs.getString("Name"),
                rs.getString("Qualification"),
                rs.getString("Specialization"),
                rs.getString("Email"),
                rs.getString("Contact_No"),
                rs.getString("Join_Date"),
                rs.getString("Username"),
                rs.getString("Password")
            );
        }
    } catch (SQLException e) {
        System.out.println("Error during login: " + e.getMessage());
    }
    return instructor;
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
