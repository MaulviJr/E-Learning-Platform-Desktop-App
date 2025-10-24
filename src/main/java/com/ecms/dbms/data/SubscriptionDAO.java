/*
 * DAO class for Subscription table
 */
package com.ecms.dbms.data;

import com.ecms.dbms.models.EnrolledLearner;
import com.ecms.dbms.models.Subscription;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAO {

    private Connection conn;

    // Constructor
    public SubscriptionDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE: Add new subscription (learner enrolls in course)
    public boolean addSubscription(Subscription sub) {
        String sql = "INSERT INTO subscription (Subscription_ID, Learner_ID, Course_ID, Start_Date, Expiry_Date, Payment_Status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sub.getSubscriptionId());
            stmt.setInt(2, sub.getLearnerId());
            stmt.setInt(3, sub.getCourseId());
            stmt.setString(4, sub.getStartDate());
            stmt.setString(5, sub.getExpiryDate());
            stmt.setString(6, sub.getPaymentStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error adding subscription: " + e.getMessage());
            return false;
        }
    }

    // READ: Get all subscriptions
    public List<Subscription> getAllSubscriptions() {
        List<Subscription> list = new ArrayList<>();
        String sql = "SELECT * FROM subscription";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Subscription sub = new Subscription(
                        rs.getInt("Subscription_ID"),
                        rs.getInt("Learner_ID"),
                        rs.getInt("Course_ID"),
                        rs.getString("Start_Date"),
                        rs.getString("Expiry_Date"),
                        rs.getString("Payment_Status")
                );
                list.add(sub);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching subscriptions: " + e.getMessage());
        }
        return list;
    }

    // READ: Get subscriptions by learner ID (for Learner Dashboard)
    public List<Subscription> getSubscriptionsByLearnerId(int learnerId) {
        List<Subscription> list = new ArrayList<>();
        String sql = "SELECT * FROM subscription WHERE Learner_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, learnerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Subscription sub = new Subscription(
                        rs.getInt("Subscription_ID"),
                        rs.getInt("Learner_ID"),
                        rs.getInt("Course_ID"),
                        rs.getString("Start_Date"),
                        rs.getString("Expiry_Date"),
                        rs.getString("Payment_Status")
                );
                list.add(sub);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching learner subscriptions: " + e.getMessage());
        }
        return list;
    }

    // READ: Get subscriptions by course ID (for Instructor Dashboard)
    public List<Subscription> getSubscriptionsByCourseId(int courseId) {
        List<Subscription> list = new ArrayList<>();
        String sql = "SELECT * FROM subscription WHERE Course_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Subscription sub = new Subscription(
                        rs.getInt("Subscription_ID"),
                        rs.getInt("Learner_ID"),
                        rs.getInt("Course_ID"),
                        rs.getString("Start_Date"),
                        rs.getString("Expiry_Date"),
                        rs.getString("Payment_Status")
                );
                list.add(sub);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching course subscriptions: " + e.getMessage());
        }
        return list;
    }

    public List<EnrolledLearner> getLearnersByInstructorId(int instructorId) {
    List<EnrolledLearner> list = new ArrayList<>();
String sql =
    "SELECT l.Name AS LearnerName, " +
    "c.Course_Name AS CourseName, " +
    "i.Name AS InstructorName " +
    "FROM subscription s " +
    "JOIN learner l ON s.Learner_ID = l.Learner_ID " +
    "JOIN course c ON s.Course_ID = c.Course_ID " +
    "JOIN instructor i ON c.Instructor_ID = i.Instructor_ID " +
    "WHERE i.Instructor_ID = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, instructorId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(new EnrolledLearner(
                rs.getString("LearnerName"),
                rs.getString("CourseName"),
                rs.getString("InstructorName")
            ));
        }
    } catch (SQLException e) {
        System.out.println("Error fetching learners: " + e.getMessage());
    }

    return list;
}

    
    // UPDATE: Modify subscription info (e.g. update payment status)
    public boolean updateSubscription(Subscription sub) {
        String sql = "UPDATE subscription SET Learner_ID=?, Course_ID=?, Start_Date=?, Expiry_Date=?, Payment_Status=? WHERE Subscription_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sub.getLearnerId());
            stmt.setInt(2, sub.getCourseId());
            stmt.setString(3, sub.getStartDate());
            stmt.setString(4, sub.getExpiryDate());
            stmt.setString(5, sub.getPaymentStatus());
            stmt.setInt(6, sub.getSubscriptionId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error updating subscription: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Remove subscription
    public boolean deleteSubscription(int subscriptionId) {
        String sql = "DELETE FROM subscription WHERE Subscription_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error deleting subscription: " + e.getMessage());
            return false;
        }
    }
}
