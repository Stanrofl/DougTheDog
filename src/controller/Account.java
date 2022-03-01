import java.io.File;
import java.security.SecureRandom;

/**
 * Class: Account
 *
 * @author Ekechi Nzewi
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/05/2021
 * Description: this class contains logic for Account objects
 */
public class Account {
    private static int accountID;
    private static String password;
    private static String username;
    public static String saveName;

    /**
     * default no-arg constructor
     */
    public Account() {
    }

    /**
     * @param name the account username being verified
     * @param pass the account password being verified
     * @return boolean - true or false depending on whether the account is valid or not
     * @throws Exception
     */
    public boolean validLogIn(String name, String pass) throws Exception {
        if (!password.equals(pass) || !username.equalsIgnoreCase(name)) {
            throw new Exception("Username or Password is incorrect");
        }
        return true;
    }

    /**
     * @param name the username for the account being created
     * @param pass the password for the account being created
     * @throws Exception
     */
    public void createAccount(String name, String pass) throws Exception {
        SecureRandom randomGenerator = new SecureRandom();
        setPassword(pass);
        setUsername(name);
        saveName = ("databaseFiles/" + Account.username + "_save.db").toLowerCase();
        accountID = randomGenerator.nextInt(100_000);
        new AccountDB().addAcct(this);
    }

    /**
     * Method createSave
     * creates a new database file for the account
     * @throws Exception
     */
    public void createSave() throws Exception {
        GameDBCreate dbCreate = new GameDBCreate();
        File dbFile = new File(saveName);
        File savesDir = new File("databaseFiles");
        savesDir.mkdir();
        if (!dbFile.exists()) {
            dbCreate.buildTables(saveName);
        } else {
            throw new Exception("A save with that name already exists");
        }
    }

    /**
     * Method verifySave
     * Verifies a player's save exists
     * @throws Exception
     */
    public void verifySave() throws Exception {
        File dbFile = new File(saveName);
        if (!dbFile.exists()) {
            throw new Exception("That save does not exist");
        }
    }

    /**
     * Method getAccountID
     * getter for accountID
     * @return int - the id of the current account
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * Method getAccountID
     * setter for accountID
     * @param accountID the id being set
     */
    public void setAccountID(int accountID) {
        Account.accountID = accountID;
    }

    /**
     * Method getPassword
     * getter for password
     * @return String - the password of the current account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method getPassword
     * setter for password
     * @param password the password being set
     */
    public void setPassword(String password) {
        Account.password = password;
    }

    /**
     * Method getUsername
     * getter for username
     * @return String - the username of the current account
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method getUsername
     * setter for username
     * @param username the username being set
     */
    public void setUsername(String username) {
        Account.username = username;
    }

    /**
     * Method getSaveName
     * getter for saveName
     * @return String - the saveName of the current account
     */
    public String getSaveName() {
        return saveName;
    }

    /**
     * Method getSaveName
     * setter for saveName
     * @param saveName the saveName being set
     */
    public void setSaveName(String saveName) {
        Account.saveName = saveName;
    }
}
