import java.io.File;
import java.sql.SQLException;

/**
 * Class: AccountDBCreate
 *
 * @author Ekechi Nzewi
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/05/2021
 * Description: this class handles the creation of the Accounts database
 */
public class AccountDBCreate {
    SQLiteDB sdb;

    /**
     * Method buildTables
     * Builds the Accounts database
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void buildTables() throws SQLException, ClassNotFoundException {
        buildAccount();
    }

    /**
     * Method buildAccount
     * Builds the Accounts table
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void buildAccount() throws SQLException, ClassNotFoundException {
        File accountsDir = new File("databaseFiles");
        accountsDir.mkdir();
        sdb = new SQLiteDB("databaseFiles/Accounts.db");
        String sql = """
                CREATE TABLE Account (accountID INTEGER NOT NULL PRIMARY KEY,
                username TEXT NOT NULL, password TEXT NOT NULL, saveName TEXT NOT NULL)
                """;
        sdb.updateDB(sql);
        sdb.close();
    }
}
