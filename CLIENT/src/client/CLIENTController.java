/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;



import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import static javafx.application.Application.launch;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 *
 * @author wassi
 */
public class CLIENTController  implements Initializable {

    
     static Socket S;
     static ServerSocket SS;
     static DataOutputStream OS;
     static DataInputStream IS;
    @FXML
    private  TextArea txt;
    @FXML
    private TextField msg;
    @FXML
    private Button send;
    @FXML
    private Button deconnecter;
    
 
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       new ClientThread().start();
        }

    
    
     private class ClientThread extends Thread {
 
        @Override
        public void run() {
            try {
                S = new Socket("localhost", 1201);
                OS = new DataOutputStream(S.getOutputStream());
                IS = new DataInputStream(S.getInputStream());
                 txt.setText("");
                while (true) {
                    txt.appendText(IS.readUTF());
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 
    }
    
      @FXML
    private void sending(ActionEvent event) {
         String msgout="";
        try{
            
            msgout=msg.getText().trim();
            if (!msgout.equals("")){
            OS.writeUTF(msgout);
            msg.clear();}
            
        } catch (Exception E){ E.printStackTrace();}
    }
    
    @FXML
    private void deconnexion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/LOGIN.fxml"));
        Stage primaryStage=new Stage();
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("LOGIN");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("Images/téléchargement.png").toString()) {});
        Stage stage = (Stage) deconnecter.getScene().getWindow();
            stage.close();
        primaryStage.show();
    }

   
}
