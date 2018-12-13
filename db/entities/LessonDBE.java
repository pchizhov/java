package db.entities;

public class LessonDBE {

    private String name;
    private String description;

    public LessonDBE(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

}