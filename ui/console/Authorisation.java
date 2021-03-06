package ui.console;

import java.lang.System;
import java.util.Scanner;

import bl.entities.User;
import bl.LoginManager;
import ui.Context;

public class Authorisation {

    private final LoginManager lM;
    private Context _ctx = Context.get();

    public Authorisation(LoginManager lM){
        this.lM = lM;
    }

    public void authorise(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Please, sign into your account.");
        System.out.print("Login: ");
        String login = reader.next();
        System.out.print("Password: ");
        String password = reader.next();

        User user = lM.signIn(login, password);

        if (user == null) {
            System.out.println("Wrong data, try again.\n");
            this.authorise();
        } else {
            System.out.println("Welcome, " + user.getName() + "\n");
            _ctx.setUser(user);
        }
    }

}
