/*
 * DAO class for Course table
 */
package com.ecms.dbms.data;

import com.ecms.dbms.models.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private Connection conn;

    // Constructor
    public CourseDAO(Connection conn) {
        this.conn = conn;
    }

    // CREATE: Add new course
    public boolean addCourse(Course course) {
        String sql = "INSERT INTO course (Course_ID, Course_Name, Description, Category, Duration, Instructor_ID, Prerequisite, Price) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, course.getCourseId());
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getDescription());
            stmt.setString(4, course.getCategory());
            stmt.setInt(5, course.getDuration());
            stmt.setInt(6, course.getInstructorId());
            stmt.setString(7, course.getPrerequisite());
             stmt.setDouble(8, course.getPrice());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
            return false;
        }
    }

    // READ: Get all courses
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Course c = new Course(
                        rs.getInt("Course_ID"),
                        rs.getString("Course_Name"),
                        rs.getString("Description"),
                        rs.getString("Category"),
                        rs.getInt("Duration"),
                        rs.getInt("Instructor_ID"),
                        rs.getString("Prerequisite"),
                        rs.getDouble("Price")
                );
                courses.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching courses: " + e.getMessage());
        }
        return courses;
    }

    // READ: Get course by ID
    public Course getCourseById(int courseId) {
        Course course = null;
        String sql = "SELECT * FROM course WHERE Course_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                course = new Course(
                        rs.getInt("Course_ID"),
                        rs.getString("Course_Name"),
                        rs.getString("Description"),
                        rs.getString("Category"),
                        rs.getInt("Duration"),
                        rs.getInt("Instructor_ID"),
                        rs.getString("Prerequisite"),
                         rs.getDouble("Price")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching course by ID: " + e.getMessage());
        }
        return course;
    }

    // READ: Get courses by Instructor ID
    public List<Course> getCoursesByInstructorId(int instructorId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course WHERE Instructor_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, instructorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course c = new Course(
                        rs.getInt("Course_ID"),
                        rs.getString("Course_Name"),
                        rs.getString("Description"),
                        rs.getString("Category"),
                        rs.getInt("Duration"),
                        rs.getInt("Instructor_ID"),
                        rs.getString("Prerequisite"),
                         rs.getDouble("Price")
                );
                courses.add(c);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching courses by instructor: " + e.getMessage());
        }
        return courses;
    }

    // UPDATE: Modify course info
    public boolean updateCourse(Course course) {
        String sql = "UPDATE course SET Course_Name=?, Description=?, Category=?, Duration=?, Instructor_ID=?, Prerequisite=? WHERE Course_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getDescription());
            stmt.setString(3, course.getCategory());
            stmt.setInt(4, course.getDuration());
            stmt.setInt(5, course.getInstructorId());
            stmt.setString(6, course.getPrerequisite());
            stmt.setInt(7, course.getCourseId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error updating course: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Remove course by ID
    public boolean deleteCourse(int courseId) {
        String sql = "DELETE FROM course WHERE Course_ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error deleting course: " + e.getMessage());
            return false;
        }
    }
}
