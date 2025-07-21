import employees.Employees;
import students.Students;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter 'E' for employee and 'S' for student");
        String n1 = sc.nextLine().toUpperCase();


        if (n1.equals("E")) {
            Employees e1 = new Employees();

//            e1.createDataBase();
//            e1.createTable();
            System.out.println("enter how many employee you want to save ");
            int emp = sc.nextInt();
            for (int i = 0; i < emp; i++) {
                e1.insertdata();
                System.out.println("data is loaded successfully.. ");
            }
            e1.update();


        } else if (n1.equals("S")) {
            Students s1 = new Students();
//            s1.createDataBase();
            s1.createTable();
            System.out.println("enter how many student you wanna register");
            int stu = sc.nextByte();
            for (int i = 0; i < stu; i++) {
                s1.insertdata();
                System.out.println("data is loaded successfully.. ");
            }

            s1.update();

        } else {
            System.out.println("!Invalid Input!");
        }
    }

}