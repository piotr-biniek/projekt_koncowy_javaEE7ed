/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author samsung
 */
@Table(name = "Payment", uniqueConstraints
        = @UniqueConstraint(columnNames = {"ID_RUNNER", "ID_COURSE"})
)
@Entity
@NamedQueries({
//    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
@NamedQuery(name = "Payment.findByCourseAndRunner", query = "SELECT d FROM Payment d WHERE d.course = ?1 AND d.runner=?2"),
  @NamedQuery(name = "Payment.findAllByCourse", query = "SELECT d FROM Payment d WHERE d.course = :course"),
  @NamedQuery(name = "Payment.findAllByUzer", query = "SELECT d FROM Payment d WHERE d.runner = :runner")
      
})
//@Table(name = "Payment")
public class Payment extends AbstractEntity implements Serializable {

    @Id
    @Column(name = "id", updatable = false)
    @TableGenerator(name = "PayGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "Course", initialValue = 2000000, allocationSize = 25)
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "CourseGen")
    private long id;

    @JoinColumn(name = "ID_RUNNER", referencedColumnName = "ID")//nullable = false
    @ManyToOne
    private Runner runner;

    @JoinColumn(name = "ID_COURSE", referencedColumnName = "ID")//nullable = false
    @ManyToOne
    private Course course;

    @Column(unique = false, nullable = true)
    private boolean coursePayed;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isCoursePayed() {
        return coursePayed;
    }

    public void setCoursePayed(boolean coursePayed) {
        this.coursePayed = coursePayed;
    }

    @Override
    public Object getId() {
        return id; //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    protected String getBusinessKey() {
//     if(null!=this){
//        return "id :" + String.valueOf(id) ;
//     //   }
// //       else return "Payment removed";
//    }

    /**
     * Get the value of runner
     *
     * @return the value of runner
     */
    public Runner getRunner() {
        return runner;
    }

    /**
     * Set the value of runner
     *
     * @param runner new value of runner
     */
    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    @Override
    protected String getBusinessKey() {
          return "id :"+String.valueOf(id);
       
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", runnerIds=" + runner.getBusinessKey() + ", courseIds=" + course + ", coursePayed=" + coursePayed + '}';
    }
 

}
