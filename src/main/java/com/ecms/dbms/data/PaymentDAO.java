/*
 * DAO class for Payment table
 */
package com.ecms.dbms.data;

import com.ecms.dbms.models.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private Connection conn;

    // Constructor
    public PaymentDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE: Add a new payment record
    public boolean addPayment(Payment payment) {
        String sql = "INSERT INTO payment (Payment_ID, Subscription_ID, Amount, Payment_Date, Payment_Mode, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getPaymentId());
            stmt.setInt(2, payment.getSubscriptionId());
            stmt.setDouble(3, payment.getAmount());
            stmt.setString(4, payment.getPaymentDate());
            stmt.setString(5, payment.getPaymentMode());
            stmt.setString(6, payment.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error adding payment: " + e.getMessage());
            return false;
        }
    }

    // READ: Get all payment records
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Payment p = new Payment(
                        rs.getInt("Payment_ID"),
                        rs.getInt("Subscription_ID"),
                        rs.getDouble("Amount"),
                        rs.getString("Payment_Date"),
                        rs.getString("Payment_Mode"),
                        rs.getString("Status")
                );
                payments.add(p);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching payments: " + e.getMessage());
        }
        return payments;
    }

    // READ: Get payment by subscription ID
    public Payment getPaymentBySubscriptionId(int subscriptionId) {
        Payment payment = null;
        String sql = "SELECT * FROM payment WHERE Subscription_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                payment = new Payment(
                        rs.getInt("Payment_ID"),
                        rs.getInt("Subscription_ID"),
                        rs.getDouble("Amount"),
                        rs.getString("Payment_Date"),
                        rs.getString("Payment_Mode"),
                        rs.getString("Status")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching payment by subscription ID: " + e.getMessage());
        }
        return payment;
    }

    // UPDATE: Update payment details (e.g. mark as Paid)
    public boolean updatePayment(Payment payment) {
        String sql = "UPDATE payment SET Subscription_ID=?, Amount=?, Payment_Date=?, Payment_Mode=?, Status=? WHERE Payment_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getSubscriptionId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setString(3, payment.getPaymentDate());
            stmt.setString(4, payment.getPaymentMode());
            stmt.setString(5, payment.getStatus());
            stmt.setInt(6, payment.getPaymentId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error updating payment: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Delete payment by ID
    public boolean deletePayment(int paymentId) {
        String sql = "DELETE FROM payment WHERE Payment_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paymentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error deleting payment: " + e.getMessage());
            return false;
        }
    }
}
