package bl.entities;

public class Lesson {

    private String name;
    private String description;

    public Lesson(String name, String description) {
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
