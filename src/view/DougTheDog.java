import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class: DougTheDog
 *
 * @author Ekechi Nzewi
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/10/2021
 * Description: This class contains the main gameplay loop
 */
public class DougTheDog {
    private GameController gc;
    private DBController dbc;
    private Scanner input;
    private String userName;

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        try {
            DougTheDog theDog = new DougTheDog();
            theDog.loginMenu();
        } catch (GameException | SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Default no-arg constructor
     */
    public DougTheDog() throws SQLException, ClassNotFoundException {
        gc = new GameController();
        dbc = new DBController();
    }

    /**
     * Method newGame
     * Starts a new game for the user
     *
     * @param account the account relating to the save
     * @throws Exception
     */
    public void newGame(Account account) throws Exception {
        File dbFile = new File(account.getSaveName());
        if (dbFile.exists()) {
            dbFile.delete();
        }
        account.createSave();
        account.verifySave();
        continueGame();
    }

    /**
     * Method continueGame
     * Allows the player to play the game.
     * Prints an introduction message
     * Loops until the user chooses to exit.
     * Calls getCommand to get users input. Passes the user's command to Commands, executeCommand for processing.
     */
    public void continueGame() {
        String control = ""; // String for controlling the loop
        try {
            System.out.println();
            System.out.println("""
                    Welcome player!
                    You are about to play Doug the Dog.
                    Words in CAPS are your commands. Pay attention to these, they are useful to help you navigate through the game. Inputs are not case sensitive.
                    Enter EXIT to leave the game.
                    """);
            System.out.println(gc.displayCurrentRoom());
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        do {
            try {
                String userInput = getCommand(); // gets the user's input
                control = gc.executeCommand(userInput); // pass the input to executeCommand in GameController to determine what should happen
                String exitText = "What would you like to do?";
                String outputText = control.equalsIgnoreCase("no") ? exitText : control; // returns a different String depending on the output of executeCommand
                System.out.println(outputText); // prints the output
            } catch (SQLException | ClassNotFoundException | GameException ex) {
                System.out.println(ex.getMessage());
            }
        } while (!control.equalsIgnoreCase("no")); // as long as the control string isn't "no", the loop continues

        input = new Scanner(System.in);
        try {
            System.out.println("""
                    Start a new game? (1)
                    Continue from your previous save? (2)
                    Or do you want to exit? (3)
                    """);
            String response = input.nextLine();
            switch (response) {
                case "1" -> newGame(dbc.getAcct(userName));
                case "2" -> continueGame();
                case "3" -> System.exit(0);
                default -> throw new GameException("Invalid Input");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        }
    }

    /**
     * Method getCommand
     * Prompts the user for their input and returns this to playGame
     *
     * @return String - the command entered by the user.
     */
    public String getCommand() {
        System.out.println("What would you like to do?");
        System.out.print("> ");
        input = new Scanner(System.in);
        String userInput = input.nextLine(); // gets the user's input
        while (userInput.isBlank()) {
            userInput = getCommand(); // as long as their input is blank, prompt them for their input
        }
        System.out.println();
        return userInput.trim(); // returns the user's input
    }

    /**
     * Method loginMenu
     * Prompts the user for whether they are a returning user or not
     */
    public void loginMenu() throws GameException {
        System.out.println("""
                New Player (1)
                Returning Player (2)
                """);
        input = new Scanner(System.in);

        String userResponse = input.nextLine();

        switch (userResponse) {
            case "1" -> createAccount();
            case "2" -> returningPlayer();
            default -> throw new GameException("Invalid Input");
        }
    }

    /**
     * Method returningPlayer
     * Verifies a user's login then prompts them to either continue a saved game or start a new game
     */
    private void returningPlayer() {
        input = new Scanner(System.in);
        System.out.println("Username: ");
        userName = input.nextLine();

        System.out.println("Password: ");
        String password = input.nextLine();

        try {
            Account acct = dbc.getAcct(userName);
            System.out.println();
            if (acct.validLogIn(userName, password)) {
                System.out.println("""
                        Do you want to start a new game? (1)
                        Or continue from your previous save? (2)
                        """);
                String response = input.nextLine();
                switch (response) {
                    case "1" -> newGame(acct);
                    case "2" -> continueGame();
                    default -> throw new GameException("Invalid Input");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        }
    }

    /**
     * Method createAccount
     * Creates a new account and save for a user
     */
    public void createAccount() {
        input = new Scanner(System.in);
        System.out.println("Username: ");
        userName = input.nextLine();

        System.out.println("Password: ");
        String password = input.nextLine();
        System.out.println("Reenter password:");
        String temp = input.nextLine();
        while (!temp.equals(password)) {
            System.out.println("""
                    Password does not match.
                    Reenter password:
                    """);
            temp = input.next();
        }
        System.out.println();
        Account account = new Account();
        try {
            dbc.buildAccountsTable();
            account.createAccount(userName, password);
            newGame(account);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println();
        }
    }
}