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
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author wassi
 */
public class LOGINController extends Application implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button login;
    @FXML
    private TextField hostnameTextfield;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private JFXButton ins;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
 @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/LOGIN.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("LOGIN");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("Images/téléchargement.png").toString()) {});
        primaryStage.show();
       
        
    }

    @FXML
    private int loginButtonAction(ActionEvent event) throws  SQLException, IOException  {
        
        
            Service_BD SB= new Service_BD();
            try{
                if(!SB.LoginUser(usernameTextfield.getText(),hostnameTextfield.getText()))
                {Alerte alert = new Alerte("WARNING","WARNING","VERIFIE VOTRE USERNAME ET MOT DE PASSE ");
                
                return 0;}
            }catch(SQLException E){}
                
               
            Parent root = FXMLLoader.load(getClass().getResource("/Views/CLIENT.fxml"));
            Stage primaryStage=new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(usernameTextfield.getText());
            primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("Images/téléchargement.png").toString()) {});
            Stage stage = (Stage) login.getScene().getWindow();
            stage.close();
            primaryStage.show();
            return 1;
    }
 
 public static void main(String[] args) {
        
        launch(args);}


    @FXML
    private void inscrip(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("/Views/AJOUT.fxml"));
            Stage primaryStage=new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            
            primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("Images/téléchargement.png").toString()) {});
            Stage stage = (Stage) login.getScene().getWindow();
            stage.close();
            primaryStage.show();
    }

}
