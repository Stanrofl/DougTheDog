import java.sql.SQLException;

/**
 * Class: Room
 * @author Nan Zhou
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/06/2021
 * Description: this class contains logic for Room objects
 */

public class Room {

    private int roomID;
    private String name;
    private String description;

    /**
     * no-arg constructor
     */
    public Room() {
    }

    /**
     * constructor that takes in three parameters
     * @param roomID
     * @param name
     * @param description
     */
    public Room(int roomID, String name, String description) {
        this.roomID = roomID;
        this.name = name;
        this.description = description;
    }

    /**
     * Display description of room upon entering
     * @return String description
     */
    public String displayRoom() {
        String output = "";
        output = name + "\n" + description;
        return output;
    }

    /**
     * Retrieve room based on roomID
     * @param roomID
     */
    public Room getRoom(int roomID, String dbName) throws SQLException, ClassNotFoundException {
        RoomDB roomDB = new RoomDB();
        return roomDB.getRoom(roomID,dbName);
    }

    /**
     * Getter method for roomID
     * @return the roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * Getter method for name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for roomID
     * @param roomID
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    /**
     * Setter method for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Custom toString method for Room objects
     * @return String output of current room
     */
    @Override
    public String toString() {
        return "Room{" +
                "roomID=" + roomID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}