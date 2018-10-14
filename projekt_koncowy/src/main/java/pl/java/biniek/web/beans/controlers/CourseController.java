//dostęp bezpieczenstwa zrobiono

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.web.beans.controlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.java.biniek.endpoints.CourseEndPoint;
import pl.java.biniek.endpoints.service.MailServiceGmail;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.exceptions.NullUzerApplicationException;
import pl.java.biniek.exceptions.WrongUzerApplicationException;
import pl.java.biniek.model.Course;
import pl.java.biniek.model.Organiser;
import pl.java.biniek.model.Payment;
import pl.java.biniek.model.Runner;
import pl.java.biniek.model.Uzer;
import pl.java.biniek.exception.interceptor.frontend.BinderController;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.model.Administrator;

/**
 *
 * @author samsung
 */
@Named
@SessionScoped
@BinderController//@Interceptors(LoggingInterceptorForController.class)//@Interceptors(LoggingInterceptorForController.class)
public class CourseController implements Serializable {

    @EJB
    MailServiceGmail mailServiceGmail;
    @EJB
    CourseEndPoint courseEndPoint;
    @Inject
    UzerController uzerController;
//    @Inject
//    AplicationController appControler;

    @Inject
    PaymentController paymentController;

    private Course editetCourse;

    private Course viewedCourse;

    private boolean backNavigatedFromPrivateRuns;

    private List<Course> courses;

    private String name;

    private Uzer uzer;

    // private ParticipantRunner runner;
////////////???????????????????
    private boolean isNavigatedFromMyCourses = false;

    public boolean isBackNavigatedFromPrivateRuns() {
        return backNavigatedFromPrivateRuns;
    }

    public void setBackNavigatedFromPrivateRuns(boolean backNavigatedFromPrivateRuns) {
        this.backNavigatedFromPrivateRuns = backNavigatedFromPrivateRuns;
    }

    public void setName(String name) {
        this.name = name;
    }

    @BinderPageBean
    public Runner getRunner() {
        return (Runner) uzer;
    }

    @BinderPageBean
    public void setRunner(Runner runner) {
        this.uzer = runner;
    }

    public Organiser getOrganiser() {
        return (Organiser) uzer;
    }

    public void setOrganiser(Organiser organiser) {
        this.uzer = organiser;
    }

    public Course getViewedCourse() {
        return viewedCourse;
    }

    public void setViewedCourse(Course viewedCourse) {
        this.viewedCourse = viewedCourse;
    }

    public List<Course> getAlUzerCourses(Uzer user) throws WrongUzerApplicationException {//
        if (user instanceof Organiser) {

            List<Course> listOfcourses = ((Organiser) user).getCourses();
            return listOfcourses;

        } else if (user instanceof Runner) {

            List<Payment> listOfPayment = paymentController.findPaymentsByRunner((Runner) user);
            List<Course> listOfcourses = new ArrayList<>();

            for (Payment payment : listOfPayment) {
                listOfcourses.add(payment.getCourse());//todo kwerenda jpql albo vidok?
            }
            return listOfcourses;
        } else {
            throw new WrongUzerApplicationException();
        }

    }

//
    public List<Course> getAllCourses() {
        return courseEndPoint.getAllCourses();
    }

    public List<Course> getFutureCourses() {//todo -  kwrenda

        //Date date = new Date();
        return courseEndPoint.getFutureCourses();

        // return futureCourses;
    }

    public CourseEndPoint getCourseEndPoint() {
        return courseEndPoint;
    }

    public void setCourseEndPoint(CourseEndPoint courseEndPoint) {
        this.courseEndPoint = courseEndPoint;

    }

    public Course getEditetCourse() {
        return editetCourse;
    }

    public void setEditetCourse(Course editetCourse) {

        this.editetCourse = editetCourse;
    }

    public String saveNew(Course course) throws BasicApplicationException {
        if (uzerController.getLoggedUser() == null) {
            throw new NullUzerApplicationException();
        }

        courseEndPoint.createCourse(course);
        AplicationController.showInfoSuccesMessage("");
        return "index";
    }

    public void saveEdited(Course courseEdited) {
        courseEndPoint.saveAfterEdit(courseEdited);
        mailServiceGmail.fireCourseChangeEmailInformation(courseEdited);

    }

    public String saveEditedCourse(Course course) {
        saveEdited(editetCourse);
        this.setViewedCourse(editetCourse);
        this.setEditetCourse(null);
        return "courseDetails";

    }

    public String deleteCourse(Course courseForDelete) throws BasicApplicationException {
        courseEndPoint.deleteCourse(courseForDelete);
        AplicationController.showInfoSuccesMessage("");
        if (backNavigatedFromPrivateRuns) {
            this.setBackNavigatedFromPrivateRuns(false);
            return "listOfMyCourses";
        } else {
            return "listOfCourses";
        }

    }

    public boolean isViewedCourseCreatedByUzer() {

        return ((Organiser) uzerController.getLoggedUser()).equals(getViewedCourse().getOrganiserOfTheCourse());
    }

    public Payment getViewedCoursePaymentForLoggedRunner(Course cours) throws BasicApplicationException {
        Uzer localUzer;
        if ((localUzer = uzerController.getLoggedUser()) instanceof Runner) {
            return paymentController.findPaymentByUserAndCourse((Runner) localUzer, cours);
        } else {
            throw new WrongUzerApplicationException();
        }

    }

    public void signRunnerOut(Course course) throws BasicApplicationException {

        courseEndPoint.signRunnerOut(uzerController.getLoggedUser(), course);

    }

    public void payForRun() throws BasicApplicationException {
        courseEndPoint.payForCourse(this.getViewedCourse());
        AplicationController.showInfoSuccesMessage("eplipepli");

    }

    public void signLoggedRunnerForViewedCourse(Course course) throws BasicApplicationException {
        courseEndPoint.signRunnerForCourse((Runner) uzerController.getLoggedUser(), course);
        AplicationController.showInfoSuccesMessage("");
        mailServiceGmail.fireSignedForCourseInformation((Runner) uzerController.getLoggedUser(), course);

    }

    public boolean isAvalibleRunnersLimit(Course course) {
        return courseEndPoint.isAvalibleRunnersLimit(viewedCourse);

    }

    public String deleteViewedCourse11a() throws Exception {

        deleteCourse(this.getViewedCourse());
        viewedCourse = null;
        AplicationController.showInfoSuccesMessage(null);
        if (this.isBackNavigatedFromPrivateRuns()) {
            this.setBackNavigatedFromPrivateRuns(false);
            return "listOfMyCourses";
        }
        return "listOfCourses";
    }

    public String editViewedCourse(Course course) throws BasicApplicationException {
        /**
         * Dodatkowe zabezpieczenie przed dostaniem się do formatki
         *
         */

        System.out.println(course.getOrganiserOfTheCourse().getId().getClass() + "  org id  " + uzerController.getLoggedUser().getId().getClass() + " logged id @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        if (((long) course.getOrganiserOfTheCourse().getId()) == ((long) uzerController.getLoggedUser().getId())
                || uzerController.getLoggedUser() instanceof Administrator) {
            this.setEditetCourse(course);
            //this.setViewedCourse(null);
            return "courseEdit";
        } else {
            throw new WrongUzerApplicationException();
        }
    }

    public String editCourseFromList(Course viewedCourse) throws BasicApplicationException {
        /**
         * Dodatkowe zabezpieczenie przed dostaniem się do formatki
         *
         */

        if (viewedCourse.getOrganiserOfTheCourse().getId() == uzerController.getLoggedUser().getId()
                || uzerController.getLoggedUser() instanceof Administrator) {//dodatkowe zabezpieczenie przed dostaniem się do formatki
            this.setEditetCourse(viewedCourse);
            this.setViewedCourse(null);
            return "courseEdit";
        } else {
            throw new WrongUzerApplicationException();
        }

    }

    public boolean isIsNavigatedFromMyCourses() {
        return isNavigatedFromMyCourses;
    }

    public void setIsNavigatedFromMyCourses(boolean isNavigatedFromMyCourses) {
        this.isNavigatedFromMyCourses = isNavigatedFromMyCourses;
    }

    public boolean doesGivenNameExists(String name) {
        return courseEndPoint.checkIfNameExists(name);

    }

    public boolean doesGivenShortNameExists(String name) {
        return courseEndPoint.checkIfShortNameExists(name);

    }

    public Course findByID(long id) {
        return courseEndPoint.find(id);
    }

    public Course findByShortName(String shortName) {
        return courseEndPoint.findByShortName(shortName);
    }

//    @Deprecated
//    public List<Course> getFutureCourses1() {//todo -  kwrenda
//
//        Date date = new Date();
//        List<Course> futureCourses = new ArrayList();
//        List<Course> allCourses = getAllCourses();
//        for (Course course : allCourses) {
//            if (course.getDateOfStart().after(date)) {
//                futureCourses.add(course);
//            }
////to zmienic z powrotem
//        }
//
//        return futureCourses;
//    }
//
//    @Deprecated
//    public Payment getViewedCoursePaymentForLogged() {
//        long idUzer = ((Runner) uzerController.getLoggedUser()).getId();
//        List<Payment> payments = getViewedCourse().getPayments();
//
//        for (Payment payment : payments) {
//            if (idUzer == (long) payment.getRunner().getId()) {
//                return payment;
//            }
//        }
//        return null;
//    }
//
//    @Deprecated
//    public void delete(Course courseForDelete) {
//
//        courseForDelete.getOrganiserOfTheCourse().getCourses().remove(courseForDelete);
//        //   courseEndPoint.remove(courseForDelete);
//
//    }
//    @Deprecated
//    public void payForViewedRun() {
//
//        if (getViewedCourse().isPaymentRequired()) {
//           
//            Payment coursePaymentForLoggedRunner = getViewedCoursePaymentForLoggedRunner();
//            coursePaymentForLoggedRunner.setCoursePayed(true);
//            paymentController.saveEdited(coursePaymentForLoggedRunner);
//            
//        }
//    }
//    @Deprecated
//    public void signRunnerOutFromCourse() {
//        Payment currentPayment = getViewedCoursePaymentForLoggedRunner();
//        paymentController.delete(currentPayment);
//        getViewedCourse().removePayment(currentPayment);
//        ((Runner) appControler.getLoggedUzer()).removePayment(currentPayment);
//    }
//
//    @Deprecated
//    public String deleteViewedCourseFromMyList() throws Exception {
//
//        deleteCourse(this.getViewedCourse());
//        viewedCourse = null;
//
//        return "listOfCourses";
//    }
//
//    @Deprecated
//    public boolean isAvalibleRunnersLimits() {
//        // courseEndPoint.refresh(viewedCourse);//blad optymistic lock
//        return (viewedCourse.getPayments().size()) < (viewedCourse.getRunnersLimit());
//    }
}
