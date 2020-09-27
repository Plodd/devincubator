import java.sql.*;

public class AccountDao {

    public static Integer returningUserIdOfRichestUser() throws ClassNotFoundException {
        String RETURNING_ACCOUNTS_SQL = "SELECT * FROM account";

        Integer userid = 0;
        Integer account = 0;
        String url = "jdbc:mysql://localhost:3306/devincubatordb?useUnicode=true&serverTimezone=UTC";
        String username = "root";
        String password = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(RETURNING_ACCOUNTS_SQL);

            while(resultSet.next()) {

                if(account<resultSet.getInt(2)) {
                    account  = resultSet.getInt(2);
                    userid = resultSet.getInt(3);
                }
            }

        } catch (SQLException e) {

            printSQLException(e);

        }
        return userid;
    }

    public static Integer returningSumOfAllAccounts() throws ClassNotFoundException {
        String RETURNING_ACCOUNTS_SQL = "SELECT * FROM account";

        Integer sumOfAllAccounts = 0;
        String url = "jdbc:mysql://localhost:3306/devincubatordb?useUnicode=true&serverTimezone=UTC";
        String username = "root";
        String password = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(RETURNING_ACCOUNTS_SQL);

            while(resultSet.next()) {

                sumOfAllAccounts += resultSet.getInt(2);

            }

        } catch (SQLException e) {

            printSQLException(e);

        }
        return sumOfAllAccounts;
    }

    private static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
