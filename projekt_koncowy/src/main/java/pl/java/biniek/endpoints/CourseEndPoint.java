/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.endpoints;

import pl.java.biniek.endpoints.service.MailServiceGmail;
import pl.java.biniek.exceptions.NotEmptyPaymentListException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.interceptor.Interceptors;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.exceptions.NoPaymentRequiredException;
import pl.java.biniek.exceptions.NullPointerApplicationException;
import pl.java.biniek.exceptions.NullUzerApplicationException;
import pl.java.biniek.exceptions.WrongUzerApplicationException;
import pl.java.biniek.facade.CourseFacade;
import pl.java.biniek.exception.interceptor.backend.LoggingInterceptorWithRepackingForEndPoint;
import pl.java.biniek.model.Course;
import pl.java.biniek.model.Organiser;
import pl.java.biniek.model.Payment;
import pl.java.biniek.model.Runner;
import pl.java.biniek.model.Uzer;
import pl.java.biniek.web.beans.controlers.AplicationController;
//import pl.java.biniek.model.Course;

/**
 *
 * @author java pbi moje!!!!!
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptorWithRepackingForEndPoint.class)
public class CourseEndPoint implements Serializable {

    @EJB
    private CourseFacade courseFacade;

    @EJB
    private PaymentEndPoint paymentEndPoint;
    @EJB
    private UzerEndPoint uzerEndPoint;


    @RolesAllowed({"Organiser", "Administrator"})
    private void remove(Course course) {
        courseFacade.remove(course);

    }

    
    public List<Course> getAllCourses() {
        return courseFacade.findAll();

    }

    @RolesAllowed({"Organiser"})
    public void createCourse(Course course) throws BasicApplicationException {
        Uzer uzer = uzerEndPoint.getLoggedUser();
        if (null == uzer) {
            throw new NullUzerApplicationException();
        }

        course.setOrganiserOfTheCourse((Organiser) uzer);

        courseFacade.create(course);
    }

    @RolesAllowed({"Organiser"})
    public void saveAfterEdit(Course course) {

        courseFacade.edit(course);
        
        
// starczy prosta zasada - przed edit dobrze po edit Źle!!!!!!! :)
    }

    public Course find(long id) {

        return courseFacade.find(id);
    }

    @RolesAllowed("Runner")
    public void signRunnerForCourse(Runner runner, Course course) throws BasicApplicationException {

        Payment payment = new Payment();

        payment.setCoursePayed(false);

        course.addPayment(payment);

        runner.addPayment(payment);
        

        paymentEndPoint.createPayment(payment);
    }

    @RolesAllowed({"Organiser", "Administrator"})
    public void deleteCourse(Course course) throws BasicApplicationException {
        //  Uzer uzer;
        if (course == null) {
            throw new NullPointerApplicationException();
        }
        //    if(uzer=)

        Course coursForDelete = courseFacade.find(course.getId());
        Uzer uzer = uzerEndPoint.getLoggedUser();

        if ((uzer instanceof Organiser) && (!((Organiser) uzer).equals(course.getOrganiserOfTheCourse()))) {
            throw new WrongUzerApplicationException();
        }

        if ((uzer instanceof Organiser) && !course.getPayments().isEmpty()) {
           throw new NotEmptyPaymentListException();
        } else {
            course.getOrganiserOfTheCourse().getCourses().remove(course);
            remove(course);
        }
    }

    @RolesAllowed({"Runner"})
    public void payForCourse(Course viewedCourse) throws BasicApplicationException {
        Uzer loggedUser = uzerEndPoint.getLoggedUser();
        if (viewedCourse == null) {
            throw new NullPointerApplicationException();
        }

        if (viewedCourse.isPaymentRequired()) {
         
            Payment coursePaymentToPayFor = paymentEndPoint.findPaymentByUserAndCourse((Runner) loggedUser, viewedCourse);
            if (coursePaymentToPayFor != null) {
                coursePaymentToPayFor.setCoursePayed(true);
                paymentEndPoint.saveAfterEdit(coursePaymentToPayFor);
            } else {
                throw new NullPointerApplicationException();
            }

        } else {
            throw new NoPaymentRequiredException();
        }
    }

    @RolesAllowed({"Runner"})
    public void signRunnerOut(Uzer loggedUzer, Course course) throws BasicApplicationException {

        Payment payment = paymentEndPoint.findPaymentByUserAndCourse((Runner) loggedUzer, course);
        paymentEndPoint.remove(payment);
        course.removePayment(payment);
        ((Runner) loggedUzer).removePayment(payment);

    }



    @RolesAllowed({"Organiser"})
    public boolean checkIfNameExists(String name) {
        return courseFacade.checkByNameIfCourseExists(name);
    }

    @RolesAllowed({"Organiser"})
    public boolean checkIfShortNameExists(String shName) {
        return courseFacade.checkByShortNameIfCourseExists(shName);
    }

    public Course findByShortName(String shortName) {
       return courseFacade.findByShortName(shortName);
    }

    public List<Course> getFutureCourses() {
        Date date = new Date();
       return  courseFacade.findBeforeDate(date);
              
    }


    public boolean isAvalibleRunnersLimit(Course viewedCourse) {
        return (viewedCourse.getPayments().size()) < (viewedCourse.getRunnersLimit());
    }
//    @RolesAllowed({})
//    @Deprecated
//    public void payForRun(Payment paymentByUserAndCourse) throws BasicApplicationException {
//        paymentByUserAndCourse.setCoursePayed(true);
//        paymentEndPoint.saveAfterEdit(paymentByUserAndCourse);
//    }
}
