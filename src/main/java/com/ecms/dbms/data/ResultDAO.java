/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * DAO class for Result table
 */
package com.ecms.dbms.data;

import com.ecms.dbms.models.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {

    private Connection conn;

    // Constructor
    public ResultDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE: Add new result entry
    public boolean addResult(Result result) {
        String sql = "INSERT INTO result (Result_ID, Learner_ID, Assessment_ID, Marks_Obtained, Grade) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, result.getResultId());
            stmt.setInt(2, result.getLearnerId());
            stmt.setInt(3, result.getAssessmentId());
            stmt.setInt(4, (int) result.getMarksObtained());
            stmt.setString(5, result.getGrade());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error adding result: " + e.getMessage());
            return false;
        }
    }

    // READ: Get all results
    public List<Result> getAllResults() {
        List<Result> list = new ArrayList<>();
        String sql = "SELECT * FROM result";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Result r = new Result(
                        rs.getInt("Result_ID"),
                        rs.getInt("Learner_ID"),
                        rs.getInt("Assessment_ID"),
                        rs.getInt("Marks_Obtained"),
                        rs.getString("Grade")
                );
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching results: " + e.getMessage());
        }
        return list;
    }

    // READ: Get results for a specific learner
    public List<Result> getResultsByLearner(int learnerId) {
        List<Result> list = new ArrayList<>();
        String sql = "SELECT * FROM result WHERE Learner_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, learnerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Result r = new Result(
                        rs.getInt("Result_ID"),
                        rs.getInt("Learner_ID"),
                        rs.getInt("Assessment_ID"),
                        rs.getInt("Marks_Obtained"),
                        rs.getString("Grade")
                );
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching results by learner: " + e.getMessage());
        }
        return list;
    }

    // READ: Get results by assessment
    public List<Result> getResultsByAssessment(int assessmentId) {
        List<Result> list = new ArrayList<>();
        String sql = "SELECT * FROM result WHERE Assessment_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assessmentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Result r = new Result(
                        rs.getInt("Result_ID"),
                        rs.getInt("Learner_ID"),
                        rs.getInt("Assessment_ID"),
                        rs.getInt("Marks_Obtained"),
                        rs.getString("Grade")
                );
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching results by assessment: " + e.getMessage());
        }
        return list;
    }

    // UPDATE: Update marks or grade
    public boolean updateResult(Result result) {
        String sql = "UPDATE result SET Learner_ID=?, Assessment_ID=?, Marks_Obtained=?, Grade=? WHERE Result_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, result.getLearnerId());
            stmt.setInt(2, result.getAssessmentId());
            stmt.setInt(3, (int) result.getMarksObtained());
            stmt.setString(4, result.getGrade());
            stmt.setInt(5, result.getResultId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error updating result: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Remove a result entry
    public boolean deleteResult(int resultId) {
        String sql = "DELETE FROM result WHERE Result_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, resultId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error deleting result: " + e.getMessage());
            return false;
        }
    }
}
