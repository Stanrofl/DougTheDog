import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class: Commands
 *
 * @author Nan Zhou
 * @version 1.0
 * Course: ITEC 3860 Summer 2021
 * Written: 07/07/2021
 * Description: this class handles command logic
 */

public class Commands {

    private Player player;
    private static int count = 1;

    /**
     * Default no-arg constructor
     */
    public Commands() throws SQLException, ClassNotFoundException {
    }

    /**
     * Validate commands for current room
     *
     * @param cmd
     * @throws GameException
     */
    public ValidCommand validCommand(String cmd) throws GameException, SQLException, ClassNotFoundException {
        ValidCommandDB validCommandDB = new ValidCommandDB();
        ValidCommand vc = new ValidCommand();
        ArrayList<ValidCommand> validCommands = validCommandDB.getValidCommands(player.getCurrentRoom(), Account.saveName);
        boolean valid = false;
        for (ValidCommand validCommand : validCommands) {
            if (cmd.equalsIgnoreCase(validCommand.getCmd())) {
                valid = true;
                vc = validCommand;
            }
        }
        if (valid) {
            return vc;
        } else {
            throw new GameException("Invalid command");
        }
    }

    /**
     * Call validCommand() for validation and execute command
     * based on its type
     *
     * @param cmd
     * @throws GameException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String executeCommand(String cmd) throws GameException, SQLException, ClassNotFoundException {
        this.player = new Player();
        revealCommand();
        if (!(new ValidCommandDB().checkExists(cmd))) {
            return switch (cmd.toLowerCase()) {
                case "help" -> help();
                case "exit" -> exit();
                case "inventory" -> player.displayInventory();
                case "health" -> "Your health is at " + player.getHitPoints() + ".";
                default -> throw new GameException("Invalid command");
            };
        } else {
            ValidCommand vc = validCommand(cmd);
            revealCommand(vc);
            if (vc.isBeenRevealed()) {
                return switch (vc.getType().toLowerCase()) {
                    case "move" -> move(vc);
                    case "normal" -> normal(vc);
                    case "item" -> item(vc);
                    case "riddle" -> riddle(vc);
                    case "battle" -> battle(vc);
                    case "hp" -> hp(vc);
                    case "proof" -> proof(vc);
                    default -> throw new GameException("Invalid command");
                };
            } else {
                throw new GameException("Invalid command");
            }
        }
    }

    /**
     * Move to the appropriate room based on valid command
     *
     * @param vc
     * @return next room description
     * @throws GameException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String move(ValidCommand vc) throws GameException, SQLException, ClassNotFoundException {
        return switch (vc.getCmd().toLowerCase()) {
            case "ring", "right":
                moveCondition(vc);
            default:
                RoomDB roomDB = new RoomDB();
                int destination = validDirection(vc);
                player.setCurrentRoom(destination);
                count = 1;
                yield roomDB.getRoom(destination, Account.saveName).displayRoom();
        };
    }

    /**
     * Check if conditions are met before progressing
     *
     * @param vc
     * @return next room description
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws GameException
     */
    public String moveCondition(ValidCommand vc) throws SQLException, ClassNotFoundException, GameException {
        RoomDB roomDB = new RoomDB();
        if (player.getInventory().size() == 4) {
            int destination = validDirection(vc);
            player.setCurrentRoom(destination);
            count = 1;
            return roomDB.getRoom(destination, Account.saveName).displayRoom();
        } else {
            return "Hmm, I don't have the required items to move on";
        }
    }

    /**
     * Query ExitDB for exits in current room
     * and move according to player command
     *
     * @param vc
     * @return a destination
     * @throws GameException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int validDirection(ValidCommand vc) throws GameException, SQLException, ClassNotFoundException {
        ExitDB exitDB = new ExitDB();
        ArrayList<Exit> exits = exitDB.getExits(vc.getRoomID(), Account.saveName);
        for (Exit exit : exits) {
            if (vc.getCmd().equalsIgnoreCase(exit.getDirection())) {
                return exit.getDestination();
            }
        }
        throw new GameException("Invalid direction");
    }

    /**
     * Display result of player action for normal commands
     *
     * @param vc
     * @return String output of normal command
     */
    public String normal(ValidCommand vc) {
        return vc.getDescription();
    }

    /**
     * Display result of player action for item commands
     *
     * @param vc
     * @return String output of item command
     */
    public String item(ValidCommand vc) throws SQLException, ClassNotFoundException {
        if (vc.getCmd().equalsIgnoreCase("smoothie guy")) {
            ItemDB itemDB = new ItemDB();
            return itemDB.smoothieGuy(vc);
        }
        Item item = new ItemDB().getItem(vc.getItem());
        boolean exist = item.isWithPlayer();
        if (exist) {
            return "Item already exists in your inventory";
        } else {
            player.addItem(item);
            return vc.getDescription() + "\n" + "\n" + "Items received: " + item.getName() + "\n" + item.getDescription() + "\n";
        }
    }

    /**
     * Initiate riddle
     *
     * @param vc
     * @return String outcome of riddle
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String riddle(ValidCommand vc) throws SQLException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        if (vc.getRoomID() == 5) {
            return puzzle1(input);
        } else if (vc.getRoomID() == 17) {
            return puzzle2(input);
        } else {
            return question(input);
        }
    }

    /**
     * Display 3 questions for the player
     *
     * @param input
     * @return String of questions
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String question(Scanner input) throws SQLException, ClassNotFoundException {
        RoomDB roomDB = new RoomDB();
        System.out.println("""
                NOTE: Hi friend, you are 1 floor closer to finding your Doug.
                Finding the answer to this puzzle will give you the code to enter the next floor.
                The questions are in the order they should be typed into the keypad.
                1-     How many legs does Doug have?
                2-     How many glasses did you help the SMOOTHIE GUY find?
                3-     How many people did you have to battle on this floor?
                """);
        while (!input.nextLine().equalsIgnoreCase("411")) {
            System.out.println("Wrong answer, please try again");
        }
        player.setCurrentRoom(9);
        return roomDB.getRoom(9, Account.saveName).displayRoom();
    }

    /**
     * Initiate puzzle 1 with randomized riddles
     *
     * @param input
     * @return String outcome of puzzle
     */
    public String puzzle1(Scanner input) throws SQLException, ClassNotFoundException {
        PuzzleDB puzzleDB = new PuzzleDB();
        ArrayList<Puzzle> puzzles = puzzleDB.getPuzzles(5, Account.saveName);
        int random = (int) Math.round(Math.random()); // generate integer 0 or 1
        Puzzle currentPuzzle = puzzles.get(random);
        System.out.println(currentPuzzle.getDescription());
        while (currentPuzzle.getAttempt() != 0) {
            if (input.nextLine().equalsIgnoreCase(currentPuzzle.getAnswer())) {
                return currentPuzzle.getPassMessage();
            } else {
                currentPuzzle.attempted();
                System.out.println("Wrong answer, please try again");
            }
        }
        System.out.println();
        System.out.println("Looks like you didn’t get that one right. Hmm let’s try an easier one. The riddle is: ");
        currentPuzzle = puzzles.get(1 - random); // get whichever puzzle remains
        System.out.println(currentPuzzle.getDescription());
        while (currentPuzzle.getAttempt() != 0) {
            if (input.nextLine().equalsIgnoreCase(currentPuzzle.getAnswer())) {
                return currentPuzzle.getPassMessage();
            } else {
                currentPuzzle.attempted();
                System.out.println("Wrong answer, please try again");
            }
        }
        System.out.println("Hint: WATER,FIRE");
        String answer = input.nextLine();
        while (!answer.equalsIgnoreCase(currentPuzzle.getAnswer())) {
            System.out.println("Wrong answer, please try again");
            System.out.println("The riddle is: ");
            System.out.println(currentPuzzle.getDescription());
            answer = input.nextLine();
        }
        return currentPuzzle.getPassMessage();
    }

    /**
     * Initiate puzzle 2 that takes the player to room 18
     *
     * @param input
     * @return String outcome of puzzle
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String puzzle2(Scanner input) throws SQLException, ClassNotFoundException {
        PuzzleDB puzzleDB = new PuzzleDB();
        ArrayList<Puzzle> puzzles = puzzleDB.getPuzzles(17, Account.saveName);
        for (int i = 0; i < 2; i++) {
            Puzzle currentPuzzle = puzzles.get(i);
            System.out.println(currentPuzzle.getDescription());
            while (currentPuzzle.getAttempt() != 0) {
                if (input.nextLine().equalsIgnoreCase(currentPuzzle.getAnswer())) {
                    RoomDB roomDB = new RoomDB();
                    player.setCurrentRoom(18);
                    return roomDB.getRoom(18, Account.saveName).displayRoom();
                } else {
                    currentPuzzle.attempted();
                    System.out.println("Wrong answer, please try again");
                }
            }
        }
        System.out.println("Hint: SHERLOCK BONES, BLOODHOUND");
        String answer = input.nextLine();
        while (!answer.equalsIgnoreCase("bloodhound")) {
            System.out.println("Wrong answer, please try again");
            answer = input.nextLine();
        }
        RoomDB roomDB = new RoomDB();
        player.setCurrentRoom(18);
        return roomDB.getRoom(18, Account.saveName).displayRoom();
    }

    /**
     * Initiate battle with a monster
     *
     * @return String output of a battle
     */
    public String battle(ValidCommand vc) throws SQLException, ClassNotFoundException {
        RoomDB roomDB = new RoomDB();
        MonsterDB monsterDB = new MonsterDB();
        Monster monster = monsterDB.getMonster(vc.getRoomID());
        Player player = Player.getInstance();
        int playerHP = player.getHitPoints();
        int monsterHP = monster.getHitPoints();
        if (!monster.getDefeated()) {
            while (playerHP > 0 && monsterHP > 0) {
                playerHP -= monster.getAttackPower();
                monsterHP -= player.getAttackPower();
            }
            if (playerHP > 0) {
                player.setHitPoints(playerHP);
                monsterDB.updateIsDefeated(player.getCurrentRoom());
                return monster.getWinMessage();
            } else {
                player.setCurrentRoom(19);
                return monster.getFailMessage() + "\n\n\n" + "..." + "\n\n\n" + roomDB.getRoom(player.getCurrentRoom(), Account.saveName).displayRoom();
            }
        } else {
            return "You have already beat this boss";
        }
    }

    /**
     * Display all valid commands for current room
     *
     * @return list of valid commands
     */
    public String help() throws SQLException, ClassNotFoundException {
        ValidCommandDB validCommandDB = new ValidCommandDB();
        ArrayList<ValidCommand> validCommands = validCommandDB.getValidCommands(player.getCurrentRoom(), Account.saveName);
        StringBuilder output = new StringBuilder("Valid commands:\n");
        for (ValidCommand validCommand : validCommands) {
            if (validCommand.isBeenRevealed()) {
                output.append(validCommand.getCmd()).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Restore player hp based on command
     *
     * @param vc
     * @return hp added to player
     */
    public String hp(ValidCommand vc) throws SQLException, ClassNotFoundException {
        if (vc.getCmd().equalsIgnoreCase("massage")) {
            player.addHP(5);
        } else {
            player.addHP(10);
        }
        return vc.getDescription();
    }

    /**
     * Method proof
     * Validates proof valid commands and ensures they are input in the right order
     *
     * @param vc the proof command
     * @return String - output based on whether the proof is entered in the right order
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String proof(ValidCommand vc) throws SQLException, ClassNotFoundException {
        MiniBoss boss = new MiniBossDB().getMiniBoss(player.getCurrentRoom());
        Item item = new ItemDB().getItem(vc.getItem());
        if (vc.getCommandOrder() != count) {
            return boss.getFailMessage();
        } else {
            count++;
            for (Item currentItem : player.getInventory()) {
                if (vc.getItem().equalsIgnoreCase(currentItem.getName())) {
                    player.removeItem(currentItem); // the item is then removed from the player's inventory
                    break;
                }
            }
            if (count == 4) {
                player.emptyInventory();
            }
            return "Removed " + item.getName() + " from inventory." + "\n" + vc.getDescription();
        }
    }

    /**
     * Displays exit message
     *
     * @return exit message
     */
    public String exit() {
        return "no";
    }

    private void revealCommand() throws SQLException, ClassNotFoundException {
        ArrayList<ValidCommand> validCommands = new ValidCommandDB().getValidCommands(player.getCurrentRoom(), Account.saveName);
        Room currentRoom = new RoomDB().getRoom(player.getCurrentRoom(), Account.saveName);

        for (ValidCommand command : validCommands) {
            if (currentRoom.getDescription().contains(command.getCmd().toUpperCase())) {
                command.setBeenRevealed(true);
            }
        }
    }

    private void revealCommand(ValidCommand vc) throws SQLException, ClassNotFoundException {
        ArrayList<ValidCommand> validCommands = new ValidCommandDB().getValidCommands(player.getCurrentRoom(), Account.saveName);
        for (ValidCommand command : validCommands) {
            if (vc.getType().equalsIgnoreCase("battle")) {
                Monster monster = new MonsterDB().getMonster(player.getCurrentRoom());
                if (monster.getWinMessage().contains(command.getCmd().toUpperCase())) {
                    command.setBeenRevealed(true);
                }
            } else if (vc.getDescription().contains(command.getCmd().toUpperCase())) {
                command.setBeenRevealed(true);
            }
        }
        if (vc.getCmd().equalsIgnoreCase("pool pass")) {
            vc.setBeenRevealed(true);
        }
    }
}