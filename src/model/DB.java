import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *Class: DB
 * @author Nan Zhou
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/05/2021
 * Description: this class hold basic database functionality
 */

public class DB {

    protected String dbName;
    protected String sJDBC;
    protected String sDriverName;
    protected Connection conn;
    protected String sDBURL;
    protected int timeout;

    /**
     * Method for querying database
     * @param sql
     * @return the ResultSet
     * @throws SQLException
     */
    public ResultSet queryDB(String sql) throws SQLException {
        Statement statement = conn.createStatement();
        statement.setQueryTimeout(timeout);
        return statement.executeQuery(sql);
    }

    /**
     * Method for updating database
     * @param sql
     * @return update statement
     * @throws SQLException
     */
    public boolean updateDB(String sql) throws SQLException {
        Statement statement = conn.createStatement();
        return statement.execute(sql);
    }

}