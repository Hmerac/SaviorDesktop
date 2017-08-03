/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author Mert
 */
public class User {
    
    public long id;
    public String name;
    public String lastName;
    public String address;
    public String telephoneNumber;
    public String bloodType;
    public String licenceNumber;
    public double latitude;
    public double longitude;
    public String tcNo;
    public int situation;
    public String password;
    public String email;
    public List<Relatives> relatives;
    public List<BloodDonor> bloodDonors;
    
    public User(long id, String tcNo, String email, String name, String lastName, String address, String telephoneNumber, String bloodType, String licenceNumber, List<Relatives> relatives, List<BloodDonor> bloodDonors) {
        this.id = id;
        this.tcNo = tcNo;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.bloodType = bloodType;
        this.licenceNumber = licenceNumber;
        this.relatives = relatives;
        this.bloodDonors = bloodDonors;
    }
    
}
