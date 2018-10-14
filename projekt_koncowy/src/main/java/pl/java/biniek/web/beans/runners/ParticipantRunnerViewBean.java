//chybaOK
package pl.java.biniek.web.beans.runners;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.model.Course;
import pl.java.biniek.model.Payment;
import pl.java.biniek.web.beans.controlers.CourseController;
import pl.java.biniek.web.beans.controlers.PaymentController;


@Named
@ViewScoped
@BinderPageBean
public class ParticipantRunnerViewBean implements Serializable {

    @Inject
    CourseController courseControler;
    @Inject
    PaymentController paymentController;

    Course course;

    @PostConstruct

    public void init() {
        course = courseControler.getViewedCourse();

    }

    public List<ParticipantRunnerViewBean> getParticipantList() {

        List<Payment> payment = paymentController.findPaymentsByCourse(course);

        //   Runner runner;
        List<ParticipantRunnerViewBean> participantRunners = new ArrayList<>();
        //     ParticipantRunner runner = new ParticipantRunner();
        ParticipantRunnerViewBean runner;
        for (Payment payment1 : payment) {
            runner = new ParticipantRunnerViewBean();

            runner.setFirstName(payment1.getRunner().getFirstName());
            runner.setLastName(payment1.getRunner().getLastName());
            runner.setIsMale(payment1.getRunner().getIsMale());
            runner.setBirthYare(this.birthYear(payment1.getRunner().getDateOfBirth()));
            runner.setPayed(payment1.isCoursePayed());
            participantRunners.add(runner);

        }

        return participantRunners;
    }

    public int birthYear(Date date) {

        LocalDate lDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return lDate.getYear();

    }

    public CourseController getCourseControler() {
        return courseControler;
    }

    public void setCourseControler(CourseController courseControler) {
        this.courseControler = courseControler;
    }

    private String firstName;

    private String lastName;

    private int birthYare;

    private boolean isMale;

    private boolean payed;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthYare() {
        return birthYare;
    }

    public void setBirthYare(int birthYare) {
        this.birthYare = birthYare;
    }

    public boolean isIsMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "ParticipantRunner{" + "firstName=" + firstName + ", lastName=" + lastName + ", birthYare=" + birthYare + ", isMale=" + isMale + ", payed=" + payed + '}';
    }

}
