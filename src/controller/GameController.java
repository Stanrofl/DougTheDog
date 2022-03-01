import java.io.File;
import java.sql.SQLException;

/**
 * Class: GameController
 * @author Nan Zhou
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/10/2021
 * Description: this class handles interactions between view model and commands logic
 */

public class GameController {
    private Commands commands;

    /**
     * Default no-arg constructor
     */
    public GameController() throws SQLException, ClassNotFoundException {
        this.commands = new Commands();
    }

    /**
     * Executes player commands
     * @param cmd
     * @return String outcome of player command
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws GameException
     */
    public String executeCommand(String cmd) throws SQLException, ClassNotFoundException, GameException {
        return commands.executeCommand(cmd);
    }

    /**
     * Display current room description
     * @return room description
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String displayCurrentRoom() throws SQLException, ClassNotFoundException {
        PlayerDB playerDB = new PlayerDB();
        RoomDB roomDB = new RoomDB();
        return roomDB.getRoom(playerDB.getCurrentRoom(), Account.saveName).displayRoom();
    }

}