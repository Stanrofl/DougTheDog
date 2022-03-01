import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * Class: SQLiteDB
 * @author Nan Zhou
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/05/2021
 * Description: this class is an interface to SQLite database
 */

public class SQLiteDB extends DB{

    /**
     * default no-arg constructor
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public SQLiteDB() throws ClassNotFoundException, SQLException {
        sDriverName = "org.sqlite.JDBC";
        Class.forName(sDriverName);
        sJDBC = "jdbc:sqlite";
        sDBURL = sJDBC + ":" + dbName;
        conn = DriverManager.getConnection(sDBURL);
    }

    /**
     * Constructor that takes in the name of the database
     * @param dbName
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public SQLiteDB(String dbName) throws ClassNotFoundException, SQLException {
        this.dbName = dbName;
        sDriverName = "org.sqlite.JDBC";
        Class.forName(sDriverName);
        sJDBC = "jdbc:sqlite";
        sDBURL = sJDBC + ":" + dbName;
        conn = DriverManager.getConnection(sDBURL);
    }

    /**
     * Method for closing database connection
     * @throws SQLException
     */
    public void close() throws SQLException {
        conn.close();
    }

}