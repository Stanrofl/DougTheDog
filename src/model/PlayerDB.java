import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class: PlayerDB
 *
 * @author Ekechi Nzewi
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/08/2021
 * Description: this class controls the interaction with the player table in the database
 */
public class PlayerDB {
    private SQLiteDB sdb;

    /**
     * Method updateHitPoints
     * updates the player's hit points in the database
     * @param hitPoints the new hit point being uploaded to the database
     * @throws SQLException
     */
    public void updateHitPoints(int hitPoints) throws SQLException, ClassNotFoundException {
        sdb  = new SQLiteDB(Account.saveName);
        String sql = "UPDATE Player SET hitPoints = ? ";
        PreparedStatement stmt = sdb.conn.prepareStatement(sql);
        stmt.setInt(1, hitPoints);
        stmt.executeUpdate();
        sdb.close();
    }

    /**
     * Method updateCurrentRoom
     * updates the player's current room in the database
     * @param currentRoom the new room being uploaded to the database
     * @throws SQLException
     */
    public void updateCurrentRoom(int currentRoom) throws SQLException, ClassNotFoundException {
        sdb  = new SQLiteDB(Account.saveName);
        String sql = "UPDATE Player SET currentRoom = ? ";
        PreparedStatement stmt = sdb.conn.prepareStatement(sql);
        stmt.setInt(1, currentRoom);
        stmt.executeUpdate();
        sdb.close();
    }

    /**
     * Method getHitPoints
     * gets the player's hit points from the database
     * @return int - the player's hit points
     * @throws SQLException
     */
    public int getHitPoints() throws SQLException, ClassNotFoundException {
        sdb  = new SQLiteDB(Account.saveName);
        String sql = "Select hitPoints from Player";

        ResultSet rs = sdb.queryDB(sql);
        int hitPoints = 0;
        while (rs.next()) {
            hitPoints = rs.getInt("hitPoints");
        }
        sdb.close();
        return hitPoints;
    }

    /**
     * Method getCurrentRoom
     * gets the player's current room from the database
     * @return int - the player's current room
     * @throws SQLException
     */
    public int getCurrentRoom() throws SQLException, ClassNotFoundException {
        sdb  = new SQLiteDB(Account.saveName);
        String sql = "Select currentRoom from Player";

        ResultSet rs = sdb.queryDB(sql);
        int currentRoom = 0;
        while (rs.next()) {
            currentRoom = rs.getInt("currentRoom");
        }
        sdb.close();
        return currentRoom;
    }

    /**
     * Method getAttackPower
     * gets the player's attack power from the database
     * @return int - the player's attack power
     * @throws SQLException
     */
    public int getAttackPower() throws SQLException, ClassNotFoundException {
        sdb  = new SQLiteDB(Account.saveName);
        String sql = "Select attackPower from Player";

        ResultSet rs = sdb.queryDB(sql);
        int attackPower = 0;
        while (rs.next()) {
            attackPower = rs.getInt("attackPower");
        }
        sdb.close();
        return attackPower;
    }
}