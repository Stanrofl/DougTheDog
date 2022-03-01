/**Class: Monster
 * @author Sean Hetherington
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: July 8, 2021
 *
 * This class creates an instance of a Monster for the player to fight in a given Room
 * as well as initiates a fight between the player and the monster when the player
 * inputs the proper command, and then generates the fight log.
 */

public class Monster {

    private int monsterID;
    private String name;
    private Boolean isDefeated;
    private int hitPoints;
    private int attackPower;
    private int roomID;
    private String winMessage;
    private String failMessage;

    /**
     * Method: getMonsterID
     * Getter for monsterID variable
     *
     * @return monsterID variable
     */
    public int getMonsterID() {
        return monsterID;
    }

    /**
     * Method: setMonsterID
     * Setter for monsterID variable
     *
     * @param monsterID numerical ID for instance of Monster object
     */
    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }

    /**
     * Method: getName
     * Getter for name variable
     *
     * @return name variable
     */
    public String getName() {
        return name;
    }

    /**
     * Method: setName
     * Setter for name variable
     *
     * @param name name of the monster for the instance of the Monster object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method: getDefeated
     * Getter for defeated variable
     *
     * @return defeated variable
     */
    public Boolean getDefeated() {
        return isDefeated;
    }

    /**
     * Method: setDefeated
     * Setter for defeated variable
     *
     * @param defeated determines whether or not the instance of the Monster object has been defeated
     */
    public void setDefeated(Boolean defeated) {
        isDefeated = defeated;
    }

    /**
     * Method: getHitPoints
     * Getter for hitPoints variable
     *
     * @return hitPoints variable
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Method: setHitPoints
     * Setter for hitPoints variable
     *
     * @param hitPoints the amount of damage the instance of the Monster object can take from the player's attacks.
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Method: getAttackPower
     * Getter for attackPower variable
     *
     * @return attackPower variable
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Method: setAttackPower
     * Setter for attackPower variable
     *
     * @param attackPower the default base amount of damage that the instance of the Monster object can do to the player
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * Method: getRoomID
     * Getter for roomID variable
     *
     * @return roomID variable
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * Method: setRoomID
     * Setter for roomID variable
     *
     * @param roomID the numerical ID for the instance of the Room object
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    /**
     * Method: getWinMessage
     * Getter for winMessage variable
     *
     * @return winMessage variable
     */
    public String getWinMessage() {
        return winMessage;
    }

    /**
     * Method: setWinMessage
     * Setter for winMessage variable
     *
     * @param winMessage the message displayed to the player upon defeating the instance of the Monster object
     */
    public void setWinMessage(String winMessage) {
        this.winMessage = winMessage;
    }

    /**
     * Method: getFailMessage
     * Getter for failMessage variable
     *
     * @return failMessage variable
     */
    public String getFailMessage() {
        return failMessage;
    }

    /**
     * Method: setFailMessage
     * Setter for failMessage variable
     *
     * @param failMessage the message displayed to the player upon being defeated by the instance of the Monster object
     */
    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    /**
     * Method: toString
     * creates a string containing all of the relevant info that the player needs to
     * know about the given instance of the Monster object.
     *
     * @return a string displaying the relevant info that the player needs to
     * know about the given instance of the Monster object.
     */
    @Override
    public String toString() {
        return "Monster #" + monsterID +
                ": " + name + "\n" +
                "HP: " + hitPoints + System.lineSeparator() +
                "Attack: " + attackPower;
    }

    public Monster(int monsterID, String name, Boolean isDefeated, int hitPoints, int attackPower, int roomID, String winMessage, String failMessage) {
        this.monsterID = monsterID;
        this.name = name;
        this.isDefeated = isDefeated;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
        this.roomID = roomID;
        this.winMessage = winMessage;
        this.failMessage = failMessage;
    }
}