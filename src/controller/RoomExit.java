/**Class: RoomExit
 * @author Sean Hetherington
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: July 8, 2021
 *
 * This class serves as a connection between the Room and Exit classes
 * that makes it easier to create proper tables in the SQL Database tied to the code.
 */

public class RoomExit {

    private int roomID;
    private int exitID;

    /**
     * Method: getRoomID
     * Getter for roomID variable.
     * @return the numerical ID for a given instance of the Room object.
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * Method: setRoomID
     * Setter for roomID variable.
     * @param roomID the numerical ID for a given instance of the Room object.
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    /**
     * Method: getExitID
     * Getter for exitID variable.
     * @return the numerical ID for a given instance of the Exit object.
     */
    public int getExitID() {
        return exitID;
    }

    /**
     * Method: setExitID
     * Setter for exitID variable.
     * @param exitID the numerical ID for a given instance of the Exit object.
     */
    public void setExitID(int exitID) {
        this.exitID = exitID;
    }

    /**
     * Method: RoomExit
     * Constructor for the RoomExit class object.
     * @param roomID the numerical ID for a given instance of the Room object.
     * @param exitID the numerical ID for a given instance of the Exit object.
     */
    public RoomExit(int roomID, int exitID) {
        this.roomID = roomID;
        this.exitID = exitID;
    }
}