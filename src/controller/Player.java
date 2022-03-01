import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class: Player
 *
 * @author Ekechi Nzewi
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/09/2021
 * Description: this class contains logic for player objects
 */
public class Player {
    private static int hitPoints;
    private static int attackPower;
    private static int currentRoom;
    private int playerID;
    private static ArrayList<Item> inventory;
    private final PlayerDB playerDB;
    private static Player instance;

    /**
     * Method addItem
     * Adds an item to the player's inventory
     *
     * @param item the Item to add to the inventory
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected void addItem(Item item) throws SQLException, ClassNotFoundException {
        inventory.add(item);
        ItemDB itemDB = new ItemDB();
        itemDB.addToPlayerInventory(item);
    }

    /**
     * default no-arg constructor
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected Player() throws SQLException, ClassNotFoundException {
        instance = this; // instantiates the instance

        if (inventory == null) {
            inventory = new ArrayList<>(); // instantiates the inventory ArrayList
        }

        this.playerDB = new PlayerDB();
        hitPoints = playerDB.getHitPoints();
        attackPower = playerDB.getAttackPower();
        currentRoom = playerDB.getCurrentRoom(); // initializes the player's current room to the first room
    }

    /**
     * Method getInstance
     * This is a static method to implement the singleton pattern.
     * This allows the other objects to get a reference to this class and ensure that they have the same reference.
     * This is required since we are storing the Rooms in ArrayList and not in a database.
     * This ensures that we have the correct persistence objects.
     * If instance is null, the private constructor is called.
     * If it is not, it simply returns the instance
     *
     * @return Player - the current Player object.
     */
    public static Player getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            new Player(); // if there is not current instance of Player, a new one will be created
        }
        return instance; // returns the instance
    }

    /**
     * Method removeItem
     * Removes an item from the player's inventory
     *
     * @param item the Item to remove from the inventory
     */
    protected void removeItem(Item item) throws SQLException, ClassNotFoundException {
        inventory.remove(item);
        ItemDB itemDB = new ItemDB();
        itemDB.removeFromPlayerInventory(item);
    }

    /**
     * Method displayInventory
     * Returns the String of all items in the player's inventory
     *
     * @return String - the String of the player's inventory
     */
    protected String displayInventory() {
        return (inventory.isEmpty()) ? "You have no items in your inventory"
                : "You have the following items in your inventory" + "\n" + inventory;
    }

    /**
     * Method getHitPoints
     * Retrieves the player's current hitPoints
     *
     * @return int - the amount of health the player has
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Method getAttackPower
     * Retrieves the player's current attackPower
     *
     * @return int - the strength of the player's attack
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Method getPlayerID
     * Retrieves the player's ID
     *
     * @return int - the id of the player
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Method getCurrentRoom
     * Retrieves the room the player is currently in
     *
     * @return int - the id of the current room
     */
    public int getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Method setHitPoints
     * Updates the player's hitPoints
     *
     * @param hitPoints the HP being set
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void setHitPoints(int hitPoints) throws SQLException, ClassNotFoundException {
        Player.hitPoints = hitPoints;
        playerDB.updateHitPoints(hitPoints);
    }

    /**
     * Method setAttackPower
     * Updates the player's attackPower
     *
     * @param attackPower the attackPower being set
     */
    public void setAttackPower(int attackPower) {
        Player.attackPower = attackPower;
    }

    /**
     * Method setPlayerID
     * Updates the player's ID
     *
     * @param playerID the playerID being set
     */
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     * Method setCurrentRoom
     * Sets the room the player is currently in
     *
     * @param currentRoom the id of the current room
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void setCurrentRoom(int currentRoom) throws SQLException, ClassNotFoundException {
        Player.currentRoom = currentRoom;
        playerDB.updateCurrentRoom(currentRoom);
    }

    /**
     * Method setCurrentRoom
     * Restore player hp
     *
     * @param hp the amount of hp to increment by
     */
    public void addHP(int hp) throws SQLException, ClassNotFoundException {
        hitPoints += hp;
        playerDB.updateHitPoints(hitPoints);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Method emptyInventory
     * Empties the player's inventory
     */
    public void emptyInventory() throws SQLException, ClassNotFoundException {
        inventory = new ArrayList<>();
        ItemDB itemDB = new ItemDB();
        itemDB.emptyPlayerInventory();
    }
}