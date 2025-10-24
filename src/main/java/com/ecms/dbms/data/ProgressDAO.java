/*
 * DAO class for Progress table
 */
package com.ecms.dbms.data;

import com.ecms.dbms.models.Progress;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgressDAO {

    private Connection conn;

    // Constructor
    public ProgressDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE: Add a new progress record
    public boolean addProgress(Progress progress) {
        String sql = "INSERT INTO progress (Progress_ID, Learner_ID, Course_ID, Completed_Modules, Total_Modules, Progress_Percentage) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, progress.getProgressId());
            stmt.setInt(2, progress.getLearnerId());
            stmt.setInt(3, progress.getCourseId());
            stmt.setInt(4, progress.getCompletedModules());
            stmt.setInt(5, progress.getTotalModules());
            stmt.setDouble(6, progress.getProgressPercentage());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error adding progress: " + e.getMessage());
            return false;
        }
    }

    // READ: Get all progress records
    public List<Progress> getAllProgress() {
        List<Progress> list = new ArrayList<>();
        String sql = "SELECT * FROM progress";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Progress p = new Progress(
                        rs.getInt("Progress_ID"),
                        rs.getInt("Learner_ID"),
                        rs.getInt("Course_ID"),
                        rs.getInt("Completed_Modules"),
                        rs.getInt("Total_Modules"),
                        rs.getDouble("Progress_Percentage")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching progress records: " + e.getMessage());
        }
        return list;
    }

    // READ: Get progress by learner
    public List<Progress> getProgressByLearner(int learnerId) {
        List<Progress> list = new ArrayList<>();
        String sql = "SELECT * FROM progress WHERE Learner_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, learnerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Progress p = new Progress(
                        rs.getInt("Progress_ID"),
                        rs.getInt("Learner_ID"),
                        rs.getInt("Course_ID"),
                        rs.getInt("Completed_Modules"),
                        rs.getInt("Total_Modules"),
                        rs.getDouble("Progress_Percentage")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching progress by learner: " + e.getMessage());
        }
        return list;
    }

    // READ: Get progress by course
    public List<Progress> getProgressByCourse(int courseId) {
        List<Progress> list = new ArrayList<>();
        String sql = "SELECT * FROM progress WHERE Course_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Progress p = new Progress(
                        rs.getInt("Progress_ID"),
                        rs.getInt("Learner_ID"),
                        rs.getInt("Course_ID"),
                        rs.getInt("Completed_Modules"),
                        rs.getInt("Total_Modules"),
                        rs.getDouble("Progress_Percentage")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching progress by course: " + e.getMessage());
        }
        return list;
    }

    // UPDATE: Update progress (modules or percentage)
    public boolean updateProgress(Progress progress) {
        String sql = "UPDATE progress SET Learner_ID=?, Course_ID=?, Completed_Modules=?, Total_Modules=?, Progress_Percentage=? WHERE Progress_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, progress.getLearnerId());
            stmt.setInt(2, progress.getCourseId());
            stmt.setInt(3, progress.getCompletedModules());
            stmt.setInt(4, progress.getTotalModules());
            stmt.setDouble(5, progress.getProgressPercentage());
            stmt.setInt(6, progress.getProgressId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error updating progress: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Remove progress record
    public boolean deleteProgress(int progressId) {
        String sql = "DELETE FROM progress WHERE Progress_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, progressId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error deleting progress: " + e.getMessage());
            return false;
        }
    }
}
