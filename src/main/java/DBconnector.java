import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector {
    private static final String URL = "jdbc:mysql://localhost:3306/startcode_test?serverTimezone=Europe/Copenhagen";
    private static final String USER = "dev";
    private static final String PW = "ax2";

    private static Connection singleton;

    public static void setConnection( Connection con ) {
        singleton = con;
    }

    public static Connection connection() {
        if ( singleton == null ) {
            try {
                Class.forName( "com.mysql.cj.jdbc.Driver" );
                singleton = DriverManager.getConnection( URL, USER, PW );
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return singleton;
    }

}