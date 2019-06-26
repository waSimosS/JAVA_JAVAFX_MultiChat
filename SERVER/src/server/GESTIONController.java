/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author wassi
 */
public class GESTIONController implements Initializable {

    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> username;
    @FXML
    private TableColumn<?, ?> passwd;

    /**
     * Initializes the controller class.
     */
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        System.out.println("server.GESTIONController.initialize()");      
        Service_BD SB=new Service_BD();
        try{
        ArrayList<Client> list=SB.AfficherUser();
            System.err.println(list);
        ObservableList otab= FXCollections.observableArrayList(list);
        table.setItems(otab);
        username.setCellValueFactory(new PropertyValueFactory<>("Nom d'utilisateur"));
        passwd.setCellValueFactory(new PropertyValueFactory<>("Mot de passe"));
         } catch(SQLException E){E.printStackTrace();}
        
    }
         

    @FXML
    private void retourner(ActionEvent event) {
   
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }
    

}
