import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class: ValidCommandDB
 *
 * @author Ekechi Nzewi
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/06/2021
 * Description: this class controls the interaction with the validCommands table in the database
 */
public class ValidCommandDB {
    /**
     * Method getValidCommands
     * Retrieves a list of available commands for a room
     *
     * @param roomID the ID of the room containing the commands
     * @param dbName the name of the database with the tables
     * @return ArrayList<ValidCommand> - a list of available commands for a room
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<ValidCommand> getValidCommands(int roomID, String dbName) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB(dbName);
        ArrayList<ValidCommand> validCommands = new ArrayList<>();
        String sql = "Select * from ValidCommands WHERE roomID = " + roomID;

        ResultSet rs = sdb.queryDB(sql);
        while (rs.next()) {
            int commandID = rs.getInt("commandID");
            String commandType = rs.getString("type");
            String command = rs.getString("cmd");
            String commandDescription = rs.getString("description");
            String item = rs.getString("item");
            int commandOrder = rs.getInt("commandOrder");
            boolean beenRevealed = (rs.getInt("beenRevealed")) == 1;
            ValidCommand cmd = new ValidCommand(commandID, commandType, command, roomID, commandDescription, item, commandOrder, beenRevealed);
            validCommands.add(cmd);
        }
        sdb.close();
        return validCommands;
    }

    public boolean checkExists(String cmd) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB(Account.saveName);
        ArrayList<ValidCommand> validCommands = new ArrayList<>();
        String sql = "Select * from ValidCommands";

        ResultSet rs = sdb.queryDB(sql);
        while (rs.next()) {
            int commandID = rs.getInt("commandID");
            String commandType = rs.getString("type");
            String command = rs.getString("cmd");
            String commandDescription = rs.getString("description");
            String item = rs.getString("item");
            int roomID = rs.getInt("roomID");
            int commandOrder = rs.getInt("commandOrder");
            boolean beenRevealed = (rs.getInt("beenRevealed")) == 1;
            ValidCommand validCommand = new ValidCommand(commandID, commandType, command, roomID, commandDescription, item, commandOrder, beenRevealed);
            validCommands.add(validCommand);
        }
        sdb.close();

        for (ValidCommand command : validCommands) {
            if (command.getCmd().equalsIgnoreCase(cmd)) {
                return true;
            }
        }
        return false;
    }

    public void setBeenRevealed(int commandID) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB(Account.saveName);
        String sql = "UPDATE ValidCommands SET beenRevealed = ? WHERE commandID = " + commandID;
        PreparedStatement stmt = sdb.conn.prepareStatement(sql);
        stmt.setInt(1, 1);
        stmt.executeUpdate();
        sdb.close();
    }
}