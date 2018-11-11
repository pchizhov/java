package bl.entities;

public class BLUser {

    private String login;
    private String name;
    private Role role;
    private BLGroup group;

    public BLUser(String login, String name, Role role, BLGroup group) {
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

    public BLGroup getGroup() {
        return group;
    }
}
