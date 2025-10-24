/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * DAO class for Assessment table
 */
package com.ecms.dbms.data;

import com.ecms.dbms.models.Assessment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssessmentDAO {
    
    private Connection conn;

    // Constructor
    public AssessmentDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE: Add a new assessment
    public boolean addAssessment(Assessment assessment) {
        String sql = "INSERT INTO assessment (Assessment_ID, Course_ID, Assessment_Type, Max_Marks, Date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assessment.getAssessmentId());
            stmt.setInt(2, assessment.getCourseId());
            stmt.setString(3, assessment.getAssessmentType());
            stmt.setInt(4, assessment.getMaxMarks());
            stmt.setString(5, assessment.getDate());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error adding assessment: " + e.getMessage());
            return false;
        }
    }

    // READ: Fetch all assessments
    public List<Assessment> getAllAssessments() {
        List<Assessment> list = new ArrayList<>();
        String sql = "SELECT * FROM assessment";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Assessment a = new Assessment(
                        rs.getInt("Assessment_ID"),
                        rs.getInt("Course_ID"),
                        rs.getString("Assessment_Type"),
                        rs.getInt("Max_Marks"),
                        rs.getString("Date")
                );
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching assessments: " + e.getMessage());
        }
        return list;
    }

    // READ: Get assessments for a specific course
    public List<Assessment> getAssessmentsByCourse(int courseId) {
        List<Assessment> list = new ArrayList<>();
        String sql = "SELECT * FROM assessment WHERE Course_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Assessment a = new Assessment(
                        rs.getInt("Assessment_ID"),
                        rs.getInt("Course_ID"),
                        rs.getString("Assessment_Type"),
                        rs.getInt("Max_Marks"),
                        rs.getString("Date")
                );
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching assessments by course: " + e.getMessage());
        }
        return list;
    }

    // UPDATE: Update assessment details
    public boolean updateAssessment(Assessment assessment) {
        String sql = "UPDATE assessment SET Course_ID=?, Assessment_Type=?, Max_Marks=?, Date=? WHERE Assessment_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assessment.getCourseId());
            stmt.setString(2, assessment.getAssessmentType());
            stmt.setInt(3, assessment.getMaxMarks());
            stmt.setString(4, assessment.getDate());
            stmt.setInt(5, assessment.getAssessmentId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error updating assessment: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Remove an assessment
    public boolean deleteAssessment(int assessmentId) {
        String sql = "DELETE FROM assessment WHERE Assessment_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assessmentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error deleting assessment: " + e.getMessage());
            return false;
        }
    }
}
