/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.facade;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.exceptions.OverLimitRunnersException;
import pl.java.biniek.exception.interceptor.backend.ExceptionAndLoggingInterceptorWithRepackingExceptionsForFACADE;
import pl.java.biniek.model.Course;
import pl.java.biniek.model.Payment;
import pl.java.biniek.model.Runner;

/**
 *
 * @author samsung
 */
@Stateless
@Singleton
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(ExceptionAndLoggingInterceptorWithRepackingExceptionsForFACADE.class)
public class PaymentFacade extends AbstractFacade<Payment> {

    @PersistenceContext(unitName = "com.mycompany_aProjektKoncowy02_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @EJB
    CourseFacade courseFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaymentFacade() {
        super(Payment.class);
    }

//synchronized
    @Override
    public synchronized void create(Payment entity) throws BasicApplicationException {
        Course course=courseFacade.reload(entity.getCourse().getId());

        if (course.getPayments().size() < course.getRunnersLimit()) {

            super.create(entity); //To change body of generated methods, choose Tools | Templates.
            em.flush();
       } else {
            throw new OverLimitRunnersException("przekroczono limit zapisów");
       }
    }

     /**
     * Return the payment if exists 
     * Cought exception inside NoResultException
     *
     * @return the Payment 
     */
    public Payment findPaymentByUserAndCourse(Runner runner, Course course) {
        if (runner == null && course == null) {
            return null;
        } else {
            //   long idRunner = runner.getId();
            //   long idCourse= course.getId();

            //  Query tq1 = em.createQuery("SELECT d FROM Payment d WHERE d.course = ?1 AND d.runner=?2", Payment.class);
            //    tq.setParameter("runner", runner, "course", course);
            TypedQuery<Payment> tq = em.createNamedQuery("Payment.findByCourseAndRunner", Payment.class);
            tq.setParameter(1, course);
            tq.setParameter(2, runner);

            try {
                return (Payment) tq.getSingleResult();
            } catch (javax.persistence.NoResultException e) {
             //  System.out.println("LOGGER jak by co i EXCEPTION!!!!!!! " + e); // LOGGER jak by co i EXCEPTION!!!!!!! -- uzgodniono z MK że nie
                Logger.getGlobal().log(Level.INFO, "Logged Exception  in PaymentFacade- NoResultException: "+e);
           
                return null;

            }

        }
    }
     public List<Payment> findPaymentsByCourse(Course course) {
        
            TypedQuery<Payment> tq = em.createNamedQuery("Payment.findAllByCourse", Payment.class);
            tq.setParameter("course", course);
             return  tq.getResultList();
           


            }

           public List<Payment> findPaymentsByRunner(Runner runner) {
        
            TypedQuery<Payment> tq = em.createNamedQuery("Payment.findAllByUzer", Payment.class);
            tq.setParameter("runner", runner);
             return  tq.getResultList();
           


            }
    

//    @Deprecated
//    public Payment findPaymentByUserAndCours(Runner runner, Course course) {
//        if (runner == null && course == null) {
//            return null;
//        } else {
//            //   long idRunner = runner.getId();
//            //   long idCourse= course.getId();
//
//            Query tq = em.createQuery("SELECT d FROM Payment d WHERE d.course = ?1 AND d.runner=?2", Payment.class);
//            tq.setParameter(1, course);
//            tq.setParameter(2, runner);
//
//            try {
//                return (Payment) tq.getSingleResult();
//            } catch (javax.persistence.NoResultException e) {
//
//               
//                return null;
//
//            }
//
//        }
//    }
}
