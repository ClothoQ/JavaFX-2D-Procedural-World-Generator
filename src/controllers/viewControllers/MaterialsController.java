package controllers.viewControllers;

import controllers.MainController;
import controllers.mapEngine.Textures;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MaterialsController {

    @FXML private TextField LowT;
    @FXML private TextField HighT;
    @FXML private TextField ImageLoc;
    @FXML private VBox TextureList;
    @FXML private Button addTexture;

    private void getTextureList(){
        var textureList  = new Textures().getTextureList();
        for(int q = 0; q != textureList.length; q++){
            HBox ui = null;
            try {
                ui = FXMLLoader.load(getClass().getResource("/views/UIitems/textureItem.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            assert ui  != null;
            var id   = (Label) ui.lookup("#ID");
            var high = (Label) ui.lookup("#HIGH");
            var low  = (Label) ui.lookup("#LOW");

            id.setText(String.valueOf(textureList[q].getID()));
            high.setText(String.valueOf(textureList[q].getHigh()));
            low.setText(String.valueOf(textureList[q].getLow()));

            var image = (ImageView) ui.lookup("#Texture");
            try{
                image.setImage(new Image("/textures/"+textureList[q].getName()));
            }catch (Exception e){
                System.out.println("No textures found");
            }

            TextureList.getChildren().add(ui);
        }
    }

    private void addTexture(){
        var texture = new Textures();
        addTexture.setOnAction((e) -> {
            texture.addTexture(ImageLoc.getText() + ".png", Double.parseDouble(LowT.getText()), Double.parseDouble(HighT.getText()));

            HBox ui = null;
            try {
                ui = FXMLLoader.load(getClass().getResource("/views/UIitems/textureItem.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            assert ui  != null;
            var id   = (Label) ui.lookup("#ID");
            var high = (Label) ui.lookup("#HIGH");
            var low  = (Label) ui.lookup("#LOW");

            id.setText(String.valueOf("new"));
            high.setText(String.valueOf(LowT.getText()));
            low.setText(String.valueOf(HighT.getText()));

            var image = (ImageView) ui.lookup("#Texture");
            try{
                image.setImage(new Image("/textures/"+ImageLoc.getText() + ".png"));
            }catch (Exception ea){
                System.out.println("No texture found");
            }

            TextureList.getChildren().add(ui);

            MainController.engine.updateTextures();
        });
    }

    @FXML
    public void initialize() {
        getTextureList();
        addTexture();
    }

}
