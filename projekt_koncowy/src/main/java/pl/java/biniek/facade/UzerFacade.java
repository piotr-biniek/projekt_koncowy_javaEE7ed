/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import pl.java.biniek.exception.interceptor.backend.ExceptionAndLoggingInterceptorWithRepackingExceptionsForFACADE;
import pl.java.biniek.model.Course;
import pl.java.biniek.model.Payment;
import pl.java.biniek.model.Runner;
import pl.java.biniek.model.Uzer;

/**
 *
 * @author samsung
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(ExceptionAndLoggingInterceptorWithRepackingExceptionsForFACADE.class)
public class UzerFacade extends AbstractFacade<Uzer> {

    @PersistenceContext(unitName = "com.mycompany_aProjektKoncowy02_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UzerFacade() {
        super(Uzer.class);
    }

    public Uzer findUzerByEmail(String email) {
        if(email==null) return null;
        else{
        TypedQuery<Uzer> tq = em.createNamedQuery("Uzer.findByEmail", Uzer.class);
        tq.setParameter("email", email);
        return tq.getSingleResult();
        }
    }

    
        public boolean checkIfEmailExists(String email) {// todo dodac count
      
       
        Query tq = em.createNamedQuery("Uzer.findByEmail");
        tq.setParameter("email", email);
         return tq.getResultList().size()>=1;
        }
    }
    
    
//    
//    public boolean doesUniqueEmailExists(String email) {
//       // SELECT d FROM Uzer d WHERE
//        
//         Query q = em.createQuery("SELECT b FROM Uzer b WHERE b.email=?1");
////String query = "SELECT * FROM Uzer b WHERE b.email='"+email+"'";
//            q.setParameter(1, email);
//
//        return q.getSingleResult()getResultList(...).size() == 1;
//
//     
//rs = stmt.executeQuery(query);
//ResultSetMetaData metaData = rs.getMetaData();
//rowcount = metaData.getColumnCount();
//        
//        q.setParameter(1, email);
//   
//    
//
//            try {
//                 q.getResultList();
//            } catch ( javax.persistence.NoResultException e) {
//                
//           
//                return null;
//                
//            }
//       
//        }
//    }
//    
//    
//    
////    em.createQuery(
////    "SELECT COUNT(b.email) FROM Uzer b WHERE b.email=:email"
////         //   SELECT COUNT(b.email) FROM Buyer b WHERE b.email=:emailparam
//            
//          //  SELECT d FROM Uzer d WHERE d.email = :email"
//);
//    
    
    

