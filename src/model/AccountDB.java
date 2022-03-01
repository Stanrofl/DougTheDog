import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class: AccountDB
 *
 * @author Ekechi Nzewi
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/05/2021
 * Description: this class handles interactions between the database and Account class
 */
public class AccountDB {
    private static int accountID;

    /**
     * Method getAcct
     * returns a specific user account
     *
     * @param userName the name of the account being looked up
     * @return Account - the account being found
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Account getAcct(String userName) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB("databaseFiles/Accounts.db");
        Account acct = new Account();
        String sql = "Select * from Account WHERE username like '" + userName + "'";

        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            acct.setUsername(rs.getString("username"));
            acct.setPassword(rs.getString("password"));
            acct.setSaveName(rs.getString("saveName"));
            accountID = rs.getInt("accountID");
            acct.setAccountID(rs.getInt("accountID"));
        } else {
            sdb.close();
            throw new SQLException("Account " + userName + " not found.");
        }
        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return acct;
    }

    /**
     * Method getAcct
     * returns a specific user account
     *
     * @param id the id of the account being looked up
     * @return Account - the account being found
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Account getAcct(int id) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB("databaseFiles/Accounts.db");
        Account acct = new Account();
        String sql = "Select * from Account WHERE accountID = " + id;

        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            acct.setUsername(rs.getString("username"));
            acct.setPassword(rs.getString("password"));
            acct.setSaveName(rs.getString("saveName"));
            accountID = rs.getInt("accountID");
            acct.setAccountID(rs.getInt("accountID"));
        } else {
            sdb.close();
            throw new SQLException("No account exists with the ID " + acct.getUsername());
        }
        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return acct;
    }

    /**
     * Method addAcct
     * adds an account to the Accounts database
     *
     * @param account the account being added to the database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addAcct(Account account) throws Exception {
        SQLiteDB sdb = new SQLiteDB("databaseFiles/Accounts.db");
        checkUnique(account.getUsername());
        String sql = "INSERT INTO Account(accountID, username, password, saveName) Values(" + account.getAccountID() + ", " + "'" + account.getUsername() + "', " + "'" + account.getPassword() + "', " + "'" + account.getSaveName() + "')";
        sdb.updateDB(sql);
        sdb.close();
    }

    /**
     * Method checkUnique
     * checks if there is already an account in the database with a certain userName
     * @param userName the userName being checked if it is unique
     * @throws Exception
     */
    private void checkUnique(String userName) throws Exception {
        String name;
        SQLiteDB sdb = new SQLiteDB("databaseFiles/Accounts.db");
        Statement stmt = sdb.conn.createStatement();
        String sql = "Select username from Account";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            name = rs.getString(1);
            if (userName.equals(name)) {
                throw new Exception(("There is already a user with that username."));
            }
        }
        sdb.close();
    }
}
