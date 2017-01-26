import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseManager {


    public static Connection DbConnector()
    {
        Connection conn=null;
        try{
            //https://bitbucket.org/xerial/sqlite-jdbc/downloads
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:Db");

            return conn;

        }catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e);

        }
        return null;
    }
}
