/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.endpoints;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.TypedQuery;
import pl.java.biniek.exceptions.AftertTimeException;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.facade.PaymentFacade;
import pl.java.biniek.exception.interceptor.backend.LoggingInterceptorWithRepackingForEndPoint;
import pl.java.biniek.model.Course;
import pl.java.biniek.model.Payment;
import pl.java.biniek.model.Runner;

/**
 *
 * @author java pbi moje!!!!!
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptorWithRepackingForEndPoint.class)
public class PaymentEndPoint implements Serializable {

    @EJB
    private PaymentFacade paymentFacade;

    @RolesAllowed({"Runner"})
    public void remove(Payment payment) throws BasicApplicationException {
        Date now = new Date();
        if (payment.getCourse().getDateOfStart().after(now)) {
            paymentFacade.remove(payment);
        } else {
            throw new AftertTimeException();
        }
    }


    @RolesAllowed({"Runner"})
    public void createPayment(Payment payment) throws BasicApplicationException {
        Date now = new Date();
        if (payment.getCourse().getDateOfStart().after(now)) {
            paymentFacade.create(payment);
        } else {
            throw new AftertTimeException();
        }
    }

    @RolesAllowed({"Runner"})
    public void saveAfterEdit(Payment payment) throws BasicApplicationException {
        Date now = new Date();
        if (payment.getCourse().getDateOfStart().after(now)) {
            paymentFacade.edit(payment);
        } else {
            throw new AftertTimeException();
        }

      
    }

    public List<Payment> getAllPayments() {
        return paymentFacade.findAll();
    }

    public Payment find(long id) {
        return paymentFacade.find(id);
    }

    public Payment findPaymentByUserAndCourse(Runner runner, Course course) {
        return paymentFacade.findPaymentByUserAndCourse(runner, course);
    }

    public List<Payment> findPaymentsByCourse(Course course) {
        return paymentFacade.findPaymentsByCourse(course);
    }

    public List<Payment> findPaymentsByRunner(Runner runner) {
        return paymentFacade.findPaymentsByRunner(runner);
    }
}
