package db.entities;

public class UserDBE {

    private String login;
    private String password;
    private String name;
    private String role;
    private GroupDBE group;

    public UserDBE(String login, String password, String name, String role, GroupDBE group){
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

    public GroupDBE getGroup() {
        return group;
    }
}