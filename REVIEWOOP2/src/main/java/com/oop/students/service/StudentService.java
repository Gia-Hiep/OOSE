package com.oop.students.service;

import com.oop.students.dao.StudentDAO;
import com.oop.students.model.Student;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    private final StudentDAO dao;
    private final Scanner sc;
    private final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public StudentService(StudentDAO dao, Scanner sc) {
        this.dao = dao;
        this.sc = sc;
    }

    public void addStudent() throws SQLException {
        Student s = inputStudent(true);
        if (dao.findById(s.getId()) != null) {
            System.out.println("=> Ma SV da ton tai!");
            return;
        }
        dao.insert(s);
        System.out.println("=> Da them!");
    }

    public void updateStudent() throws SQLException {
        System.out.print("Nhap ma SV can sua: ");
        String id = sc.nextLine().trim();
        Student old = dao.findById(id);
        if (old == null) { System.out.println("=> Khong tim thay!"); return; }
        System.out.println("Hien tai: " + old);

        Student s = inputStudent(false);
        s.setId(id);
        dao.update(s);
        System.out.println("=> Da cap nhat!");
    }

    public void deleteStudent() throws SQLException {
        System.out.print("Nhap ma SV can xoa: ");
        String id = sc.nextLine().trim();
        boolean ok = dao.delete(id);
        System.out.println(ok ? "=> Da xoa!" : "=> Khong tim thay!");
    }

    public void listAll() throws SQLException { print(dao.findAll()); }

    public void listByClass() throws SQLException {
        System.out.print("Nhap lop: ");
        String cls = sc.nextLine().trim();
        print(dao.findByClass(cls));
    }

    public void listByMajor() throws SQLException {
        System.out.print("Nhap nganh (CNTT/KTPM): ");
        String major = sc.nextLine().trim().toUpperCase();
        print(dao.findByMajor(major));
    }

    public void listByGpaDesc() throws SQLException { print(dao.sortByGpaDesc()); }

    public void listByBirthMonth() throws SQLException {
        System.out.print("Nhap thang (1..12): ");
        int m;
        try { m = Integer.parseInt(sc.nextLine().trim()); }
        catch (Exception e) { System.out.println("=> Thang khong hop le!"); return; }
        if (m < 1 || m > 12) { System.out.println("=> Thang khong hop le!"); return; }
        print(dao.findByBirthMonth(m));
    }

    public void seed() throws SQLException {
        Student[] arr = new Student[] {
            mk("4551050001","  nguyen   van a","12/03/2003","CNTT",8.7,"CNTT45A"),
            mk("4551050002","le thi  b","05/07/2004","CNTT",7.9,"CNTT45A"),
            mk("4551050003","tran  van   c","22/01/2002","CNTT",6.5,"CNTT45B"),
            mk("4551050004","pham thi d","30/09/2001","CNTT",9.1,"CNTT45B"),
            mk("4551050005","do van e","02/11/2003","CNTT",5.8,"CNTT45A"),
            mk("4551090001","bui  minh f","14/04/2003","KTPM",8.3,"KTPM"),
            mk("4551090002","ngo   thi  g","18/02/2004","KTPM",7.2,"KTPM"),
            mk("4551090003","hoang van h","09/06/2002","KTPM",9.4,"KTPM"),
            mk("4551090004","pham  thanh i","01/12/2001","KTPM",6.9,"KTPM"),
            mk("4551050006","vu  thi j","19/08/2003","CNTT",8.0,"CNTT45B")
        };
        int ok = 0, skip = 0;
        for (Student s : arr) {
            if (dao.findById(s.getId()) == null) { dao.insert(s); ok++; }
            else skip++;
        }
        System.out.printf("=> Seed xong: %d them, %d bo qua.%n", ok, skip);
    }

    private Student inputStudent(boolean requireId) {
        Student s = new Student();

        if (requireId) {
            while (true) {
                System.out.print("Ma SV (10 so, 455105xxxx CNTT / 455109xxxx KTPM): ");
                String id = sc.nextLine().trim();
                System.out.print("Nganh (CNTT/KTPM): ");
                String major = sc.nextLine().trim().toUpperCase();
                if (isValidId(id, major)) { s.setId(id); s.setMajor(major); break; }
                System.out.println("=> Ma SV hoac nganh khong hop le!");
            }
        } else {
            while (true) {
                System.out.print("Nganh (CNTT/KTPM): ");
                String major = sc.nextLine().trim().toUpperCase();
                if (isValidMajor(major)) { s.setMajor(major); break; }
                System.out.println("=> Nganh khong hop le!");
            }
        }

        while (true) {
            System.out.print("Ho ten: ");
            String raw = sc.nextLine();
            String name = raw.trim().replaceAll("\\s+", " ").toLowerCase();
            String[] parts = name.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String p : parts) {
                if (p.isEmpty()) continue;
                sb.append(Character.toUpperCase(p.charAt(0))).append(p.substring(1)).append(' ');
            }
            String normalized = sb.toString().trim();
            if (!normalized.isEmpty()) { s.setFullName(normalized); break; }
            System.out.println("=> Ho ten khong hop le!");
        }

        while (true) {
            System.out.print("Ngay sinh (dd/MM/yyyy): ");
            String txt = sc.nextLine().trim();
            try {
                LocalDate d = LocalDate.parse(txt, DF);
                if (isValidBirth(d)) { s.setBirth(d); break; }
                System.out.println("=> Tuoi phai 15..110!");
            } catch (DateTimeParseException e) {
                System.out.println("=> Dinh dang ngay khong dung!");
            }
        }

        while (true) {
            System.out.print("GPA (0..10): ");
            try {
                double g = Double.parseDouble(sc.nextLine().trim());
                if (g >= 0 && g <= 10) { s.setGpa(g); break; }
            } catch (Exception ignored) {}
            System.out.println("=> GPA khong hop le!");
        }

        System.out.print("Lop sinh hoat: ");
        s.setClassGroup(sc.nextLine().trim());

        return s;
    }

    private Student mk(String id, String rawName, String dob, String major, double gpa, String cls) {
        String name = rawName.trim().replaceAll("\\s+", " ").toLowerCase();
        String[] parts = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String p : parts) {
            if (p.isEmpty()) continue;
            sb.append(Character.toUpperCase(p.charAt(0))).append(p.substring(1)).append(' ');
        }
        String normalized = sb.toString().trim();
        return new Student(id, normalized, LocalDate.parse(dob, DF), major, gpa, cls);
    }

    private boolean isValidId(String id, String major) {
        return id != null && id.matches("\\d{10}") &&
                (("CNTT".equals(major) && id.startsWith("455105")) ||
                 ("KTPM".equals(major) && id.startsWith("455109")));
    }
    private boolean isValidMajor(String major) { return "CNTT".equals(major) || "KTPM".equals(major); }
    private boolean isValidBirth(LocalDate d) {
        if (d == null || d.isAfter(LocalDate.now())) return false;
        int age = Period.between(d, LocalDate.now()).getYears();
        return age >= 15 && age <= 110;
    }

    private void print(List<Student> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("(Danh sach rong)");
            return;
        }
        System.out.println("ID        | Ho ten                 | Ngay sinh | Nganh | GPA  | Lop");
        System.out.println("--------------------------------------------------------------------------------");
        for (Student s : list) System.out.println(s);
    }
}
