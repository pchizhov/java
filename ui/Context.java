package ui;

import bl.entities.BLUser;

public class Context {

    private static Context _context = new Context();

    private BLUser user;

    private Context(){}

    public static Context get(){
        return _context;
    }

    public BLUser getUser() {
        return user;
    }

    public void setUser(BLUser user) {
        this.user = user;
    }
}
