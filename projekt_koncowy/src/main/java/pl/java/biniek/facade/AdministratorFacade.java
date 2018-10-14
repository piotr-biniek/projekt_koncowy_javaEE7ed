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
import pl.java.biniek.exception.interceptor.backend.ExceptionAndLoggingInterceptorWithRepackingExceptionsForFACADE;
import pl.java.biniek.model.Administrator;

/**
 *
 * @author samsung
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(ExceptionAndLoggingInterceptorWithRepackingExceptionsForFACADE.class)
public class AdministratorFacade extends AbstractFacade<Administrator> {

    @PersistenceContext(unitName = "com.mycompany_aProjektKoncowy02_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministratorFacade() {
        super(Administrator.class);
    }
    
}
