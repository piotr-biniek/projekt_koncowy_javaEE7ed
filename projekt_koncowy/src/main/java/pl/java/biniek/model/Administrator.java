/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author java
 */
@Entity
@Table(name = "Admin")
@DiscriminatorValue("ADMIN")
public class Administrator extends Uzer {

    @NotNull
    @Size(min=3, max=40,message="{constraint.string.length.notinrange}")
    private String adminName;

    /**
     * Get the value of isPrac
     *
     * @return the value of isPrac
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * Set the value of isPrac
     *
     * @param name new value of isPrac
     */
    public void setAdminName(String name) {
        this.adminName = name;
    }

    @Override
    public String toString() {
        return "Administrator " + super.toString();
    }

    @Override
    public String getName() {
        return adminName; 
    }
    

}
