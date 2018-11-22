package ui.gui;

import bl.LoginManager;
import bl.entities.BLUser;
import db.repositories.UserRepo;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ui.Context;

public class AuthorisationController {

    public TextField login;
    public PasswordField password;
    public Button submit;
    public Label message;
    private final LoginManager lM = new LoginManager(new UserRepo());
    private Context context = Context.get();

    public void submit(ActionEvent actionEvent) {
        BLUser user = lM.signIn(login.getText(), password.getText());
        if (user == null) {
            login.setText("");
            password.setText("");
            message.setText("Wrong data, try again");
            message.setTextFill(Color.RED);
        } else {
            message.setText("Welcome!");
            message.setTextFill(Color.GREEN);
            context.setUser(user);
        }
    }
}
