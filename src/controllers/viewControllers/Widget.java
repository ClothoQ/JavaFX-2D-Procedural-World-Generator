package controllers.viewControllers;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

class Widget {

    void minimizeWidget(Button closeWidget, VBox widget){
        closeWidget.setOnAction(e ->  {
            if(widget.isVisible()){
                closeWidget.setText("+");
                widget.setMaxHeight(0);
                widget.setMinHeight(0);
                widget.setVisible(false);
            }else{
                widget.setMinHeight(13);
                widget.setMaxHeight(1000);
                widget.setVisible(true);
                closeWidget.setText("-");
            }
        });
    }

    int isInt(String value){
        try{
            return Integer.parseInt(value);
        }catch (Exception e){
            return 0;
        }
    }

    public double isDouble(String value){
      try{
          return Double.parseDouble(value);
      }catch (Exception e){
          return 0.0;
      }
    }


}
