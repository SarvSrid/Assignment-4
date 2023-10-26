import java.sql.*;
import java.util.Scanner;

public class SecDatabase {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/SecDatabase";
        String username = "root";
        String password = "";
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            AddDelete addDelete = new AddDelete(connection);

            int choice;

            do {
                System.out.println("\nMenu:");
                System.out.println("1. Add new student");
                System.out.println("2. View all students");
                System.out.println("3. Update student data");
                System.out.println("4. Delete a student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (choice == 1) {
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student email: ");
                    String email = scanner.nextLine();
                    addDelete.addRecord(id, name, email);
                } else if (choice == 2) {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
                    }
                } else if (choice == 3) {
                    System.out.print("Enter student ID to update: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new student name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new student email: ");
                    String newEmail = scanner.nextLine();
                    addDelete.updateRecord(idToUpdate, newName, newEmail);
                } else if (choice == 4) {
                    System.out.print("Enter student ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    addDelete.deleteRecord(idToDelete);
                } else if (choice == 5) {
                    System.out.println("Exiting...");
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }

            } while (choice != 5);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
