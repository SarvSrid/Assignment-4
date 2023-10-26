import java.sql.*;

public class AddDelete {
    String url = "jdbc:mysql://localhost:3306/SecDatabase";
    private static final String username = "root";
    private static final String password = "";

    private Connection connection;

    public AddDelete(Connection connection) {
        this.connection = connection;
    }
    public void addRecord(int id, String name, String email) {
        try {
            // Check if the ID already exists in the database
            String checkSql = "SELECT id FROM customers WHERE id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setInt(1, id);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("A student with the same ID already exists. Please use a different ID.");
            } else {
                // If the ID is not found, proceed to add the new student record
                String sql = "INSERT INTO customers (id, name, email) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, email);

                // Execute the SQL statement to add the student
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Student added successfully.");
                } else {
                    System.out.println("Failed to add the student.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateRecord(int id, String newName, String newEmail) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE customers SET name = ?, email = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newEmail);
            preparedStatement.setInt(3, id);

            // Execute the SQL statement to update the student
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Failed to update the student. Student not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int id) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "DELETE FROM customers WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            // Execute the SQL statement to delete the student
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Failed to delete the student. Student not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
