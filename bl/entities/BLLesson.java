package bl.entities;

public class BLLesson {

    private String name;
    private String description;

    public BLLesson(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
