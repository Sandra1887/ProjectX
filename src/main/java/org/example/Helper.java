package org.example;

import com.sun.security.jgss.GSSUtil;

import java.util.Scanner;

public class Helper {
    public Scanner sc;
    public Helper() {
        sc = new Scanner(System.in);
    }

    public String askForDbName() {
        System.out.println("Welcome to the ToDo-Application! Enter dbName and press enter");
        return sc.nextLine();
    }

    public int mainMenu() {
        System.out.println("""
                What would you like to do?
                1. Create Todo
                2. Read Todo
                3. Update Todo
                4. Delete Todo
                5. Exit""");

        return sc.nextInt();
    }
    public String askForTableName() {
        System.out.println("Enter table name and press enter");
        return sc.nextLine();
    }

   public String askForOnlyAssignment() {
        System.out.println("Enter assignment and press enter");
        return sc.nextLine();
    }
    public String askForAssignee() {
        System.out.println("Enter assignee and press enter");
        return sc.nextLine();
    }
    public String askForDone() {
        System.out.println("Enter done (yes or no) and press enter");
        return sc.nextLine();
    }
    public String askForUpdate() {
        System.out.println("Update assignment or done? Enter your choice and press enter");
        return sc.nextLine();
    }

    public ToDo askForAssignment() {
        System.out.println("Enter assignment and press enter");
        String assignment = sc.nextLine();
        System.out.println("Enter assignee and press enter");
        String assignee = sc.nextLine();
        System.out.println("Enter done (yes or no) and press enter");
        String done = sc.nextLine();
        return new ToDo(assignment, assignee, done);
    }
    public int askForId() {
        System.out.println("Enter id and press enter");
        return sc.nextInt();
    }
}
