/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * DAO class for Certificate table
 */
/*
 * DAO class for Certificate table
 */
package com.ecms.dbms.data;

import com.ecms.dbms.models.Certificate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificateDAO {

    private Connection conn;

    // Constructor
    public CertificateDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE: Issue a new certificate
    public boolean addCertificate(Certificate certificate) {
        String sql = "INSERT INTO certificate (Certificate_ID, Learner_ID, Course_ID, Issue_Date, Instructor_ID, Verification_Code) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, certificate.getCertificateId());
            stmt.setInt(2, certificate.getLearnerId());
            stmt.setInt(3, certificate.getCourseId());
            stmt.setString(4, certificate.getIssueDate());
            stmt.setInt(5, certificate.getInstructorId());
            stmt.setString(6, certificate.getVerificationCode());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error issuing certificate: " + e.getMessage());
            return false;
        }
    }

    // READ: Get all certificates
    public List<Certificate> getAllCertificates() {
        List<Certificate> certificates = new ArrayList<>();
        String sql = "SELECT * FROM certificate";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Certificate c = new Certificate(
                        rs.getInt("Certificate_ID"),
                        rs.getInt("Learner_ID"),
                        rs.getInt("Course_ID"),
                        rs.getString("Issue_Date"),
                        rs.getInt("Instructor_ID"),
                        rs.getString("Verification_Code")
                );
                certificates.add(c);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching certificates: " + e.getMessage());
        }
        return certificates;
    }

    // READ: Get certificates for a specific learner
    public List<Certificate> getCertificatesByLearner(int learnerId) {
        List<Certificate> list = new ArrayList<>();
        String sql = "SELECT * FROM certificate WHERE Learner_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, learnerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Certificate c = new Certificate(
                        rs.getInt("Certificate_ID"),
                        rs.getInt("Learner_ID"),
                        rs.getInt("Course_ID"),
                        rs.getString("Issue_Date"),
                        rs.getInt("Instructor_ID"),
                        rs.getString("Verification_Code")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching certificates by learner: " + e.getMessage());
        }
        return list;
    }

    // READ: Get certificate by course (for a specific learner)
    public Certificate getCertificateByCourse(int learnerId, int courseId) {
        Certificate c = null;
        String sql = "SELECT * FROM certificate WHERE Learner_ID = ? AND Course_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, learnerId);
            stmt.setInt(2, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                c = new Certificate(
                        rs.getInt("Certificate_ID"),
                        rs.getInt("Learner_ID"),
                        rs.getInt("Course_ID"),
                        rs.getString("Issue_Date"),
                        rs.getInt("Instructor_ID"),
                        rs.getString("Verification_Code")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching certificate by course: " + e.getMessage());
        }
        return c;
    }

    // UPDATE: Modify certificate details (if needed)
    public boolean updateCertificate(Certificate certificate) {
        String sql = "UPDATE certificate SET Learner_ID=?, Course_ID=?, Issue_Date=?, Instructor_ID=?, Verification_Code=? WHERE Certificate_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, certificate.getLearnerId());
            stmt.setInt(2, certificate.getCourseId());
            stmt.setString(3, certificate.getIssueDate());
            stmt.setInt(4, certificate.getInstructorId());
            stmt.setString(5, certificate.getVerificationCode());
            stmt.setInt(6, certificate.getCertificateId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error updating certificate: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Remove a certificate
    public boolean deleteCertificate(int certificateId) {
        String sql = "DELETE FROM certificate WHERE Certificate_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, certificateId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error deleting certificate: " + e.getMessage());
            return false;
        }
    }
}
