import java.sql.SQLException;

/**
 * Class: ValidCommand
 * @author Nan Zhou
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/06/2021
 * Description: this class contains logic for ValidCommand objects
 */

public class ValidCommand {

    private int roomID;
    private int commandID;
    private int commandOrder;
    private String cmd;
    private String item;
    private String type;
    private String description;
    private boolean beenRevealed;

    /**
     * No-arg constructor
     */
    public ValidCommand() {}

    /**
     * Constructor that takes in five parameters
     * @param commandID
     * @param type
     * @param cmd
     * @param roomID
     * @param description
     */
    public ValidCommand(int commandID, String type, String cmd, int roomID, String description, String item, int order, boolean beenRevealed) {
        this.commandID = commandID;
        this.type = type;
        this.cmd = cmd;
        this.roomID = roomID;
        this.description = description;
        this.item = item;
        this.commandOrder = order;
        this.beenRevealed = beenRevealed;
    }

    /**
     * Getter method for roomID
     * @return the roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /** Getter method for item
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * Getter method for commandID
     * @return the commandID
     */
    public int getCommandID() {
        return commandID;
    }

    /**
     * Getter method for description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for type
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Getter method for command
     * @return a command
     */
    public String getCmd() {
        return cmd;
    }

    /**
     * Setter method for RoomID
     * @param roomID
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    /**
     * Setter method for CommandID
     * @param commandID
     */
    public void setCommandID(int commandID) {
        this.commandID = commandID;
    }

    /**
     * Setter method for description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter method for command
     * @param cmd
     */
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Setter method for type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    public int getCommandOrder() {
        return commandOrder;
    }

    public void setCommandOrder(int commandOrder) {
        this.commandOrder = commandOrder;
    }

    public boolean isBeenRevealed() {
        return beenRevealed;
    }

    public void setBeenRevealed(boolean beenRevealed) throws SQLException, ClassNotFoundException {
        this.beenRevealed = beenRevealed;
        new ValidCommandDB().setBeenRevealed(this.commandID);
    }

    /**
     * Custom toString method for current ValidCommand
     * @return String output for current ValidCommand
     */
    @Override
    public String toString() {
        return "ValidCommand{" +
                "commandID=" + commandID +
                ", type='" + type + '\'' +
                ", cmd='" + cmd + '\'' +
                ", roomID=" + roomID +
                ", description='" + description + '\'' +
                '}';
    }
}