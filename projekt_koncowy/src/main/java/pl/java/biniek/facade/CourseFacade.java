/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.facade;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pl.java.biniek.exception.interceptor.backend.ExceptionAndLoggingInterceptorWithRepackingExceptionsForFACADE;
import pl.java.biniek.model.Course;


/**
 *
 * @author samsung
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(ExceptionAndLoggingInterceptorWithRepackingExceptionsForFACADE.class)
public class CourseFacade extends AbstractFacade<Course> {

    @PersistenceContext(unitName = "com.mycompany_aProjektKoncowy02_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CourseFacade() {
        super(Course.class);
    }



    public boolean checkByNameIfCourseExists(String name) {// todo przerobić na  count

        Query tq = em.createNamedQuery("Course.findByName");
        tq.setParameter("name", name);

        return tq.getResultList().size() == 1;
    }

    public boolean checkByShortNameIfCourseExists(String shortName) {// todo przerobić na  count

        Query tq = em.createNamedQuery("Course.findByShortName");
        tq.setParameter("shortName", shortName);

        return tq.getResultList().size() == 1;
    }

    public Course findByShortName(String shortName) {

        Query tq = em.createNamedQuery("Course.findByShortName");
        tq.setParameter("shortName", shortName);

        return (Course) tq.getSingleResult();
    }

    public Course reload(long id) {
        Query q = em.createNamedQuery("Course.findById");
        q.setParameter("id", id);
        return (Course) q.getSingleResult();

    }
        public List<Course> findBeforeDate(Date date) {

        Query tq = em.createNamedQuery("Course.findBeforeDate");
        tq.setParameter("dateOfStart", date);

        return  tq.getResultList();
    }
}
