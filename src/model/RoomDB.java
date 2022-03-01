import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class: RoomDB
 * @author Nan Zhou
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/05/2021
 * Description: this class handles interactions between the database and Room class
 */

public class RoomDB {

    /**
     * Get room based on roomID
     * @param roomID
     * @return a Room
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Room getRoom(int roomID, String dbName) throws SQLException, ClassNotFoundException {
        SQLiteDB sqLiteDB = new SQLiteDB(dbName);
        String sql = "SELECT * FROM Room WHERE roomID = " + roomID;
        ResultSet resultSet = sqLiteDB.queryDB(sql);
        if (resultSet.next()) {
            int id = resultSet.getInt("roomID");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Room room = new Room(id,name,description);
            sqLiteDB.close();
            return room;
        } else {
            sqLiteDB.close();
            throw new SQLException("Room " + roomID + " not found");
        }
    }
}