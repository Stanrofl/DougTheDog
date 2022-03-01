import java.io.IOException;

/**
 * Class: GameException
 * @author Nan Zhou
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/07/2021
 * Description: this is a custom Exception class for handling all in-game exceptions
 */

public class GameException extends IOException {

    /**
     * No-arg constructor
     */
    public GameException() {
        super();
    }

    /**
     * Constructor that takes in a String
     * @param message
     */
    public GameException(String message) {
        super(message);
    }
}
