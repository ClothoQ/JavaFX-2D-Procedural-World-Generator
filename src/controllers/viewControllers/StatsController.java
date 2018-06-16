package controllers.viewControllers;

import controllers.MainController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatsController extends Widget{

    @FXML private Label FPS;
    @FXML private Label RenderSize;
    @FXML private Label MapSize;
    @FXML private Label CameraPos;
    @FXML private Label SeedName;
    @FXML private Label TextureSize;


    @FXML private Button closeWidget;
    @FXML private VBox widget;

    private void displayStats(){
        new Thread(() -> {
            for(;;){
                try {
                    Thread.sleep(100);
                    Platform.runLater(() -> FPS.setText("FPS "+ MainController.engine.getFPS()));
                    Platform.runLater(() -> RenderSize.setText("Render Size w"+ MainController.engine.getMapW() + " h" +  MainController.engine.getMapH()));
                    Platform.runLater(() -> MapSize.setText("Map size "+ MainController.engine.getMapSizeXY() * MainController.engine.getMapSizeXY()));
                    Platform.runLater(() -> CameraPos.setText("Camera Pos x"+ MainController.engine.getCameraX() + " y" +  MainController.engine.getCameraY()));
                    Platform.runLater(() -> SeedName.setText("Seed "+ MainController.engine.getSeed()));
                    Platform.runLater(() -> TextureSize.setText("Texture size "+ MainController.engine.getTextureSize()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @FXML
    public void initialize() {
        displayStats();
        minimizeWidget(closeWidget ,widget);
    }

}
