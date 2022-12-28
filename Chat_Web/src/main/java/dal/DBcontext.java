package dal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author fifak
 */
public class DBcontext {
    protected Connection connection;

    public DBcontext() {

        try {
            String user = "sonbn";
            String password = "201001";
            String url = "jdbc:sqlserver://localhost:1433;" +
                    "database=ChattWeb;" +
                    "encrypt=true;" +
                    "trustServerCertificate=true";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
