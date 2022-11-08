/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delegadomotos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Kayn Malícias
 */
public class DelegadoMotos extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("/css/Style.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line argumentss
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
