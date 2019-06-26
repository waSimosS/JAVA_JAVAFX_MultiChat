/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wassi
 */
public class AJOUTController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField pass;
    @FXML
    private TextField repass;
    @FXML
    private Button inscrire;
    @FXML
    private JFXButton retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private int inscription(ActionEvent event) {
        Service_BD SB= new Service_BD();
        if (!username.getText().equals("")){ 
            if (!pass.getText().equals(repass.getText())){
              Alerte alert = new Alerte("WARNING","WARNING","Vérifier votre mot de passe ");
              return 0;
            }
            else{
             try{  
            SB.AjouterUser(username.getText(), pass.getText());
            Alerte alert = new Alerte("INFORMATION","INFORMATION","Inscription est validé ");
             return 1;}
             catch(SQLException E){
                 Alerte alert = new Alerte("WARNING","WARNING","Username existe deja ");return 0;}}
        }
        else{
        Alerte alert = new Alerte("WARNING","WARNING","Inscription n'est pas validé ");
        return 0;}
        
    }

        
    
    @FXML
    private void retourner(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/LOGIN.fxml"));
            Stage primaryStage=new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            
            primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("Images/téléchargement.png").toString()) {});
            Stage stage = (Stage) retour.getScene().getWindow();
            stage.close();
            primaryStage.show();
    }
    
}
