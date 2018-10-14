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
public class UnexpectedDBException extends DBException{
static final long serialVersionUID=20L;
    public UnexpectedDBException() {
    }

    public UnexpectedDBException(String message) {
        super(message);
    }

    public UnexpectedDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedDBException(Throwable cause) {
        super(cause);
    }
    
    
    
}
