public class MiniBoss {
    private String failMessage;
    private String name;
    private int monsterID;
    private int roomID;

    public MiniBoss(String failMessage, String name, int monsterID, int roomID) {
        this.failMessage = failMessage;
        this.name = name;
        this.monsterID = monsterID;
        this.roomID = roomID;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
}
