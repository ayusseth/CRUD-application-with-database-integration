package employees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Employees {
    Scanner sc = new Scanner(System.in);

    public void createDataBase() {
        try {
            String url = "jdbc:mysql://localhost:3306";
            String userName = "root";
            String pswrd = "Ayush*(0)(0)";

            Connection con = DriverManager.getConnection(url, userName, pswrd);
            System.out.println("connection is established");
            Statement stm = con.createStatement();
            String query = "create database if not exists employeesDB";
            stm.execute(query);

            System.out.println("employee database is created");
            con.close();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void createTable() {
        try {
            String url = "jdbc:mysql://localhost:3306/employeesDB";
            String userName = "root";
            String pswrd = "Ayush*(0)(0)";

            Connection con = DriverManager.getConnection(url, userName, pswrd);

            Statement stm = con.createStatement();
            String query = "create table employeeInfo (id int primary key, name varchar(50), salary double(20,2), email varchar(20))";
            stm.execute(query);

            System.out.println("table (employeeInfo) is created");
            stm.close();
            con.close();
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
            System.out.println("enter the salary ");
            double salary = sc.nextDouble();
            sc.nextLine();
            System.out.println("enter the mail ");
            String email = sc.nextLine();


            String url = "jdbc:mysql://localhost:3306/employeesDB";
            String userName = "root";
            String pswrd = "Ayush*(0)(0)";

            Connection con = DriverManager.getConnection(url, userName, pswrd);

            String query = "insert into employeeInfo (id, name, salary, email) values (?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setInt(1, id);
            pstm.setString(2, name);
            pstm.setDouble(3, salary);
            pstm.setString(4, email);

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
            String url = "jdbc:mysql://localhost:3306/employeesDB";
            String userName = "root";
            String pswrd = "Ayush*(0)(0)";

            Connection con = DriverManager.getConnection(url, userName, pswrd);
            System.out.println("enter 'U' to update and 'D' to delete");
            String n2 = sc.nextLine().toUpperCase();
            if (n2.equals("U")) {
                while (true) {
                    System.out.println("enter what you want to update 'N'-> name, 'S'-> salary, 'E'-> email, 'X'-> exit");
                    String n3 = sc.nextLine().toUpperCase();

                    switch (n3) {
                        case "N":
                            System.out.println("enter id which you want to update ");
                            int idname = sc.nextInt();
                            sc.nextLine();
                            System.out.println("enter the new name");
                            String newname = sc.nextLine();
                            String namequery = "update employeeInfo set name=? where id=?";
                            try (PreparedStatement pstm = con.prepareStatement(namequery)) {
                                pstm.setString(1, newname);
                                pstm.setInt(2, idname);
                                int rows = pstm.executeUpdate();
                                System.out.println(rows > 0 ? "name is updated" : "id not found");
                            }

                            break;
                        case "S":
                            System.out.println("enter id which you want to update ");
                            int idsalary = sc.nextInt();
                            sc.nextLine();
                            System.out.println("enter new Salary ");
                            double newsalary = sc.nextDouble();
                            String salquery = "update employeeInfo set salary=? where id=?";
                            try (PreparedStatement pstm = con.prepareStatement(salquery)) {
                                pstm.setDouble(1, newsalary);
                                pstm.setInt(2, idsalary);
                                int rows = pstm.executeUpdate();
                                System.out.println(rows > 0 ? "salary changed" : "id not found");
                            }
                            break;
                        case "E":
                            System.out.println("enter id which you want to update ");
                            int idemail = sc.nextInt();
                            sc.nextLine();
                            System.out.println("enter new mail ");
                            String newmail = sc.nextLine();
                            String mailquery = "update employeeInfo set email=?, where id=?";
                            try (PreparedStatement pstm = con.prepareStatement(mailquery)) {
                                pstm.setString(1, newmail);
                                pstm.setInt(2, idemail);
                                int rows = pstm.executeUpdate();
                                System.out.println(rows > 0 ? "mail changed" : "id not found");
                            }
                            break;
                        case "X":
                            System.out.println("Updation is successfull..");
                            System.out.println("returning to hanger...");
                            return;
                        default:
                            System.out.println("Invalid choice.");
                    }

                }

            } else if (n2.equals("D")) {
                System.out.println("enter the id to delete");
                int deleteid = sc.nextInt();
                String deletequery = "delete from employeeInfo where id=?";
                try (PreparedStatement pst = con.prepareStatement(deletequery)) {
                    pst.setInt(1, deleteid);
                    int rows = pst.executeUpdate();
                    System.out.println(rows > 0 ? "employee is deleted" : "employee not found");
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

