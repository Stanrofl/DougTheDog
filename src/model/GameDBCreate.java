import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class: GameDBCreate
 *
 * @author Ekechi Nzewi
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/05/2021
 * Description: this class handles the creation of the save database
 */
public class GameDBCreate {
    SQLiteDB sdb;

    /**
     * Method buildTables
     * Builds the database
     * @param dbName the name of the database to connect to
     * @throws Exception
     */
    public void buildTables(String dbName) throws Exception {
        buildRoom(dbName);
        buildExit(dbName);
        buildItem(dbName);
        buildPuzzle(dbName);
        buildPlayer(dbName);
        buildMonster(dbName);
        buildItemRoom(dbName);
        buildValidCommand(dbName);
        buildMiniBoss(dbName);
    }

    /**
     * Method buildRoom
     * Builds the room table
     * @param dbName dbName the name of the database to connect to
     * @throws Exception
     */
    private void buildRoom(String dbName) throws Exception {
        sdb = new SQLiteDB(dbName);
        String sql = """
                CREATE TABLE Room (roomID INTEGER NOT NULL PRIMARY KEY,
                name INTEGER NOT NULL, description TEXT NOT NULL)
                """;
        sdb.updateDB(sql);

        FileReader fr;
        try {
            fr = new FileReader("src/textfiles/room.txt");
            Scanner inFile = new Scanner(fr);
            while (inFile.hasNextLine()) {
                int roomID = Integer.parseInt(inFile.nextLine());
                String roomName = inFile.nextLine();
                StringBuilder description = new StringBuilder(inFile.nextLine());
                while (!inFile.hasNext("----")) {
                    description.append("\n");
                    description.append(inFile.nextLine());
                }
                inFile.nextLine();
                sql = "INSERT INTO Room(roomID, name, description) Values(" + roomID + ", " + "'" + roomName + "', " + "'" + description.toString().trim() + "')";
                sdb.updateDB(sql);
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            throw new GameException(e.getMessage());
        }
        sdb.close();
    }

    /**
     * Method buildExit
     * Builds the exit table
     * @param dbName dbName the name of the database to connect to
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void buildExit(String dbName) throws Exception {
        sdb = new SQLiteDB(dbName);
        String sql = """
                CREATE TABLE Exit (exitID INTEGER NOT NULL PRIMARY KEY,
                direction TEXT NOT NULL, destination INTEGER NOT NULL, roomID INTEGER NOT NULL)
                """;
        sdb.updateDB(sql);

        FileReader fr;
        try {
            fr = new FileReader("src/textfiles/exits.txt");
            Scanner inFile = new Scanner(fr);
            while (inFile.hasNextLine()) {
                int exitID = Integer.parseInt(inFile.nextLine());
                String direction = inFile.nextLine();
                int destinationID = Integer.parseInt(inFile.nextLine());
                int roomID = Integer.parseInt(inFile.nextLine());
                inFile.nextLine();
                sql = "INSERT INTO Exit(exitID, direction, destination, roomID) Values(" + exitID + ", '" + direction + "', " + destinationID + ", " + roomID + ")";
                sdb.updateDB(sql);
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            throw new Exception(e.getMessage());
        }
        sdb.close();
    }

    /**
     * Method buildItem
     * Builds the item table
     * @param dbName dbName the name of the database to connect to
     * @throws Exception
     */
    private void buildItem(String dbName) throws Exception {
        sdb = new SQLiteDB(dbName);
        String sql = """
                CREATE TABLE Item (itemID INTEGER Primary Key not null,
                name TEXT not null, description TEXT not null, withPlayer INTEGER)
                """;
        sdb.updateDB(sql);

        FileReader fr;
        try {
            fr = new FileReader("src/textfiles/items.txt");
            Scanner inFile = new Scanner(fr);
            while (inFile.hasNextLine()) {
                var itemID = Integer.parseInt(inFile.nextLine());
                String itemName = inFile.nextLine();
                StringBuilder description = new StringBuilder(inFile.nextLine());
                while (!inFile.hasNext("----")) {
                    description.append("\n");
                    description.append(inFile.nextLine());
                }
                int withPlayer = 0;
                inFile.nextLine();
                sql = "INSERT INTO Item(itemID, name, description, withPlayer) Values(" + itemID + ", " + "'" + itemName + "', " + "'" + description + "'," + withPlayer + ")";
                sdb.updateDB(sql);
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            throw new Exception(e.getMessage());
        }
        sdb.close();
    }

    /**
     * Method buildItemRoom
     * Builds the ItemRoom table
     * @param dbName dbName the name of the database to connect to
     * @throws Exception
     */
    private void buildItemRoom(String dbName) throws Exception {
        sdb = new SQLiteDB(dbName);
        String sql = """
                CREATE TABLE ItemRoom (roomID INTEGER NOT NULL, itemID INTEGER NOT NULL,
                PRIMARY KEY (roomID, itemID), FOREIGN KEY(roomID) REFERENCES Room(roomID),
                FOREIGN KEY(itemID) REFERENCES Item(itemID))
                """;
        sdb.updateDB(sql);

        FileReader fr;
        try {
            fr = new FileReader("src/textfiles/itemRoom.txt");
            Scanner inFile = new Scanner(fr);
            while (inFile.hasNextLine()) {
                int roomID = Integer.parseInt(inFile.nextLine());
                int itemID = Integer.parseInt(inFile.nextLine());
                inFile.nextLine();
                sql = "INSERT INTO ItemRoom(roomID, itemID) Values(" + roomID + ", " + itemID + ")";
                sdb.updateDB(sql);
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            throw new Exception(e.getMessage());
        }
        sdb.close();
    }

    /**
     * Method buildPuzzle
     * Builds the puzzle table
     * @param dbName dbName the name of the database to connect to
     * @throws Exception
     */
    private void buildPuzzle(String dbName) throws Exception {
        sdb = new SQLiteDB(dbName);
        String sql = """
                CREATE TABLE Puzzle (puzzleID INTEGER not null Primary Key,
                description TEXT NOT NULL, passMessage TEXT NOT NULL,
                answer TEXT NOT NULL, roomID INTEGER NOT NULL,
                FOREIGN KEY(roomID) REFERENCES Room(roomID))
                """;
        sdb.updateDB(sql);

        FileReader fr;
        try {
            fr = new FileReader("src/textfiles/puzzle.txt");
            Scanner inFile = new Scanner(fr);
            while (inFile.hasNextLine()) {
                int puzzleID = Integer.parseInt(inFile.nextLine());
                String puzzleDescription = inFile.nextLine();
                String puzzleSolution = inFile.nextLine();
                int roomID = Integer.parseInt(inFile.nextLine());
                String passMessage = inFile.nextLine();
                inFile.nextLine();
                sql = "INSERT INTO Puzzle(puzzleID, description, passMessage, answer, roomID) " +
                        "Values(" + puzzleID + ", '" + puzzleDescription + "', '" + passMessage + "', '" + puzzleSolution + "', " + roomID + ")";
                sdb.updateDB(sql);
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            throw new Exception(e.getMessage());
        }
        sdb.close();
    }

    /**
     * Method buildMonster
     * Builds the monster table
     * @param dbName dbName the name of the database to connect to
     * @throws Exception
     */
    private void buildMonster(String dbName) throws Exception {
        sdb = new SQLiteDB(dbName);
        String sql = """
                CREATE TABLE Monster (monsterID INTEGER not null Primary Key,
                name TEXT not null, isDefeated INTEGER not null,
                hitPoints INTEGER not null,attackPower INTEGER not null,
                roomID INTEGER not null, winMessage TEXT not null,
                failMessage TEXT not null, FOREIGN KEY(roomID) REFERENCES Room(roomID))
                """;
        sdb.updateDB(sql);

        FileReader fr;
        try {
            fr = new FileReader("src/textfiles/monster.txt");
            Scanner inFile = new Scanner(fr);
            while (inFile.hasNextLine()) {
                int monsterID = Integer.parseInt(inFile.nextLine());
                String monsterName = inFile.nextLine();
                int roomID = Integer.parseInt(inFile.nextLine());
                int hitPoints = Integer.parseInt(inFile.nextLine());
                int attackPower = Integer.parseInt(inFile.nextLine());
                String failMessage = inFile.nextLine();
                String winMessage = inFile.nextLine();
                inFile.nextLine();
                int isDefeated = 0;
                sql = "INSERT INTO Monster(monsterID, name, isDefeated, hitPoints, attackPower, roomID, winMessage, failMessage) " +
                        "Values(" + monsterID + ", '" + monsterName + "', " + isDefeated + ", "
                        + hitPoints + ", " + attackPower + ", " + roomID + ", '" + winMessage + "', '" + failMessage + "')";
                sdb.updateDB(sql);
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            throw new Exception(e.getMessage());
        }
        sdb.close();
    }

    /**
     * Method buildValidCommand
     * Builds the ValidCommands table
     * @param dbName dbName the name of the database to connect to
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void buildValidCommand(String dbName) throws Exception {
        sdb = new SQLiteDB(dbName);
        String sql = """
                CREATE TABLE ValidCommands(commandID INTEGER not null Primary Key,
                type TEXT not null, cmd TEXT not null, roomID INTEGER not null, item TEXT,
                description TEXT, commandOrder INTEGER, beenRevealed INTEGER not null,
                FOREIGN KEY(roomID) REFERENCES Room(roomID))
                """;
        sdb.updateDB(sql);

        FileReader fr;
        try {
            fr = new FileReader("src/textfiles/validCommands.txt");
            Scanner inFile = new Scanner(fr);
            while (inFile.hasNextLine()) {
                int commandID = Integer.parseInt(inFile.nextLine());
                String command = inFile.nextLine();
                String commandType = inFile.nextLine();
                int roomID = Integer.parseInt(inFile.nextLine());
                String commandDescription = inFile.nextLine();
                commandDescription += "\n";
                commandDescription += inFile.nextLine();
                commandDescription += "\n";
                commandDescription += inFile.nextLine();
                commandDescription += "\n";
                commandDescription += inFile.nextLine();
                String item = inFile.nextLine();
                int order = Integer.parseInt(inFile.nextLine());
                inFile.nextLine();
                int beenRevealed = 0;
                sql = "INSERT INTO ValidCommands(commandID, type, cmd, roomID, item, description, commandOrder, beenRevealed) " +
                        "Values(" + commandID + ", '" + commandType + "', '" + command + "', "
                        + roomID + ", '" + item + "', '" + commandDescription.trim() + "', " + order + ", " + beenRevealed + ")";
                sdb.updateDB(sql);
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            throw new Exception(e.getMessage());
        }
        sdb.close();
    }

    /**
     * Method buildPlayer
     * Builds the player table
     * @param dbName dbName the name of the database to connect to
     * @throws Exception
     */
    private void buildPlayer(String dbName) throws Exception {
        sdb = new SQLiteDB(dbName);
        String sql = """
                CREATE TABLE Player (playerID INTEGER NOT NULL PRIMARY KEY,
                hitPoints INTEGER NOT NULL, attackPower INTEGER NOT NULL,
                accountID INTEGER NOT NULL, currentRoom INTEGER NOT NULL,
                FOREIGN KEY(accountID) REFERENCES Account(accountID))
                """;
        sdb.updateDB(sql);

        SecureRandom randomGenerator = new SecureRandom();
        int playerID = randomGenerator.nextInt(100_000);
        int accountID = new Account().getAccountID();
        int currentRoom = 0;
        int hitPoints = 100;
        int attackPower = 25;
        sql = "INSERT INTO Player(playerID, hitPoints, attackPower, accountID, currentRoom) " +
                "Values(" + playerID + ", " + hitPoints + ", " + attackPower + ", " + accountID + ", " + currentRoom + ")";
        sdb.updateDB(sql);
        sdb.close();
    }

    /**
     * Method buildMiniBoss
     * Builds the MiniBoss table
     * @param dbName dbName the name of the database to connect to
     * @throws Exception
     */
    private void buildMiniBoss(String dbName) throws Exception {
        sdb = new SQLiteDB(dbName);
        String sql = """
                CREATE TABLE MiniBoss (monsterID INTEGER not null Primary Key,
                name TEXT not null, roomID INTEGER not null, failMessage TEXT not null,
                FOREIGN KEY(roomID) REFERENCES Room(roomID))
                """;
        sdb.updateDB(sql);

        FileReader fr;
        try {
            fr = new FileReader("src/textfiles/miniboss.txt");
            Scanner inFile = new Scanner(fr);
            while (inFile.hasNextLine()) {
                int monsterID = Integer.parseInt(inFile.nextLine());
                String monsterName = inFile.nextLine();
                int roomID = Integer.parseInt(inFile.nextLine());
                String failMessage = inFile.nextLine();
                inFile.nextLine();
                int isDefeated = 0;
                sql = "INSERT INTO MiniBoss(monsterID, name, roomID, failMessage) " +
                        "Values(" + monsterID + ", '" + monsterName + "', " + roomID + ", '" + failMessage + "')";
                sdb.updateDB(sql);
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            throw new Exception(e.getMessage());
        }
        sdb.close();
    }
}