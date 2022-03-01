import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class: MiniBossDB
 *
 * @author Ekechi Nzewi
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/11/2021
 * Description: this class controls the interaction with the MiniBoss table in the database
 */
public class MiniBossDB {
    private SQLiteDB sdb;

    /**
     * Method getMiniBoss
     * returns a specific miniBoss
     *
     * @param roomID the id of the room containing the mini boss
     * @return MiniBoss - the monster in a specific room
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public MiniBoss getMiniBoss(int roomID) throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB(Account.saveName);
        String sql = "Select * from MiniBoss WHERE roomID = " + roomID;

        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            int monsterID = rs.getInt("monsterID");
            String monsterName = rs.getString("name");
            String failMessage = rs.getString("failMessage");
            MiniBoss boss = new MiniBoss(failMessage, monsterName, monsterID, roomID);
            sdb.close();
            return boss;
        } else {
            sdb.close();
            throw new SQLException();
        }
    }
}
