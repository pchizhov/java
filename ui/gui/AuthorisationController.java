package ui.gui;

import bl.LoginManager;
import bl.entities.Role;
import bl.entities.User;
import db.repositories.UserRepo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.Context;

import java.io.IOException;

public class AuthorisationController {

    public TextField login;
    public PasswordField password;
    public Button submit;
    public Label message;
    private final LoginManager lM = new LoginManager(new UserRepo());
    private Context context = Context.get();

    public void setLogin(ActionEvent actionEvent) throws IOException {
        this.setPassword(actionEvent);
    }

    public void setPassword(ActionEvent actionEvent) throws IOException {
        if (!login.getText().equals("")) {
            this.submit(actionEvent);
        }
    }

    public void submit(ActionEvent actionEvent) throws IOException{
        User user = lM.signIn(login.getText(), password.getText());
        if (user == null) {
            login.setText("");
            password.setText("");
            message.setText("Wrong data, try again");
            message.setTextFill(Color.RED);
        } else {
            message.setText("Welcome!");
            message.setTextFill(Color.GREEN);
            context.setUser(user);
            this.nextStage();
        }
    }

    private void nextStage() throws IOException {
        Stage stage = (Stage) message.getScene().getWindow();
        stage.close();
        String resource = "";
        System.out.println((context.getUser().getRole()));
        switch (context.getUser().getRole()) {
            case Student:
                resource = "StudentJournal.fxml";
            case Teacher:
                resource = "TeacherJournal.fxml";
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setTitle("Journal");
        newStage.setScene(new Scene(root, 800, 600));
        newStage.show();
    }
}
