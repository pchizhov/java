package ui;

import bl.entities.User;

public class Context {

    private static Context _context = new Context();

    private User user;

    private Context(){}

    public static Context get(){
        return _context;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
