/**
 * Class: Exit
 * @author Nan Zhou
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/07/2021
 * Description: this class contains logic for Exit class
 */

public class Exit {

    private String direction;
    private int destination;
    private int exitID;
    private int roomID;

    /**
     * Constructor that takes in three parameters
     * @param exitID
     * @param destination
     * @param direction
     * @param roomID
     */
    public Exit(int exitID, int destination, String direction, int roomID) {
        this.exitID = exitID;
        this.destination = destination;
        this.direction = direction;
        this.roomID = roomID;
    }

    /**
     * Getter method for direction
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Getter method for destination
     * @return the destination
     */
    public int getDestination() {
        return destination;
    }

    /**
     * Getter Method for exitID
     * @return the exitID
     */
    public int getExitID() {
        return exitID;
    }

    /**
     * Getter method for roomID
     * @return the roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * Setter method for direction
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Setter method for destination
     * @param destination
     */
    public void setDestination(int destination) {
        this.destination = destination;
    }

    /**
     * Setter method for exitID
     * @param exitID
     */
    public void setExitID(int exitID) {
        this.exitID = exitID;
    }

    /**
     * Setter method for roomID
     * @param roomID
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

}