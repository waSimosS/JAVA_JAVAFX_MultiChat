/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author wassi
 */
public class Service_BD {
    private Connection con=DataSource.getInstance().getConnection();
  
   private Statement ste;

   
   public Service_BD(){
     try {
          ste=con.createStatement();
      } catch (SQLException ex) {
          System.out.println(ex);
      }
    }
        
    
    public void AjouterUser(String USERNAME, String PASSWD) throws SQLException
    {
        String requete="INSERT INTO `users`(`username`, `password`)"
        +"VALUES( '"+USERNAME+"' , '"+PASSWD+"');";
        ste.executeUpdate(requete);
    }
    
   public boolean LoginUser(String USERNAME, String PASSWD)throws SQLException{
       boolean log=false;
       String requete="SELECT * FROM `users` WHERE `users`.`username`='"+USERNAME+"' AND `users`.`password`='"+PASSWD+"'";
       ResultSet res=ste.executeQuery(requete);  //ici on verifie l'existance de user dans BD
       while (res.next()){ 
          log=true;
        }
     return log;
       }
       
   public  ArrayList<Client> AfficherUser() throws SQLException
   {
        ArrayList<Client> users=new ArrayList<>();
        String requete="SELECT username, password FROM users";
        ResultSet res=ste.executeQuery(requete);
        while (res.next()){
              Client C=new Client(res.getString(1),res.getString(2));
              users.add(C);
          
    
   
   }
        return users;
      
  }
      
  } 


    
    

