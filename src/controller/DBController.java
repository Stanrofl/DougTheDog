import java.io.File;
import java.sql.SQLException;

/**
 * Class: DBController
 * @author Ekechi Nzewi
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/11/2021
 * Description: this class handles interactions between view model and accounts logic
 */
public class DBController {
    private AccountDB accountDB;

    /**
     * Default no-arg constructor
     */
    public DBController() {
        this.accountDB = new AccountDB();
    }

    /**
     * Method buildAccountsTables
     * Builds the Accounts database
     *
     * @throws GameException
     */
    public void buildAccountsTable() throws GameException {
        File accountsFile = new File("databaseFiles/Accounts.db");
        AccountDBCreate dbCreate = new AccountDBCreate();
        try {
            if (!accountsFile.exists()) {
                dbCreate.buildTables();
            }
        } catch (Exception ex) {
            throw new GameException(ex.getMessage());
        }
    }

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
        return accountDB.getAcct(userName);
    }
}
