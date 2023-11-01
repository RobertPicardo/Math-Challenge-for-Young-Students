import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class saveInputtoDatabase {

    public class Main {
        public static void main(String[] args) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                student student = UserInterface.getStudentInfo();

                String insertQuery = "INSERT INTO Students (Name, GradeLevel) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, student.getName());
                    preparedStatement.setInt(2, student.getGradeLevel());
                    preparedStatement.executeUpdate();
                    System.out.println("Student data inserted successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection getConnection() {
        return null;
    }

}
