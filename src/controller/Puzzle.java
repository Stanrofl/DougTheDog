/**
 * Class: Puzzle
 * @author Nan Zhou
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/08/2021
 * Description: this class handles puzzle logic
 */

public class Puzzle {

    private String description;
    private int isSolved;
    private String answer;
    private int puzzleID;
    private int roomID;
    private int attempt;
    private String passMessage;

    /**
     * Default no-arg constructor
     */
    public Puzzle(String description, String answer, int puzzleID, int roomID, String passMessage) {
        this.description = description;
        this.answer = answer;
        this.puzzleID = puzzleID;
        this.roomID = roomID;
        this.passMessage = passMessage;
        this.isSolved = 0;
        this.attempt = 3;
    }

    /**
     * Minus 1 attempt for every failed attempt
     */
    public void attempted() {
        attempt--;
    }

    /**
     * Getter method for description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for answer
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Getter method for roomID
     * @return the roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * Getter method for puzzleID
     * @return the puzzleID
     */
    public int getPuzzleID() {
        return puzzleID;
    }

    /**
     * Getter method for isSolved
     * @return 0 for not solved, 1 for solved
     */
    public int getIsSolved() {
        return isSolved;
    }

    /**
     * Getter method for attempt
     * @return the number of attempts remaining
     */
    public int getAttempt() {
        return attempt;
    }

    /**
     * Getter method for passMessage
     * @return the passMessage
     */
    public String getPassMessage() {
        return passMessage;
    }

    /**
     * Setter method for description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter method for answer
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Setter method for roomID
     * @param roomID
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    /**
     * Setter method for puzzleID
     * @param puzzleID
     */
    public void setPuzzleID(int puzzleID) {
        this.puzzleID = puzzleID;
    }

    /**
     * Set the puzzle to sovled
     */
    public void setIsSolved() {
        this.isSolved = 1;
    }

    /**
     * Setter method for attempt
     * @param attempt
     */
    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    /**
     * Setter method for passMessage
     * @param passMessage
     */
    public void setPassMessage(String passMessage) {
        this.passMessage = passMessage;
    }
}