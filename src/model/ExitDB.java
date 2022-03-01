import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class: ExitDB
 * @author Nan Zhou
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/07/2021
 * Description: this class handles interactions between the database and Exit class
 */

public class ExitDB {

    /**
     * Query the database and retrieve all exits based on roomID
     * @param roomID
     * @param dbName
     * @return ArrayList of exits
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Exit> getExits(int roomID, String dbName) throws SQLException, ClassNotFoundException {
        SQLiteDB sqLiteDB = new SQLiteDB(dbName);
        ArrayList<Exit> exits = new ArrayList<>();
        String sql = "SELECT * FROM Exit WHERE roomID = " + roomID;
        ResultSet resultSet = sqLiteDB.queryDB(sql);
        while (resultSet.next()) {
            int exitID = resultSet.getInt("exitID");
            int rID = resultSet.getInt("roomID");
            int destination = resultSet.getInt("destination");
            String direction = resultSet.getString("direction");
            Exit exit = new Exit(exitID,destination,direction,rID);
            exits.add(exit);
        }
        sqLiteDB.close();
        return exits;
    }

}