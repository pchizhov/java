package ui.console;

import db.Connector;
import db.repositories.UserRepo;
import bl.LoginManager;
import bl.DatabaseManager;
import ui.console.Authorisation;
import ui.console.Menu;


public class Console {

    public static void runConsole(String[] args) {
        if (DatabaseManager.connect()) {
            new Authorisation(new LoginManager(new UserRepo())).authorise();
            new Menu().start();
            DatabaseManager.disconnect();
        }
    }
}
