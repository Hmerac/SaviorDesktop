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
public class BloodDonor {
    
    public User user;
    public long id;
    public String name;
    public String lastName;
    public String address;
    public String telephoneNumber;
    
    public BloodDonor(long id, User user, String name, String lastName, String address, String telephoneNumber) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }
    
}
