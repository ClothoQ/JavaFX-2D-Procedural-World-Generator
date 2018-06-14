package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class APP extends Application {

    @Override
    public void start(Stage stage){
        Parent root;
        try{
            root = FXMLLoader.load(getClass().getResource("/views/main.fxml"));
        }catch (Exception e){
            root = new HBox();
            Label message = new Label("View file not found!");
            message.setStyle("-fx-text-fill: white");
            ((HBox) root).getChildren().add(message);
        }

        root.setStyle("-fx-background-color: #444444");

        stage.setTitle("JavaFX 2D procedural map generator");
        stage.setScene(new Scene(root, 960, 600));
        stage.setMinWidth(960);
        stage.setMinHeight(550);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
