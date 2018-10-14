/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author java
 */
@Entity
@Table(name = "Organiser")
@DiscriminatorValue("ORGANISER")
@NamedQueries({
    @NamedQuery(name = "Organiser.findAll", query = "SELECT d FROM Runner d")
    ,
    @NamedQuery(name = "Organiser.findByLogin", query = "SELECT d FROM Runner d WHERE d.email = :email")})

public class Organiser extends Uzer implements Serializable {

    @NotNull
    @Column(name = "organiserName", unique = true, nullable = false, length = 256)
    private String organiserName;

    @Column(name = "adresCity", unique = false, nullable = true, length = 256)
    private String adresCity;

    @Column(name = "adresZipCode", unique = false, nullable = true, length = 256)
    private String adresZipCode;

    @Column(name = "adresStreet", unique = false, nullable = true, length = 256)
    private String adresStreet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organiserOfTheCourse")
    private List<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Get the value of adresStreet
     *
     * @return the value of adresStreet
     */
    public String getAdresStreet() {
        return adresStreet;
    }

    /**
     * Set the value of adresStreet
     *
     * @param adresStreet new value of adresStreet
     */
    public void setAdresStreet(String adresStreet) {
        this.adresStreet = adresStreet;
    }

    /**
     * Get the value of adresZipCode
     *
     * @return the value of adresZipCode
     */
    public String getAdresZipCode() {
        return adresZipCode;
    }

    /**
     * Set the value of adresZipCode
     *
     * @param adresZipCode new value of adresZipCode
     */
    public void setAdresZipCode(String adresZipCode) {
        this.adresZipCode = adresZipCode;
    }

    /**
     * Get the value of adresCity
     *
     * @return the value of adresCity
     */
    public String getAdresCity() {
        return adresCity;
    }

    /**
     * Set the value of adresCity
     *
     * @param adresCity new value of adresCity
     */
    public void setAdresCity(String adresCity) {
        this.adresCity = adresCity;
    }

    /**
     * Get the value of courses
     *
     * @return the value of courses
     */
    //@@X    public Set getCourses() {
    //@@X        return courses;
    //@@X    }
//@@X     public boolean addCourse(Course cours) {
    //@@X        cours.setOrganiserOfTheCourse(this);
    //@@X       return courses.add(cours);
    //@@X  }
//    
//    public boolean removeCourse(Course cours) {
//      //  cours.removeRunner(this);
//        return courses.remove(cours);
//    }
    /**
     * Get the value of OrganiserName
     *
     * @return the value of OrganiserName
     */
    public String getOrganiserName() {
        return organiserName;
    }

    /**
     * Set the value of OrganiserName
     *
     * @param OrganiserName new value of OrganiserName
     */
    public void setOrganiserName(String OrganiserName) {
        this.organiserName = OrganiserName;
    }

    @Override
    public String getName() {
        return getOrganiserName();
    }

//    public static void createOrganiser(String OrganiserName, String nip, String email) {
//        Organiser organiser = new Organiser();
//        organiser.setOrganiserName(OrganiserName);
//        organiser.setNip(nip);
//        organiser.setEmail(email);
//       // organiser.setLogin("qwerty");
//        
//    }

    @Override
    public String toString() {
        return "Organiser{" + "organiserName=" + organiserName + "} "+super.toString();
    }

    

}


