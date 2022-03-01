import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class: ItemDB
 *
 * @author Ekechi Nzewi
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/09/2021
 * Description: this class controls the interaction with the item table in the database
 */
public class ItemDB {
    private SQLiteDB sdb;

    /**
     * Method addToPlayerInventory
     * Adds an item to the player's inventory
     *
     * @param item the item being added to the inventory
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addToPlayerInventory(Item item) throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB(Account.saveName);
        String sql = "UPDATE Item SET withPlayer = ? WHERE itemID = " + item.getItemID();
        PreparedStatement stmt = sdb.conn.prepareStatement(sql);
        stmt.setInt(1, 1);
        stmt.executeUpdate();
        sdb.close();
    }

    /**
     * Method removeFromPlayerInventory
     * Removes an item from the player's inventory
     *
     * @param item the item being removed from the inventory
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void removeFromPlayerInventory(Item item) throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB(Account.saveName);
        String sql = "UPDATE Item SET withPlayer = ? WHERE itemID = " + item.getItemID();
        PreparedStatement stmt = sdb.conn.prepareStatement(sql);
        stmt.setInt(1, 0);
        stmt.executeUpdate();
        sdb.close();
    }

    /**
     * Method emptyPlayerInventory
     * Empties the player's inventory
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void emptyPlayerInventory() throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB(Account.saveName);
        String sql = "UPDATE Item SET withPlayer = ? WHERE withPlayer = " + 1;
        PreparedStatement stmt = sdb.conn.prepareStatement(sql);
        stmt.setInt(1, 0);
        stmt.executeUpdate();
        sdb.close();
    }

    /**
     * Method getItem
     * Retrieves an item from the database
     *
     * @param itemID the ID of the item being retrieved
     * @return Item - the item being retrieved
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Item getItem(int itemID) throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB(Account.saveName);
        String sql = "Select * from Item WHERE itemID = " + itemID;

        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            String itemName = rs.getString("name");
            String itemDescription = rs.getString("description");
            boolean withPlayer = (rs.getInt("withPlayer")) == 1;
            Item item = new Item(itemID, itemName, itemDescription, withPlayer);
            sdb.close();
            return item;
        } else {
            sdb.close();
            throw new SQLException();
        }
    }

    /**
     * Method getItem
     * Retrieves an item from the database
     *
     * @param itemName the name of the item
     * @return Item - the item being retrieved
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Item getItem(String itemName) throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB(Account.saveName);
        String sql = "Select * from Item WHERE name like '" + itemName + "'";

        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            String name = rs.getString("name");
            int itemID = rs.getInt("itemID");
            String itemDescription = rs.getString("description");
            boolean withPlayer = (rs.getInt("withPlayer")) == 1;
            Item item = new Item(itemID, name, itemDescription, withPlayer);
            sdb.close();
            return item;
        } else {
            sdb.close();
            throw new SQLException();
        }
    }

    /**
     * Method smoothieGuy
     * Returns the result of the smoothie guy command
     *
     * @param validCommand the command being checked
     * @return String - result of the command
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String smoothieGuy(ValidCommand validCommand) throws SQLException, ClassNotFoundException {
        Player player = Player.getInstance();
        Item glasses = getItem(validCommand.getItem());

        if (glasses.isWithPlayer()) {
            player.emptyInventory();
            return validCommand.getDescription();
        } else {
            return "Oh, hey can’t talk right now but if you can help me collect my glasses I can chat then. They are somewhere around here. You might have to EXPLORE a bit to find them.";
        }
    }
}