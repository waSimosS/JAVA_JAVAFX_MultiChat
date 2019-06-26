/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import java.util.Vector;



import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author wassi
 */
public class SERVERController extends Application implements Initializable {

    
     static Socket S;
     static ServerSocket SS;
     static DataOutputStream OS;
     static DataInputStream IS;
     static Vector<ClientHandler> ActiveClient= new Vector<>(); 

     
    
    
    @FXML
    private  TextArea txt;
    @FXML
    private Button gestion;
   
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/SERVER.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("SERVER");
        primaryStage.show();
    
        }
    
     
    public static void main(String[] args) {
        
        launch(args);}
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         new ServerThread().start();
       
    }    

    @FXML
    private void gerer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/GESTION.fxml"));



             gestion.getScene().setRoot(root);
    }
    
   
    private class ServerThread extends Thread {
 
        public ServerThread(){
           try {
               // Lancement du Serveur
                SS =new ServerSocket(1201);
               } catch (IOException ex) {ex.printStackTrace();}
            }
    
        @Override
        public void run() {
                int i=0;
                while (true) {
                    try {
                        // Accepter les demandes arrivés
                        S = SS.accept();
                        
                        System.out.println("Une nouvelle demande Client est arrivée: " + S);
                        txt.appendText("Une nouvelle demande Client est arrivée: " + S);
                       
                        IS = new DataInputStream(S.getInputStream());
                        OS = new DataOutputStream(S.getOutputStream());
                        
                        // Creation d'un objet pour manipuler ce client
                        ClientHandler clt = new ClientHandler(S,"client " + i, IS, OS);
                        //Ajout de ce client dans la liste des actifs
                        ActiveClient.add(clt);
                        // Creation Thread pour cet objet
                        Thread t = new Thread(clt);
                        
                        // start the thread.
                        t.start();
                        
                        i++;    } catch (IOException ex) { ex.printStackTrace();}
                        
                    
 
                    }
        }
  }
    
    // ClientHandler class 
class ClientHandler implements Runnable  
{ 
     
    private String name; 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    Socket s; 
    boolean isloggedin; 
      
    // constructor 
    public ClientHandler(Socket s, String name, 
                            DataInputStream dis, DataOutputStream dos) { 
        this.dis = dis; 
        this.dos = dos; 
        this.name = name; 
        this.s = s; 
        this.isloggedin=true; 
    } 
  
    @Override
    public void run() { 
  
        String Recu; 
        while (true)  
        { 
            try
            { 
                
                Recu = dis.readUTF(); 
                  
                System.out.println(Recu); 
                  
                if(Recu.equals("logout")){ 
                    this.isloggedin=false; 
                    this.s.close(); 
                    break; 
                } 
                  
                //Diffusion de message Recu a tout les clients connectes
                
                for (ClientHandler mc : ActiveClient)  
                { 
                   if ( mc.isloggedin==true)  
                   
                        mc.dos.writeUTF(this.name+" : "+Recu+" \n"); 
               } 
               
                txt.appendText(this.name+" : "+Recu+" \n");
            } catch (IOException e) {e.printStackTrace();} 
              
        } 
        try
        { 
            // Fermeture desresources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    } 
} 
}
                
                
                
                
                
                
                
                
                
                
                

    
   
    
    
            
 



 

    
    

