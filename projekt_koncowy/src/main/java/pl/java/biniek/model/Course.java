/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.model;

import java.io.Serializable;
import java.util.ArrayList;
//import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author java
 */
@Entity
//@Interceptors(@Interceptors(LoggingInterceptor.class).class)
@Table(name = "Course")
@NamedQueries({
        @NamedQuery(name = "Course.findByName", query = "SELECT d FROM Course d WHERE d.name = :name"),
     @NamedQuery(name = "Course.findByShortName", query = "SELECT d FROM Course d WHERE d.shortName = :shortName"),
      @NamedQuery(name = "Course.findById", query = "SELECT d FROM Course d WHERE d.id = :id"),
      @NamedQuery(name = "Course.findBeforeDate", query = "SELECT d FROM Course d WHERE d.dateOfStart > :dateOfStart"),
})
public class Course extends AbstractEntity implements Serializable {//model!!

    @Id
    @Column(name = "id", updatable = false)
    @TableGenerator(name = "CourseGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "Course", initialValue = 200, allocationSize = 7919)
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "CourseGen")
    private long id;

    @NotNull
    @Column(name = "name", unique = true, nullable = false, length = 256)
    private String name;

    @NotNull
    @Column(name = "shortName", unique = false, nullable = false, length = 20)
    private String shortName;

    @Column(name = "cityOfCours", unique = false, nullable = false, length = 256)
    private String cityOfCours;

    @NotNull
    @Column(name = "distance", unique = false, nullable = false)
    private float distance;

    @NotNull
    @Column(name = "dateOfStart", unique = false, nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateOfStart;

    @JoinColumn(name = "ID_ORGANISER", referencedColumnName = "ID")//nullable = false
    @ManyToOne
    private Organiser organiserOfTheCourse = null;
    //(optional = false)
    @Column(unique = false, nullable = true)
    private int descent;

    @Column(unique = false, nullable = true)
    private int climb;

    @Column(unique = false, nullable = true, length = 256)
    private String cityOfRunOffice;

    @Column(unique = false, nullable = true)
    private String addressOfRunOffice;

    @Column(unique = false, nullable = true)
    private String info;

    //  @NotNull
    @Column(unique = false, nullable = true)
    @Pattern(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$", message = "{constraint.string.incorrectemail}")
    private String email;

    @Column(unique = false, nullable = true, length = 256)
    private String phone;

    //@NotNull
    @Column(unique = false, nullable = true)
    private int runnersLimit;

    @Column(unique = false, nullable = true)
    private boolean paymentRequired;

    
      @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
     private List<Payment> payments = new ArrayList<>();

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
      
    public void addPayment(Payment payment) {
        this.payments.add(payment);
        payment.setCourse(this);
    }     
      
      
     public void removePayment(Payment payment) {
        this.payments.remove(payment);
        payment.setCourse(null);
    }       

     
     
       
    
    public boolean isPaymentRequired() {
        return paymentRequired;
    }

    public void setPaymentRequired(boolean paymentRequired) {
        this.paymentRequired = paymentRequired;
    }

    
    
    public Organiser getOrganiserOfTheCourse() {
        return organiserOfTheCourse;
    }

    public void setOrganiserOfTheCourse(Organiser organiserOfTheCourse) {
        this.organiserOfTheCourse = organiserOfTheCourse;
    }

//     private List<Payment> payments = new ArrayList<>();
//
//    public List<Payment> getPayments() {
//        return payments;
//    }
//
//    public void setPayments(List<Payment> payments) {
//        this.payments = payments;
//  
//@@X    private Set<Runner> runners = new HashSet<>();
    /**
     * Get the value of cityOfCours
     *
     * @return the value of cityOfCours
     */
    public String getCityOfCours() {
        return cityOfCours;
    }

    /**
     * Set the value of cityOfCours
     *
     * @param cityOfCours new value of cityOfCours
     */
    public void setCityOfCours(String cityOfCours) {
        this.cityOfCours = cityOfCours;
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
     * Get the value of info
     *
     * @return the value of info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Set the value of info
     *
     * @param info new value of info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Get the value of addressOfRunOffice
     *
     * @return the value of addressOfRunOffice
     */
    public String getAddressOfRunOffice() {
        return addressOfRunOffice;
    }

    /**
     * Set the value of addressOfRunOffice
     *
     * @param addressOfRunOffice new value of addressOfRunOffice
     */
    public void setAddressOfRunOffice(String addressOfRunOffice) {
        this.addressOfRunOffice = addressOfRunOffice;
    }

    /**
     * Get the value of cityOfRunOffice
     *
     * @return the value of cityOfRunOffice
     */
    public String getCityOfRunOffice() {
        return cityOfRunOffice;
    }

    /**
     * Set the value of cityOfRunOffice
     *
     * @param cityOfRunOffice new value of cityOfRunOffice
     */
    public void setCityOfRunOffice(String cityOfRunOffice) {
        this.cityOfRunOffice = cityOfRunOffice;
    }

    /**
     * Get the value of climb
     *
     * @return the value of climb
     */
    public int getClimb() {
        return climb;
    }

    /**
     * Set the value of climb
     *
     * @param climb new value of climb
     */
    public void setClimb(int climb) {
        this.climb = climb;
    }

    /**
     * Get the value of dateOfStart
     *
     * @return the value of dateOfStart
     */
    public Date getDateOfStart() {
        return dateOfStart;
    }

    /**
     * Set the value of dateOfStart
     *
     * @param dateOfStart new value of dateOfStart
     */
    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    /**
     * Get the value of shortName
     *
     * @return the value of shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Set the value of shortName
     *
     * @param shortName new value of shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getRunnersLimit() {
        return runnersLimit;
    }

    /**
     * Set the value of runnersLimit
     *
     * @param runnersLimit new value of runnersLimit
     */
    public void setRunnersLimit(int runnersLimit) {
        this.runnersLimit = runnersLimit;
    }

    ;

    /**
     * Get the value of Runners
     *
     * @return the value of Runners
     */
 //@@X   public Set getRunners() {
 //@@X       return runners;
  //@@X  }

    /**
     * Adds the runner to the course
     *
     */
  //@@X  public void addRunner(Runner runner) throws Exception {
   //@@X     if (runnersLimit <= runners.size()) {
    //@@X        throw new Exception();
    //@@X    } else {
   //@@X         runners.add(runner);
     //@@X   }

   //@@X }

    /**
     * Removes the runner from the course
     *
     */
  //@@X  public void removeRunner(Runner runner) {
    //@@X    runners.remove(runner);
  //@@X  }

    /**
     * Get the value of distance
     *
     * @return the value of distance
     */
    public float getDistance() {
        return distance;
    }

    /**
     * Set the value of distance
     *
     * @param distance new value of distance
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     * Get the value of typeOfRun
     *
     * @return the value of typeOfRun
     */
    public int getDescent() {
        return descent;
    }

    /**
     * Set the value of typeOfRun
     *
     * @param typeOfRun new value of typeOfRun
     */
    public void setDescent(int descent) {
        this.descent = descent;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected String getBusinessKey() {
        
        return "id :"+String.valueOf(id)+", name :"+name;
        
    }

//    @Override
//    public String toString() {
//        return "Course{" + "id=" + id + ", name=" + name + ", shortName=" + shortName + ", cityOfCours=" + cityOfCours + ", distance=" + distance + ", dateOfStart=" + dateOfStart + ", descent=" + descent + ", climb=" + climb + ", cityOfRunOffice=" + cityOfRunOffice + ", addressOfRunOffice=" + addressOfRunOffice + ", info=" + info + ", email=" + email + ", phone=" + phone + ", runnersLimit=" + runnersLimit + '}';
//    }

   
    public String toMail() {
        return "Course{ name=" + name + ", shortName=" + shortName + ", cityOfCours=" + cityOfCours + ", distance=" + distance + ", dateOfStart=" + dateOfStart 
                + ", organiserOfTheCourse=" + organiserOfTheCourse + ", descent=" + descent + ", climb=" + climb + ", cityOfRunOffice=" + cityOfRunOffice 
                + ", addressOfRunOffice=" + addressOfRunOffice + ", info=" + info + ", email=" + email + ", phone=" + phone + ", runnersLimit=" 
                + runnersLimit + ", paymentRequired=" + paymentRequired + ", payments=" + payments + '}';
    }



}
