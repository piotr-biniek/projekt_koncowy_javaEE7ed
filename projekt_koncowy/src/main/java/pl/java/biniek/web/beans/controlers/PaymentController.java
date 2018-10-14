/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.web.beans.controlers;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pl.java.biniek.endpoints.service.MailServiceGmail;
import pl.java.biniek.endpoints.PaymentEndPoint;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.model.Course;
import pl.java.biniek.model.Payment;
import pl.java.biniek.model.Runner;
import pl.java.biniek.exception.interceptor.frontend.BinderController;

/**
 *
 * @author samsung
 */
@Named
@SessionScoped
@BinderController
public class PaymentController implements Serializable {

    @EJB
    PaymentEndPoint paymentEndPoint;
    
 //  @RolesAllowed("Runner")
//    public String save(Payment payment)throws BasicApplicationException {
//
//        paymentEndPoint.createPayment(payment);
//        AplicationController.showInfoSuccesMessage("");
//        MailServiceGmail.fireSignedForCourseInformation(payment.getRunner(),payment.getCourse());
//        return "listOfMyCourses";
//
//    }
 
    public void saveEdited(Payment payment)throws BasicApplicationException{
        paymentEndPoint.saveAfterEdit(payment);
        AplicationController.showInfoSuccesMessage("");
    }

    public void delete (Payment payment)throws BasicApplicationException{
        
    paymentEndPoint.remove( paymentEndPoint.find((Long)payment.getId()));
    AplicationController.showInfoSuccesMessage("");
     

    }
    public Payment findPaymentByUserAndCourse(Runner runner,Course course){
    return paymentEndPoint.findPaymentByUserAndCourse(runner, course);
}

      public List<Payment> findPaymentsByCourse(Course course) {

        return paymentEndPoint.findPaymentsByCourse(course);

    }

    public List<Payment> findPaymentsByRunner(Runner runner) {

        return paymentEndPoint.findPaymentsByRunner(runner);

    }
  
}
