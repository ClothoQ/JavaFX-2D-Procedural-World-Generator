package controllers.viewControllers;

import controllers.MainController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProceduralMapController {

    @FXML private Label  FPS;
    @FXML private Button Generate;

    private void displayFPS(){
        new Thread(() -> {
            for(;;){
                try {
                    Thread.sleep(100);
                    Platform.runLater(() -> FPS.setText("FPS "+ MainController.engine.getFPS()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @FXML
    public void initialize() {
        Generate.setOnAction(e ->  MainController.engine.autoInit());
        displayFPS();
    }

}
