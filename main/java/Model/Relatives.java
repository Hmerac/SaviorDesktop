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
public class Relatives {
    
    public User user;
    public long id;
    public String name;
    public String surname;
    public String telephoneNumber;
    
    public Relatives(long id, User user, String name, String surname, String telephoneNumber) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
    }
    
}
