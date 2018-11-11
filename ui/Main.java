package ui;

import db.repositories.UserRepo;
import bl.LoginManager;
import bl.DatabaseManager;

public class Main {

    public static void main(String[] args) {
        if (DatabaseManager.connect()) {
            new Authorisation(new LoginManager(new UserRepo())).authorise();
            new Menu().start();
            DatabaseManager.disconnect();
            System.out.println("Thank you for using");
        }
    }
}
