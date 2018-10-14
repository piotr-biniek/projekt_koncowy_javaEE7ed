/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.exceptions;

import javax.ejb.ApplicationException;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author samsung
 */
@ApplicationException(rollback=true)
public class DBException extends BasicApplicationException{
      
    public static String ENTITY_EXISTS_EXCEPTION="messages.excep.entityexistsexception";
    public static String ENTITY_NOT_FOUND_EXCEPTION="messages.excep.entitynotfoundexception";
    public static String NO_RESULT_EXCEPTION="messages.excep.noresultexception";
    public static String TRANSACTION_REQUIRED_EXCEPTION="messages.excep.transactionrequiredexception";
    public static String OPTIMISTIC_LOCK_EXCEPTION="messages.excep.optimisticlockexception";
    
    public DBException() {
    }

    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(Throwable cause) {
        super(cause);
    }
    
    
    
}
