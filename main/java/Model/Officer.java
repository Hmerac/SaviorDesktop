/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Mert
 */
public class Officer {
    
    public long id;
    public String tcNo;
    public String password;
    public String name;
    public String lastName;
    public double latitude;
    public double longitude;
    
    public Officer(String tcNo, String password){
        this.tcNo = tcNo;
        this.password = password;
    }
    
    public Officer(long id, String tcNo, String password, String name, String lastName, double latitude, double longitude){
        this.id = id;
        this.tcNo = tcNo;
        this.name = name;
        this.lastName = lastName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
}
