package controllers;

import controllers.mapEngine.Engine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController{

    @FXML private Canvas     canvas;
    @FXML private ScrollPane MenuList;
    @FXML private VBox       Menu;

    static public Engine     engine;

    @FXML private void addProceduralMapView(){
        try {
            AnchorPane panel = FXMLLoader.load(getClass().getResource("/views/UIComponents/ProceduralMap.fxml"));
            Menu.getChildren().add(panel);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML private void addMaterialsView(){
        try {
            AnchorPane panel = FXMLLoader.load(getClass().getResource("/views/UIComponents/Materials.fxml"));
            Menu.getChildren().add(panel);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML private void addStatsView(){
        try {
            AnchorPane panel = FXMLLoader.load(getClass().getResource("/views/UIComponents/Stats.fxml"));
            Menu.getChildren().add(panel);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML public void initialize() {
        MenuList.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        MenuList.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        engine = new Engine(canvas);
        engine.autoGenerate();
        engine.start();

        addProceduralMapView();
        addMaterialsView();
        addStatsView();
    }

}
