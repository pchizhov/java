package bl.entities;

public class User {

    private String login;
    private String name;
    private Role role;
    private Group group;

    public User(String login, String name, Role role, Group group) {
        this.login = login;
        this.name = name;
        this.role = role;
        this.group = group;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public Group getGroup() {
        return group;
    }
}
