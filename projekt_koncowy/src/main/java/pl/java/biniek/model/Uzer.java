/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Uzer")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@DiscriminatorValue("UZER")
@NamedQueries({
        @NamedQuery(name = "Uzer.findByEmail", query = "SELECT d FROM Uzer d WHERE d.email = :email"),
     @NamedQuery(name = "Uzer.doesEmailExists", query = "SELECT d FROM Uzer d WHERE d.email = :email"),
})
public class Uzer extends AbstractEntity implements Serializable {//user

    @Id
    @Column(name = "id", updatable = false)
    @TableGenerator(name = "UzerGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "User", initialValue = 400, allocationSize =  6053 )
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "UserGen")
    private Long id;

    @NotNull(message = "{constraint.notnull}")
    @Column(name = "email", length = 64, nullable = false, unique = true, updatable = true)
    @Size(min = 5, max = 64, message="{constraint.string.length.notinrange}")
    @Pattern(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$", message = "{constraint.string.incorrectemail}")
    private String email;

    @NotNull(message = "{constraint.notnull}")
    @Size(min = 6, message = "{constraint.string.length.tooshort}")
    @Column(name = "password", length = 256, nullable = false)
    private String password;

    @NotNull(message = "{constraint.notnull}")
    @Size(min = 9, message = "{constraint.string.length.tooshort}")
    //@Pattern(regexp = "^[0-9\\-]$", message = "{constraint.string.incorrectemail}")
    @Column(name = "phone", length = 256, nullable = false)
    private String phone;

    @Column(name = "type", updatable = false)
    private String type;

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of phone
     *
     * @return the value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the value of phone
     *
     * @param phone new value of phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
   @Override
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the value of Password
     *
     * @return the value of Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of Password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected String getBusinessKey() {
        return "emal: "+email+", id: "+id;
    }

    public String getType() {
        return type;
    }
    
    public String getName()      
    {
      return String.valueOf(id);
    }

    @Override
    public String toString() {
        return "Uzer{" + "id=" + id + ", email=" + email + ", type=" + type + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.email);
        return hash;
    }
    
}
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Uzer other = (Uzer) obj;
//        if (!Objects.equals(this.email, other.email)) {
//            return false;
//        }
//        if (!Objects.equals(this.id, other.id)) {
//            return false;
//        }
//        return true;
//    }
//    
    
    



//   
//@TableGenerator(name = "KontoIdGen",
//            table = "GENERATOR", 
//            pkColumnName = "Klasa", pkColumnValue = "User",
//            valueColumnName = "Wartosc",
//            initialValue = 100, allocationSize = 10)
