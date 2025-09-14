package com.oop.students;

import com.oop.students.dao.StudentDAO;
import com.oop.students.service.StudentService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            createDbIfNeeded();
        } catch (SQLException e) {
            System.out.println("Khong the tao DB/bang: " + e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService(new StudentDAO(), sc);

        while (true) {
            System.out.println("\n===== QUAN LY SINH VIEN =====");
            System.out.println("1. Them sinh vien");
            System.out.println("2. Sua sinh vien");
            System.out.println("3. Xoa sinh vien");
            System.out.println("4. In tat ca SV");
            System.out.println("5. In SV theo lop");
            System.out.println("6. In SV theo nganh");
            System.out.println("7. In SV sap xep GPA giam dan");
            System.out.println("8. In SV sinh vao thang");
            System.out.println("9. Seed ~10 SV mau");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            String c = sc.nextLine().trim();
            try {
                switch (c) {
                    case "1" -> service.addStudent();
                    case "2" -> service.updateStudent();
                    case "3" -> service.deleteStudent();
                    case "4" -> service.listAll();
                    case "5" -> service.listByClass();
                    case "6" -> service.listByMajor();
                    case "7" -> service.listByGpaDesc();
                    case "8" -> service.listByBirthMonth();
                    case "9" -> service.seed();
                    case "0" -> { System.out.println("Bye!"); return; }
                    default -> System.out.println("Lua chon khong hop le!");
                }
            } catch (SQLException e) {
                System.out.println("[SQL] " + e.getMessage());
            }
        }
    }

    private static void createDbIfNeeded() throws SQLException {
        String rootUrl = "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        try (Connection conn = java.sql.DriverManager.getConnection(rootUrl, "root", "");
             Statement st = conn.createStatement()) {
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS students_db");
            st.executeUpdate("USE students_db");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS students (" +
                    "student_id VARCHAR(10) PRIMARY KEY," +
                    "full_name VARCHAR(100) NOT NULL," +
                    "birth_date DATE NOT NULL," +
                    "major VARCHAR(4) NOT NULL," +
                    "gpa DOUBLE NOT NULL," +
                    "class_group VARCHAR(20) NOT NULL)");
        }
    }
}
