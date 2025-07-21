package students;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Students {
    Scanner sc = new Scanner(System.in);
    String nf = "id not found";

    public void createDataBase() {
        try {
            String url = "jdbc:mysql://localhost:3306";
            String userName = "root";
            String pswrd = "Ayush*(0)(0)";

            Connection con = DriverManager.getConnection(url, userName, pswrd);
            System.out.println("connection is established");
            Statement stm = con.createStatement();
            String query = "create database if not exists studentsDB";
            stm.execute(query);

            System.out.println("student database is created");
            stm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void createTable() {
        try {
            String url = "jdbc:mysql://localhost:3306/studentsDB";
            String userName = "root";
            String pswrd = "Ayush*(0)(0)";

            Connection con = DriverManager.getConnection(url, userName, pswrd);

            Statement stm = con.createStatement();
            String query = "create table studentInfo (id int primary key, name varchar(50), age int not null, marks int not null)";
            stm.execute(query);

            System.out.println("table (studentInfo) is created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertdata() {
        try {

            System.out.println("enter the id ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("enter the name ");
            String name = sc.nextLine();

            System.out.println("enter the age ");
            int age = sc.nextInt();

            System.out.println("enter the marks ");
            int marks = sc.nextInt();


            String url = "jdbc:mysql://localhost:3306/studentsDB";
            String userName = "root";
            String pswrd = "Ayush*(0)(0)";

            Connection con = DriverManager.getConnection(url, userName, pswrd);

            String query = "insert into studentInfo (id, name, age, marks) values (?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, id);
            pstm.setString(2, name);
            pstm.setInt(3, age);
            pstm.setInt(4, marks);

            int rows = pstm.executeUpdate();
            System.out.println(rows > 0 ? "data is inserted" : "data insertion is failed");
            con.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {

        try {
            String url = "jdbc:mysql://localhost:3306/studentsDB";
            String userName = "root";
            String pswrd = "Ayush*(0)(0)";

            Connection con = DriverManager.getConnection(url, userName, pswrd);
            System.out.println("enter 'U' to update and 'D' to delete");
            String n2 = sc.nextLine().toUpperCase();
            if (n2.equals("U")) {
                while (true) {
                    System.out.println("enter what you want to update 'N'-> name, 'A'-> age, 'M'-> marks, 'X'-> exit");
                    String n3 = sc.nextLine().toUpperCase();

                    switch (n3) {
                        case "N":
                            System.out.println("enter id which you want to update ");
                            int idname = sc.nextInt();
                            sc.nextLine();
                            System.out.println("enter the new name");
                            String newname = sc.nextLine();
                            String namequery = "update studentInfo set name=? where id=?";
                            try (PreparedStatement pstm = con.prepareStatement(namequery)) {
                                pstm.setString(1, newname);
                                pstm.setInt(2, idname);
                                int rows = pstm.executeUpdate();
                                System.out.println(rows > 0 ? "name is updated" : nf);
                            }

                            break;
                        case "A":
                            System.out.println("enter id which you want to update ");
                            int idage = sc.nextInt();
                            sc.nextLine();
                            System.out.println("enter new age ");
                            int newage = sc.nextInt();
                            String agequery = "update studentInfo set age=?, where id=?";
                            try (PreparedStatement pstm = con.prepareStatement(agequery)) {
                                pstm.setInt(1, newage);
                                pstm.setInt(2, idage);
                                int rows = pstm.executeUpdate();
                                System.out.println(rows > 0 ? "age is changed" : nf);
                            }
                            break;
                        case "M":
                            System.out.println("enter id which you want to update ");
                            int idemail = sc.nextInt();
                            sc.nextLine();
                            System.out.println("enter new marks ");
                            int newmail = sc.nextInt();
                            String mailquery = "update studentInfo set marks=?, where id=?";
                            try (PreparedStatement pstm = con.prepareStatement(mailquery)) {
                                pstm.setInt(1, newmail);
                                pstm.setInt(2, idemail);
                                int rows = pstm.executeUpdate();
                                System.out.println(rows > 0 ? "marks changed" : nf);
                            }
                            break;
                        case "X":
                            System.out.println("Updation is successfull..");
                            System.out.println("returning to hanger....");
                            return;
                        default:
                            System.out.println("Invalid choice.");
                    }

                }

            } else if (n2.equals("D")) {
                System.out.println("enter the id to delete");
                int deleteid = sc.nextInt();
                String deletequery = "delete from studentInfo where id=?";
                try (PreparedStatement pst = con.prepareStatement(deletequery)) {
                    pst.setInt(1, deleteid);
                    int rows = pst.executeUpdate();
                    System.out.println(rows > 0 ? "student is deleted" : "student not found");
                }

            } else {
                System.out.println("Invalid input");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
