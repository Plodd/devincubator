import java.sql.*;

public class UserDao {

    public static String returningRichestUser() throws ClassNotFoundException {

        Integer userid = AccountDao.returningUserIdOfRichestUser();

        String RETURNING_USERS_BY_ID_SQL = "SELECT * " +
                "FROM user " +
                "WHERE userid=" + userid;

        String richestUser = "";
        String url = "jdbc:mysql://localhost:3306/devincubatordb?useUnicode=true&serverTimezone=UTC";
        String username = "root";
        String password = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(RETURNING_USERS_BY_ID_SQL);

            resultSet.next();
            richestUser = resultSet.getString(2) + " " + resultSet.getString(3);

        } catch (SQLException e) {

            printSQLException(e);

        }
        return richestUser;
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