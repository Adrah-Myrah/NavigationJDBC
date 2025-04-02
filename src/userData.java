import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class userData {
      static final String url = "jdbc:mysql://localhost:3306/datainput_db";
     static final String user = "root";
    private static final String password = "";

    public static void saveData() {
        String sql = "INSERT INTO dataentry (fullname,date_of_birth,nationality,sex,country,marital_status,feedback) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,NavigationDemo.fullName);
            preparedStatement.setString(2,NavigationDemo.date_of_birth);
            preparedStatement.setString(3, NavigationDemo.nationality);
            preparedStatement.setString(4, NavigationDemo.sex);
            preparedStatement.setString(5, NavigationDemo.country);
            preparedStatement.setString(6, NavigationDemo.marital_status);
            preparedStatement.setString(7, NavigationDemo.feedback);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data saved successfully!");
            }

        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
            ex.printStackTrace(); // Handle errors
        }
    }
}