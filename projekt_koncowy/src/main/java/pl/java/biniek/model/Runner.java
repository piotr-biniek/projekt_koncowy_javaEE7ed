/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.model;

//import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
//import javax.validation.constraints.Pattern;

/**
 *
 * @author java
 */
@Entity
@Table(name = "Runner")
@DiscriminatorValue("RUNNER")
@NamedQueries({
    @NamedQuery(name = "Runner.findAll", query = "SELECT d FROM Runner d"),
    @NamedQuery(name = "Runner.findByLogin", query = "SELECT d FROM Runner d WHERE d.email = :email")
})
public class Runner extends Uzer implements Serializable {

    public Runner() {
    }

//    @NotNull
    @Column(name = "firstName", unique = false, nullable = false, length = 256)
    private String firstName;

    //  @NotNull
    @Column(name = "lastName", unique = false, nullable = false, length = 256)
    private String lastName;

    //uzupelnic java doc, testy, 
    //@NotNull
    @Column(name = "dateOfBirth", unique = false, nullable = false, length = 256)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;

    //    @NotNull
    @Column(name = "isMale", unique = false, nullable = false)
    private boolean isMale;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "runner")
    private List<Payment> payments = new ArrayList<>();

    public List<Payment> getPayments() {
        return payments;
    }

    public Payment getCoursePayment(Course course) {
        return getCoursePayment(course.getId());
    }

    public Payment getCoursePayment(long courseID) {
        for (Payment payment : payments) {
            if (payment.getRunner().getId() .equals(this.getId()) && payment.getCourse().getId() == courseID) {//to correct?
                return payment;
            }

        }

        return null;

    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void addPayment(Payment payment) {
        this.payments.add(payment);
        payment.setRunner(this);
    }

    public void removePayment(Payment payment) {
        this.payments.remove(payment);
        // payment.setRunner(null);
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the value of isMale
     *
     * @return the value of isMale
     */
    public boolean getIsMale() {
        return isMale;
    }

    /**
     * Set the value of isMale
     *
     * @param isMale new value of isMale
     */
    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }

    /**
     * Get the value of coursesOfRunner
     *
     * @return the value of coursesOfRunner
     */
    //@@X   public Set getCoursesOfRunner() {
//@@X        return coursesOfRunner;
//@@X    }
    /**
     * Adds new Course for runner if it is not already present
     *
     * @param course
     * @return <tt>true</tt> if this set did not already contain the specified
     * element
     */
//@@X    public boolean addCourse(Course course) throws Exception {
//        course.addRunner(this);
//@@X        return coursesOfRunner.add(course);
//@@X    }
    /**
     * Get the value of dateOfBirth
     *
     * @return the value of dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Set the value of dateOfBirth
     *
     * @param dateOfBirth new value of dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Get the value of Surname
     *
     * @return the value of LastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of S=LastName
     *
     * @param lastName new value of Surname
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public String toString() {
        return  "Runner{" + "firstName=" + firstName + ", lastName=" + lastName+" "+super.toString();
    }

}
