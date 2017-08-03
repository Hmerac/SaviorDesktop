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
public class Cases {

    public User user;
    public long id;
    public long usersId;
    public String caseType;
    public int caseDegree;
    public String casePhotosBase64;
    public String licenceNumber;
    public String description;
    public double latitude;
    public double longitude;
    public String caseDate;
    public long policeId;
    public String caseAddress;
    public boolean policeTeam;
    public boolean hospitalTeam;
    public List<Person> person;

    public Cases(long id, User user, String description, String caseType, String casePhotosBase64, String licenceNumber, double latitude, double longitude, String caseDate, int caseDegree, List<Person> person, long userId) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.casePhotosBase64 = casePhotosBase64;
        this.licenceNumber = licenceNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.caseDate = caseDate;
        this.caseDegree = caseDegree;
        this.policeTeam = false;
        this.hospitalTeam = false;
        this.caseType = caseType;
        this.person = person;
        this.usersId = userId;

    }

    public Cases(long id, User user, String description, String caseType, String casePhotosBase64, double latitude, double longitude, String caseDate, int caseDegree, List<Person> person, long userId) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.casePhotosBase64 = casePhotosBase64;
        this.latitude = latitude;
        this.longitude = longitude;
        this.caseDate = caseDate;
        this.caseDegree = caseDegree;
        this.policeTeam = false;
        this.hospitalTeam = false;
        this.caseType = caseType;
        this.person = person;
        this.usersId = userId;

    }

    public Cases(long id, User user, String description, String casePhotosBase64, String caseType, double latitude, double longitude, String licenceNumber, String caseDate, int caseDegree, List<Person> person, long userId) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.casePhotosBase64 = casePhotosBase64;
        this.latitude = latitude;
        this.longitude = longitude;
        this.caseDate = caseDate;
        this.caseDegree = caseDegree;
        this.policeTeam = false;
        this.hospitalTeam = false;
        this.caseType = caseType;
        this.licenceNumber = licenceNumber;
        this.person = person;
        this.usersId = userId;

    }

}
