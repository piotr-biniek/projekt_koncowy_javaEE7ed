/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author samsung
 */
@ApplicationException(rollback=true)
public class WrongUzerApplicationException extends BasicApplicationException{

    static final long serialVersionUID=10L;
    public WrongUzerApplicationException() {
    }

    public WrongUzerApplicationException(String message) {
        super(message);
    }

    public WrongUzerApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongUzerApplicationException(Throwable cause) {
        super(cause);
    }
    
    
    
}
