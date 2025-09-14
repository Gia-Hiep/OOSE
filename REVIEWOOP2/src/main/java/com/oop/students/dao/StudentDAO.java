package com.oop.students.dao;

import com.oop.students.db.Db;
import com.oop.students.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void insert(Student s) throws SQLException {
        String sql = "INSERT INTO students(student_id, full_name, birth_date, major, gpa, class_group) VALUES (?,?,?,?,?,?)";
        try (Connection cn = Db.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, s.getId());
            ps.setString(2, s.getFullName());
            ps.setDate(3, Date.valueOf(s.getBirth()));
            ps.setString(4, s.getMajor());
            ps.setDouble(5, s.getGpa());
            ps.setString(6, s.getClassGroup());
            ps.executeUpdate();
        }
    }

    public void update(Student s) throws SQLException {
        String sql = "UPDATE students SET full_name=?, birth_date=?, major=?, gpa=?, class_group=? WHERE student_id=?";
        try (Connection cn = Db.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, s.getFullName());
            ps.setDate(2, Date.valueOf(s.getBirth()));
            ps.setString(3, s.getMajor());
            ps.setDouble(4, s.getGpa());
            ps.setString(5, s.getClassGroup());
            ps.setString(6, s.getId());
            ps.executeUpdate();
        }
    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id=?";
        try (Connection cn = Db.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public Student findById(String id) throws SQLException {
        String sql = "SELECT * FROM students WHERE student_id=?";
        try (Connection cn = Db.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
        }
        return null;
    }

    public List<Student> findAll() throws SQLException {
        String sql = "SELECT * FROM students";
        try (Connection cn = Db.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<Student> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    public List<Student> findByClass(String classGroup) throws SQLException {
        String sql = "SELECT * FROM students WHERE class_group=?";
        try (Connection cn = Db.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, classGroup);
            ResultSet rs = ps.executeQuery();
            List<Student> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    public List<Student> findByMajor(String major) throws SQLException {
        String sql = "SELECT * FROM students WHERE major=?";
        try (Connection cn = Db.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, major);
            ResultSet rs = ps.executeQuery();
            List<Student> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    public List<Student> sortByGpaDesc() throws SQLException {
        String sql = "SELECT * FROM students ORDER BY gpa DESC, full_name ASC";
        try (Connection cn = Db.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<Student> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    public List<Student> findByBirthMonth(int month) throws SQLException {
        String sql = "SELECT * FROM students WHERE MONTH(birth_date)=?";
        try (Connection cn = Db.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            List<Student> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    private Student map(ResultSet rs) throws SQLException {
        return new Student(
                rs.getString("student_id"),
                rs.getString("full_name"),
                rs.getDate("birth_date").toLocalDate(),
                rs.getString("major"),
                rs.getDouble("gpa"),
                rs.getString("class_group")
        );
    }
}
