package db.entities;

public class GroupDBE {

    private String number;
    private String level;

    public GroupDBE(String number, String level){
        this.number = number;
        this.level = level;
    }

    public String getNumber(){
        return number;
    }

    public String getLevel(){
        return level;
    }

}