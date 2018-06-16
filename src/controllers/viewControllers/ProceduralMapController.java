package controllers.viewControllers;

import controllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProceduralMapController extends Widget{

    @FXML private Button GenerateDefault;
    @FXML private Button GenerateCustom;

    @FXML private Button closeWidget;
    @FXML private VBox widget;

    @FXML private TextField Seed;
    @FXML private TextField BlockSize;
    @FXML private TextField MapW;
    @FXML private TextField MapH;
    @FXML private TextField MapSizeXY;

    @FXML
    public void initialize() {
        GenerateDefault.setOnAction(e -> MainController.engine.autoGenerate());
        GenerateCustom.setOnAction(e -> {
            MainController.engine.setMapW(isInt(MapW.getText()));
            MainController.engine.setMapH(isInt(MapH.getText()));
            MainController.engine.setMapSizeXY(isInt(MapSizeXY.getText()));
            MainController.engine.setSeed(isInt(Seed.getText()));
            MainController.engine.setTextureSize(isInt(BlockSize.getText()));
            MainController.engine.custom();
        });

        minimizeWidget(closeWidget ,widget);
    }

}
