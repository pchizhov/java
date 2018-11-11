package db.entities;

public class User {

    private String login;
    private String password;
    private String name;
    private String role;
    private Group group;

    public User(String login, String password, String name, String role, Group group){
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
        this.group = group;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public Group getGroup() {
        return group;
    }
}