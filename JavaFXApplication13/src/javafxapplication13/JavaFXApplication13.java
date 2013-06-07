/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication13;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author oky
 */
public class JavaFXApplication13 extends Application {
    Scene scene;
    private Label label;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
         
        scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
      
    }
    public static void main(String[] args) {
        launch(args);
    }
}
