import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class: MonsterDB
 *
 * @author Ekechi Nzewi
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/09/2021
 * Description: this class controls the interaction with the monster table in the database
 */
public class MonsterDB {
    private SQLiteDB sdb;

    /**
     * Method getMonster
     * returns a specific monster
     *
     * @param roomID the id of the room containing the monster
     * @return Monster - the monster in a specific room
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Monster getMonster(int roomID) throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB(Account.saveName);
        String sql = "Select * from Monster WHERE roomID = " + roomID;

        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            String monsterName = rs.getString("name");
            String winMessage = rs.getString("winMessage");
            String failMessage = rs.getString("failMessage");
            int monsterID = rs.getInt("monsterID");
            boolean defeated = (rs.getInt("isDefeated")) == 1;
            int hitPoints = rs.getInt("hitPoints");
            int attackPower = rs.getInt("attackPower");
            Monster monster = new Monster(monsterID, monsterName, defeated,
                    hitPoints, attackPower, roomID, winMessage, failMessage);
            sdb.close();
            return monster;
        } else {
            sdb.close();
            throw new SQLException();
        }
    }

    /**
     * Method updateIsDefeated
     * updates the monster's defeated status
     *
     * @param roomID the room where the monster resides
     * @throws SQLException
     */
    public void updateIsDefeated(int roomID) throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB(Account.saveName);
        String sql = "UPDATE Monster SET isDefeated = ? WHERE roomID = " + roomID;
        PreparedStatement stmt = sdb.conn.prepareStatement(sql);
        stmt.setInt(1, 1);
        stmt.executeUpdate();
        sdb.close();
    }
}