/**Class: Item
 * @author Sean Hetherington
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: July 7, 2021
 *
 * This class creates a class object called Item, which is used to represent items collected by the player
 * either by exploring, defeating monsters, or solving puzzles.
 */

public class Item {

    private int itemID;
    private String name;
    private String description;
    private boolean withPlayer;

    /**
     * Method: getItemID
     * Getter for itemID variable.
     * @return the numerical ID for a given instance of the Item object.
     */
    public int getItemID () {
        return itemID;
    }

    /**
     * Method: setItemID
     * Setter for ItemID variable.
     * @param itemID the current instance of the numerical ID for an instance of the Item object
     */
    public void setItemID (int itemID) {
        this.itemID = itemID;
    }

    /**
     * Method: getName
     * Getter for name variable.
     * @return the name of the given instance of the Item object.
     */
    public String getName () {
        return name;
    }
    /**
     * Method: setName
     * Setter for name variable.
     * @param name the name of the current instance of the Item object
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * Method: getDescription
     * Getter for description variable.
     * @return the description of the current instance of the Item object.
     */
    public String getDescription () {
        return description;
    }

    /**
     * Method: setDescription
     * Setter for description variable.
     * @param description the description of the current instance of the Item object.
     */
    public void setDescription (String description) {
        this.description = description;
    }

    public boolean isWithPlayer() {
        return withPlayer;
    }

    public void setWithPlayer(boolean withPlayer) {
        this.withPlayer = withPlayer;
    }

    /**
     * Method: toString
     * Returns a string containing the info for a given instance of the Item object.
     * @return a string containing the info for a given instance of Item.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Method: toStringPlayer
     * Returns the version of the toString method's string that the player will see when they receive an item.
     * @return a string containing the information for an item in a user-friendly format.
     */
    public String toStringPlayer() {
        return "Item{" +
                "Item #" + itemID +
                ": '" + name + '\'' + System.lineSeparator() +
                "'" + description + '\'' + System.lineSeparator() + '}';
    }

    /**
     * Method: Item
     * Constructor for the Item class object.
     * @param itemID ID number used to identify the item
     * @param name the name of the item
     * @param description a short paragraph describing the item
     * @param withPlayer boolean representing whether a player has an item or not
     */
    public Item(int itemID, String name, String description, boolean withPlayer) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.withPlayer = withPlayer;
    }
}