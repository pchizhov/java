package bl.entities;

public class Group {

    private String number;
    private String level;

    public Group(String number, String level) {
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
