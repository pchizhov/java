package ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.console.Console;
import bl.DatabaseManager;


public class Main extends Application {

    public static void main(String[] args) {
        if (DatabaseManager.connect()) {
//            System.out.println("Do you want to run gui? (y/n)");
//            Scanner reader = new Scanner(System.in);
//            switch (reader.next()){
//                case "y":
                    launch(args);
//                default:
//                    Console.runConsole(args);
//            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Authorisation.fxml"));
        primaryStage.setTitle("Authorisation");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }
}
