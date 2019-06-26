/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author wassi
 */
public class Client {
    
    private String user;
    private String passwd;
    
    public Client(String user, String passwd) {
        this.user = user;
        this.passwd = passwd;
    }
    
    public String toString(){
        return this.user+" "+this.passwd;
    }
}
