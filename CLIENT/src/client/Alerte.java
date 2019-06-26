/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import javafx.scene.control.Alert;

/**
 *
 * @author wassi
 */
public class Alerte {
    Alert.AlertType type;
    
    public Alerte(String type,String titre, String contenu)
    {
        if(type.equals("WARNING"))
            this.type=Alert.AlertType.WARNING;
        else this.type=Alert.AlertType.INFORMATION;
        
       Alert alert = new Alert(this.type);
                alert.setTitle(titre);
                alert.setHeaderText(contenu);
                alert.setContentText("");
                alert.showAndWait();
    }
    
}
