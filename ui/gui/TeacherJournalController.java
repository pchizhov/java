package ui.gui;

import bl.task.BackgroundOperation;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import ui.Context;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class TeacherJournalController implements Initializable {

    private Context context = Context.get();
    public Label greeting;
    public ProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        greeting.setText("Welcome, " + context.getUser().getName() + "!");
    }


    public void showTable(ActionEvent actionEvent) {
        ProgressListener pl = new ProgressListener(progressBar);
        BackgroundOperation bo = new BackgroundOperation();
        bo.add(pl);
        bo.run(() -> {
            try {
                bo.setProgress(20);
                TimeUnit.SECONDS.sleep(2);
                bo.changeProgress(5);
                TimeUnit.SECONDS.sleep(1);
                bo.changeProgress(8);
                TimeUnit.SECONDS.sleep(1);
                bo.changeProgress(18);
                TimeUnit.SECONDS.sleep(2);
                bo.changeProgress(20);
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
