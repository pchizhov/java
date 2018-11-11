package bl.entities;

public class BLGroup {

    private String number;
    private String level;

    public BLGroup(String number, String level) {
        this.number = number;
        this.level = level;
    }

    public String getNumber() {
        return number;
    }

    public String getLevel() {
        return level;
    }
}
