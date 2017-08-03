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
public class Person {

    public long id;
    public String tcNo;
    public String name;
    public String lastName;

    public Person(long id, String tcNo, String name, String lastName) {
        this.id = id;
        this.tcNo = tcNo;
        this.name = name;
        this.lastName = lastName;

    }

}
