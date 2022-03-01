import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class: PuzzleDB
 * @author kello williams
 * @version 1.0
 * Course: ITEC 3860 Software Development
 * Date: 07/09/2021
 * Purpose: This class deals with interactions with database and puzzle class
 */
public class PuzzleDB {

    /**
     * Query database for puzzles based on roomID
     * @param roomID
     * @param dbName
     * @return ArrayList of puzzles
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Puzzle> getPuzzles(int roomID, String dbName) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB(dbName);
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        String sql = "Select * from Puzzle WHERE roomID = " + roomID;
        ResultSet rs = sdb.queryDB(sql);
        while (rs.next()) {
            int pID = rs.getInt("puzzleID");
            int rID = rs.getInt("roomID");
            String description = rs.getString("description");
            String answer = rs.getString("answer");
            String passMessage = rs.getString("passMessage");
            Puzzle puzzle = new Puzzle(description,answer,pID,rID,passMessage);
            puzzles.add(puzzle);
        }
        sdb.close();
        return puzzles;
    }
}